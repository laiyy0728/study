package optional;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

public class TestOptional {

    @Test
    public void testProperties(){
        Properties properties = new Properties();
        properties.setProperty("a", "5");
        properties.setProperty("b", "true");
        properties.setProperty("c", "-3");


//        Assert.assertEquals(5, readDuration(properties, "a"));

        // 使用原始方法
//        System.out.println(readDuration(properties, "a"));
        // 使用 Optional
        System.out.println(readOptional(properties, "a"));
    }

    private int readOptional(Properties properties, String name){
       return Optional.ofNullable(properties.getProperty(name))
                .flatMap(TestOptional::stringToInt)
                .filter( value -> value > 0)
                .orElse(0);
    }

    private static Optional<Integer> stringToInt(String key) {
        try {
            return Optional.of(Integer.parseInt(key));
        } catch (Exception  e){
            return Optional.empty();
        }
    }

    private int readDuration(Properties properties, String name){
        String value = properties.getProperty(name);
        if (value != null) {
            try {
                int i = Integer.parseInt(value);
                if (i > 0){
                    return i;
                }
            }catch (Exception e){

            }
        }
        return 0;
    }

    /**
     * Optional 对 Integer.parseInt 的优化
     */
    @Test
    public void testParseInt(){
        String str = "";
        // 原始方法，可能会抛出一个异常
        int i = Integer.parseInt(str);

        // Java 8 的 Optional，安全输出，不会抛出异常
        try{
            Optional<Integer> integer = Optional.of(Integer.parseInt(str));
        }catch (Exception e){
            Optional.empty();
        }
    }



    /**
     * Optional 对 Map 的优化
     */
    @Test
    public void testOptionalMap(){
        Map<String, Object> map = new HashMap<>();
        // 原始方法获取一个不存在的 key 的 value，这时会返回一个 null
        Object key1 = map.get("key");

        // 使用 Java8 的 Optional，返回一个 Optional，更加安全，不会出现空指针异常
        Optional<Object> key = Optional.ofNullable(map.get("key"));
    }



}
