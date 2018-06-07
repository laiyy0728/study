package laiyy.com.nio.netty.part5.test2;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;
import laiyy.com.nio.netty.part5.test1.UserInfo;

/**
 * @author laiyy
 * @date 2018/6/7 16:12
 * @description
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {
    private int sendNumber;

    public EchoClientHandler(int sendNumber) {
        this.sendNumber = sendNumber;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        try {
            UserInfo[] infos = userInfo();
            for (UserInfo info : infos) {
                ctx.writeAndFlush(info);
            }
            ctx.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private UserInfo[] userInfo() {
        UserInfo[] userInfos = new UserInfo[sendNumber];
        UserInfo userInfo = null;
        for (int index = 0; index < sendNumber; index ++) {
            userInfo = new UserInfo();
            userInfo.setUserId(index);
            userInfo.setUsername("ABCDEFG ---> " + index);
            userInfos[index] = userInfo;
        }
        return userInfos;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client receive the message pack message : " + msg);
        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
