package laiyy.com.nio.netty.part5.test1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

/**
 * @author laiyy
 * @date 2018/6/7 15:10
 * @description
 */
public class TestUserInfo {

    public static void main(String[] args) throws IOException {
        UserInfo userInfo = new UserInfo();
        userInfo = userInfo.buildUserId(100).buildUserName("Welcome to Netty");
//        测试2：JDK 序列化 与 ByteArray 性能对比
        int loop = 1_000_000;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        long startTime = System.currentTimeMillis();
        for (int index = 0; index < loop; index++){
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            os.writeObject(userInfo);
            os.flush();
            os.close();
            byte[] bytes = bos.toByteArray();
            bos.close();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("jdk serializable time : " + (endTime - startTime) + " ms");


        startTime = System.currentTimeMillis();
        for (int index = 0; index < loop; index ++ ){
            byte[] code = userInfo.code();
        }
        endTime = System.currentTimeMillis();
        System.out.println("the byte array serializable time: " + (endTime - startTime) + " ms");


//        测试1：JDK 二进制码流 与 ByteArray 二进制码流大小对比

//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        ObjectOutputStream os = new ObjectOutputStream(bos);
//        os.writeObject(userInfo);
//        os.flush();
//        os.close();
//
//        byte[] bytes = bos.toByteArray();
//        System.out.println("The jdk serializable length is: " + bytes.length);
//        bos.close();
//        System.out.println("----------------------------");
//
//        System.out.println("the byte array serializable length is: " + userInfo.code().length);

    }

}
