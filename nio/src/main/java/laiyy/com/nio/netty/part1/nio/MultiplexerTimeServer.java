package laiyy.com.nio.netty.part1.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * @author laiyy
 * @createDate 2018/5/23 13:53
 * @description 服务端线程处理
 */
public class MultiplexerTimeServer implements Runnable {

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    private volatile boolean stop;

    /**
     * 初始化多路复用器，绑定监听端口
     *
     * @param port 监听端口
     */
    public MultiplexerTimeServer(int port) {

        try {
            // 打开一个 ServerSocketChannel，用于监听客户端连接，是所有客户端连接的父管道
            serverSocketChannel = ServerSocketChannel.open();
            // 绑定监听端口，设置为非阻塞模式，最多积压 1024 个请求
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
            serverSocketChannel.configureBlocking(false);
            // 创建多路复用器
            selector = Selector.open();
            // 将 ServerSocketChannel 注册到多路复用器上，监听 accept 事件（请求接受）
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The time server is start in port: " + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop() {
        this.stop = true;
    }

    @Override
    public void run() {
        // 如果没有停止，就继续开始监听
        while (!stop) {
            try {
                // 多路复用器在 run 方法内容无限循环，轮询准备就绪的 key，超时时间为 1 秒
                selector.select(1000);
                // 轮询到的就绪的key
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                // 遍历轮询到的 key
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key = null;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    // 将正操作的轮询key从迭代器中去除，防止重复迭代
                    iterator.remove();
                    try {
                        // 处理接收的请求
                        handleInput(key);
                    } catch (Exception e) {
                        key.cancel();
                        if (key.channel() != null) {
                            key.channel().close();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException{
        // 如果轮询 key 可用
        if (key.isValid()){
            // 如果 key 可用被接收
            if (key.isAcceptable()) {
                // 获取轮询 key 的管道
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                // 从管道中接收请求
                SocketChannel socketChannel = serverSocketChannel.accept();
                // 设置为非阻塞模式
                socketChannel.configureBlocking(false);
                // 多路复用器注册管道
                socketChannel.register(selector, SelectionKey.OP_ACCEPT);
            }
            // 如果 key 可读
            if (key.isReadable()){
                // 读取数据信息
                SocketChannel socketChannel = (SocketChannel) key.channel();
                // 缓冲区大小 1024
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                // 从管道中读取数据
                int readBytes = socketChannel.read(readBuffer);
                // 读取到了字节，对字节进行编解码
                if (readBytes > 0){
                    // 反置偏移量，将缓冲区的 limit 置为 position，position 置为 0，用于后续对缓冲去的读取操作
                    readBuffer.flip();
                    // 创建一个与缓冲区大小相同的 byte 数组
                    byte[] bytes = new byte[readBuffer.remaining()];
                    // 从缓冲区中获取数据
                    readBuffer.get(bytes);
                    // 将数据转换为 String
                    String body = new String(bytes, "UTF-8");
                    // 打印得到的新年西
                    System.out.println("The time server receive order: " + body);
                    // 判断客户端请求
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                    // 向客户端发送响应
                    doWrite(socketChannel, currentTime);
                } else if (readBytes < 0){
                    // 链路已经关闭，需要关闭 SocketChannel，释放资源
                    key.cancel();
                    socketChannel.close();
                } else {
                    // 没有读取到字节，正常场景，忽略
                }
            }
        }
    }

    private void doWrite(SocketChannel socketChannel, String currentTime) throws IOException {
        if (currentTime != null && currentTime.trim().length() > 0){
            // 将要向客户端发送的响应信息转换为 byte 数组
            byte[] bytes = currentTime.getBytes();
            // 创建一个输出缓冲区
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            // 想缓冲区中放入数据
            writeBuffer.put(bytes);
            // 重置偏移量
            writeBuffer.flip();
            // 发送数据
            socketChannel.write(writeBuffer);
        }
    }
}
