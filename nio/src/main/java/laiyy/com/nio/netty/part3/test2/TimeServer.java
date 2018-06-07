package laiyy.com.nio.netty.part3.test2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author laiyy
 * @date 2018/6/4 16:20
 * @description netty time server
 */
public class TimeServer {

    public static void main(String[] args) throws InterruptedException {
        int port = 8087;
        new TimeServer().bind(port);
    }

    private void bind(int port) throws InterruptedException {
        // NioEventLoopGroup 是一个线程组，包含了一组 NIO 线程
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // ServerBootstrap：Netty 用于启动 NIO 服务的辅助启动类，目的是降低服务端开发复杂度
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    // 绑定 IO 事件处理类，主要用于处理 IO 事件，如：日志记录、消息编解码等
                    .childHandler(new ChildChannelHandler());

            // 绑定端口、同步等待成功
            ChannelFuture future = serverBootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } finally {
            // 退出
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            // 加入 Netty 的编解码器
            // 注意：初始化 Channel 的时候，需要格外注意顺序，由于 socketChannel.pipeline().addLast 是添加到管道最后
            // 所以需要先指定每一行编码的最大长度：1024
            // 再指定编码采用 StringDecoder
            // 在指定使用哪个 Handler 处理数据
            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
            socketChannel.pipeline().addLast(new StringDecoder());

            socketChannel.pipeline().addLast(new TimeServerHandler());
        }
    }

}
