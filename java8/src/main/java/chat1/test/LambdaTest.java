package chat1.test;

import chat1.entity.Apple;
import chat1.inter.BufferedReaderProcessor;
import chat1.inter.Predicate;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by laiyy
 * Date 2017/7/3.
 */
public class LambdaTest {

    private List<Apple> apples;

    private List<Integer> ids;

    @Before
    public void initApple() {
        apples = Arrays.asList(new Apple(150, "green"),
                new Apple(80, "red"),
                new Apple(120, "green"));

        ids = Arrays.asList(1, 2, 3, 4, 5);
    }

    @Test
    public void testLambda() {
        List<Apple> filter = filter(apples, (Apple apple) -> "red".equals(apple.getColor()));
        filter.forEach(apple -> System.out.println(apple));

        System.out.println("-----------");

        List<Integer> numbers = filter(ids, (Integer i) -> i % 2 == 0);
        numbers.forEach(number -> System.out.println(number));

    }

    @Test
    public void testPath() throws FileNotFoundException {
        FileReader fileReader = new FileReader("");
//        fileReader.
    }

    @Test
    public void testProcessFile() throws IOException{
        String oneLine = processFile( (BufferedReader br) -> br.readLine() + br.readLine());
        System.out.println(oneLine);
    }

    private static String processFile(BufferedReaderProcessor processor) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\Github\\study\\java8\\src\\main\\file\\test1.txt"))) {
            return processor.process(br);
        }
    }


    @Test
    public void testLambda1() {
        Runnable r1 = () -> System.out.println("Hello");

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello 2");
            }
        };

        process(r1);
        process(r2);

        process(() -> System.out.println("Hello 3"));

    }

    public static void process(Runnable runnable) {
        runnable.run();
    }


    /**
     * 实现 Lambda
     *
     * @param list      需要过滤的集合
     * @param predicate Lambda
     * @param <T>       类型泛型，可以是任意类型
     * @return 返回过滤后的集合
     */
    private static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<T>();
        for (T e : list) {
            if (predicate.test(e)) {
                result.add(e);
            }
        }
        return result;
    }
}