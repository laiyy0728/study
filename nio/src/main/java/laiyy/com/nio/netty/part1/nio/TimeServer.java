package laiyy.com.nio.netty.part1.nio;

/**
 * @author laiyy
 * @createDate 2018/5/23 13:50
 * @description 使用 NIO 创建的 TimeServer 主服务端
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e){

            }
        }

        // 创建一个监听 8080 端口的多路复用器线程
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
    }

}
