package laiyy.com.nio.netty.part1.aio;

/**
 * @author laiyy
 * @date 2018/5/25 16:42
 * @description NIO 2.0
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e){
                // 不做处理
            }
        }
        AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
        new Thread(timeServer, "AIO-AsyncTimeServerHandler-001").start();
    }

}
