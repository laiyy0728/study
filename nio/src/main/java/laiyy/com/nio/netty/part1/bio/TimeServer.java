package laiyy.com.nio.netty.part1.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author laiyy
 * @createDate 2018/5/21 15:30
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0){
            try {
                port = Integer.valueOf(args[0]);
            } catch (Exception e){
                // 出现异常不做任何处理
            }
        }
        ServerSocket server = null;
        try {
            // 1、创建一个服务端，监听 8080 端口
            server = new ServerSocket(port);
            System.out.println("The time server is start in port: " + port);
            Socket socket = null;
            // 2、死循环监听客户端请求
            while (true){
                // 3、接收客户端请求
                socket = server.accept();
                // 4、将客户端请求分线程处理
                new Thread(new TimeServerHandler(socket)).start();
            }
        } catch (Exception e){

        } finally {
            if (server != null){
                System.out.println("The time server close");
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
