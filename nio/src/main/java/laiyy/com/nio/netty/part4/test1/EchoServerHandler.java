package laiyy.com.nio.netty.part4.test1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * @author laiyy
 * @date 2018/6/7 11:41
 * @description
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 直接打印接收到的消息
        String body = (String) msg;
        System.out.println("This is " + ++counter + " times receive client:[ " + body + " ]");
        // 接收到的消息后加上分隔符
        body += "$_";
        ByteBuf echo = Unpooled.copiedBuffer(body.getBytes());
        // 发送给客户端
        ctx.writeAndFlush(echo);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
