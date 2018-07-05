import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by laiyy
 * Date 2017/9/21.
 */
@SuppressWarnings("unchecked")
public class Test {

    static String str = "张三,李四,王五,赵六,张三,张三四";

    public static void main(String[] args) {
//        Map map = new HashMap(5);
//        System.out.println("初始化长度为 5 的Map：" + map);
//        Map nowMap = null;
//        System.out.println("初始化 空 Map" + nowMap);
//        nowMap = map;
//        System.out.println("将长度为 5 的map 交给 空 map后，nowMap：" + nowMap + " map：" + map );
//        Map newWordMap= new HashMap<String,String>();
//        newWordMap.put("isEnd","0"); // 给新的空 Map 赋值
//        System.out.println("最新的 Map："+newWordMap);
//        nowMap.put("张",newWordMap);
//        System.out.println("将长度为 5 的map 交给 空 map后，nowMap：" + nowMap + " map：" + map +  "newWordMap：" + newWordMap);
//        nowMap = newWordMap;
//        System.out.println("将长度为 5 的map 交给 空 map后，nowMap：" + nowMap + " map：" + map +  "newWordMap：" + newWordMap);
//        System.out.println("=======================");
//        newWordMap= new HashMap<String,String>();
//        newWordMap.put("isEnd","0"); // 给新的空 Map 赋值
//        System.out.println("最新的 Map："+newWordMap);
//        nowMap.put("四",newWordMap);
//        System.out.println("将长度为 5 的map 交给 空 map后，nowMap：" + nowMap + " map：" + map +  "newWordMap：" + newWordMap);
//        nowMap = newWordMap;
//        System.out.println("将长度为 5 的map 交给 空 map后，nowMap：" + nowMap + " map：" + map +  "newWordMap：" + newWordMap);
        Set<String> keyWords = new HashSet<>(Arrays.asList(str.split(",")));
        addSensitiveWordToHashMap(keyWords);

    }

    public static void addSensitiveWordToHashMap(Set<String> keyWordSet) {

        Map sensitivewodrMap = new HashMap<>(keyWordSet.size()); // 创建一个长度为 n 的Map集合
        String key = "";
        Map nowMap = null; // 声明一个空的 Map 集合
        Iterator<String> iterator = keyWordSet.iterator();  // 遍历敏感词
        while (iterator.hasNext()) {
            key = iterator.next();// 拿到下一个敏感词
            nowMap = sensitivewodrMap; // 空 Map 对象和 长度为 n 的Map 集合，公用一个内存地址
            for (int i = 0; i < key.length(); i++) {
                char keyChar = key.charAt(i); // 拿到敏感词的第 i 个字符
                Object wordMap = nowMap.get(keyChar); // 从长度为 n 的集合里面拿到敏感词第 i 个字符为key的值
                if (wordMap != null) { // 如果拿到了
                    nowMap = (Map) wordMap; // 将拿到的值转换为一个 Map， 并赋值给长度 n 的空 Map
                } else { // 如果没拿到
                    Map newWordMap = new HashMap<String, String>(); // 创建一个新的空 Map
                    newWordMap.put("isEnd", "0"); // 给新的空 Map 赋值
                    nowMap.put(keyChar, newWordMap); // 将拿到的第 i 个字符为 key，新的 map 为值，赋值给空 Map，由于 空 Map 和 长度为 n 的Map 集合公用一个内存地址，所以长度为 n 的Map 集合也添加了一个 key-value
                    nowMap = newWordMap; // 由于 nowMap 和 sensitivewodrMap 公用地址，而此时 newWordMap 是一个新的地址，这时赋值后，nowMap 和 sensitivewodrMap 不在公用一个内存地址
                }
                if (i == key.length() - 1) {
                    nowMap.put("isEnd", "1");
                }
                System.out.println(nowMap);
            }
            System.err.println(sensitivewodrMap);
        }
    }


}
