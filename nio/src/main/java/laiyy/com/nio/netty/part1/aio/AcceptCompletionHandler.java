package laiyy.com.nio.netty.part1.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author laiyy
 * @date 2018/5/25 16:48
 * @description
 */
public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncTimeServerHandler> {
    @Override
    public void completed(AsynchronousSocketChannel result, AsyncTimeServerHandler attachment) {
        // 客户端已经接收成功了，为何要再次调用 accept？
        // 调用 AsynchronousServerSocketChannel 的 accept 方法后，如果有新的客户端接入，
        // 系统会回调传入的  CompletionHandler 实例的 completed 方法，表示新的客户端已经接入成功。
        // 因为一个 AsynchronousServerSocketChannel 可以接收成千上万个客户端，所有需要继续调用 accept 方法，
        // 接收其他客户端的链接，最终形成一个循环。每当接收一个客户读连接成功后，再异步接收新的客户端连接
        attachment.asynchronousServerSocketChannel.accept(attachment, this);
        // 链路建立成功后，服务端需要接收客户端的请求消息，创建 ByteBuffer，预分配 1MB 缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 调用 AsynchronousSocketChannel 的 read 方法，进行异步读操作
        result.read(buffer, buffer, new ReadCompletionHandler(result));
    }

    @Override
    public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
        attachment.latch.countDown();
    }
}
