package laiyy.com.nio.netty.part5.test2;

import org.msgpack.MessagePack;
import org.msgpack.template.StringTemplate;
import org.msgpack.template.Templates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author laiyy
 * @date 2018/6/7 15:58
 * @description
 */
public class TestMessagePack {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        List<String> src = new ArrayList<>();
        src.add("msgpack");
        src.add("kumofs");
        src.add("viver");

        MessagePack messagePack = new MessagePack();
        byte[] write = messagePack.write(src);
        System.out.println(write.length);

        List<String> list = messagePack.read(write, Templates.tList(StringTemplate.getInstance()));
        list.forEach(System.err::println);

    }

}
