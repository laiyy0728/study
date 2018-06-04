package laiyy.com.nio.netty.part2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author laiyy
 * @date 2018/6/4 16:50
 * @description
 */
public class TimeClient {

    public static void main(String[] args) {
        int port = 8080;
        String host = "127.0.0.1";

        new TimeClient().connect(host, port);

    }

    private void connect(String host, int port) {
        // 配置客户端 NIO 线程
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        // 当创建 NioSocketChannel 成功后没在进行初始化时，将 ChannelHandler 设置到 ChannelPipeline 中，用于处理 IO 事件
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new TimeClientHandler());
                        }
                    });
            // 发起异步链接
            ChannelFuture future = bootstrap.connect(host, port).sync();
            // 等待客户端链路关闭
            future.channel().closeFuture().sync();
        } catch (Exception e){

        }finally {
            group.shutdownGracefully();
        }
    }


}
