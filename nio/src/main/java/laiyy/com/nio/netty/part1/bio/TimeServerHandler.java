package laiyy.com.nio.netty.part1.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * @author laiyy
 * @createDate 2018/5/21 15:35
 */
public class TimeServerHandler implements Runnable{

    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("监听客户端请求");
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            // 1、创建从客户端获取请求流
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 2、创建向客户端发送响应流
            writer = new PrintWriter(socket.getOutputStream(), true);
            String currentTime;
            String body;
            while (true) {
                // 3、获取客户端请求体
                body = reader.readLine();
                if (body == null){
                    break;
                }
                System.out.println("The time Server receive order: " + body);
                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                // 4、想客户端发送数据
                writer.println(currentTime);
            }
        } catch (Exception e){
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (writer != null){
                writer.close();
            }
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
