package laiyy.com.nio.netty.part5.test2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author laiyy
 * @date 2018/6/7 11:41
 * @description
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        // 直接打印接收到的消息
//        String body = (String) msg;
//        System.out.println("This is " + ++counter + " times receive client:[ " + body + " ]");
//        // 接收到的消息后加上分隔符
//        body += "$_";
//        ByteBuf echo = Unpooled.copiedBuffer(body.getBytes());
//        // 发送给客户端
//        ctx.writeAndFlush(echo);
        System.out.println("server receive the message pack message : " + msg);
        ctx.writeAndFlush(msg);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
