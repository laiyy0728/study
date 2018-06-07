package laiyy.com.nio.netty.part5.test1;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * @author laiyy
 * @date 2018/6/7 14:40
 * @description 实现了 Serializable 接口，生成默认序列号为 1L。
 * 说明 UserInfo 使用 JDK 默认的序列化机制进行序列化和反序列化
 */
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private int userId;

    public byte[] code(){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        byte[] value = this.username.getBytes();
        buffer.putInt(value.length);
        buffer.put(value);
        buffer.putInt(this.userId);
        buffer.flip();
        value = null;
        byte[] result = new byte[buffer.remaining()];
        buffer.get(result);
        return result;
    }

    public UserInfo buildUserName(String username) {
        this.username = username;
        return this;
    }

    public UserInfo buildUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
