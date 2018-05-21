package laiyy.com.nio.netty.part1.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author laiyy
 * @createDate 2018/5/21 15:43
 */
public class TimeClient {

    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0){
            try {
                port = Integer.valueOf(args[0]);
            }catch (Exception e){
                // 出错不处理
            }
        }
        Socket socket = null;
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            // 1、创建一个客户端请求
            socket = new Socket("127.0.0.1", port);
            // 2、创建一个读取服务器返回数据的输入流
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 3、创建一个发送数据的输出流
            writer = new PrintWriter(socket.getOutputStream(), true);
            // 4、向服务器发送数据
            writer.println("QUERY TIME ORDER");
            System.out.println("Send order 2 server succeed");
            // 5、读取从服务器获取到的数据
            String result = reader.readLine();
            System.out.println("Now is: " + result);
        } catch (Exception e){

        } finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null){
                writer.close();
            }
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
