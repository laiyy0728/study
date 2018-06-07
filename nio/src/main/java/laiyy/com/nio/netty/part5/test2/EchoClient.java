package laiyy.com.nio.netty.part5.test2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author laiyy
 * @date 2018/6/7 16:08
 * @description
 */
public class EchoClient {

    private final String host;

    private final int port;

    private final int sendNumber;

    public static void main(String[] args) throws Exception {
        int port = 8787;
        String host = "127.0.0.1";
        int sendNumber = 10;
        new EchoClient(host, port, sendNumber).run();
    }

    public EchoClient(String host, int port, int sendNumber) {
        this.host = host;
        this.port = port;
        this.sendNumber = sendNumber;
    }

    public void run() throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("msgpack decoder", new MsgPackDecoder());
                            socketChannel.pipeline().addLast("msgpack encoder", new MsgPackEncoder());
                            socketChannel.pipeline().addLast(new EchoClientHandler(sendNumber));
                        }
                    });

            ChannelFuture future = bootstrap.connect(host, port).sync();


            future.channel().closeFuture().sync();

        } finally {
            group.shutdownGracefully();
        }
    }
}
