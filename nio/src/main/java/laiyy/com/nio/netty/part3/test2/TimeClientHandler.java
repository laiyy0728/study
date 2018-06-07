package laiyy.com.nio.netty.part3.test2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author laiyy
 * @date 2018/6/7 9:41
 * @description
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    private int counter;

    private byte[] req;

    public TimeClientHandler() {
        String body = "QUERY TIME ORDER" + System.getProperty("line.separator");
        req = body.getBytes();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf messgae = null;
        for (int index = 0; index < 100; index++) {
            // 循环发送 100 条消息
            messgae = Unpooled.buffer(req.length);
            messgae.writeBytes(req);
            ctx.writeAndFlush(messgae);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] req = new byte[buf.readableBytes()];
//        buf.readBytes(req);
//        String body = new String(req, "UTF-8");
        try {
            String body = (String) msg;
            // 打印接收到的服务器响应，并打印计数器
            System.out.println("Noe is: " + body + "; the counter is: " + ++counter);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
