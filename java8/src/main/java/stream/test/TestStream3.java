package stream.test;

import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestStream3 {

    @Test
    public void test6(){
        IntSupplier supplier = new IntSupplier() {
            private int previous = 0;
            private int current = 1;
            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };




        IntStream.generate(supplier).limit(10).forEach(System.err::println);
    }

    /**
     * 1、无限流：生成
     */
    @Test
    public void test5(){
        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.err::println);
    }

    /**
     * 1、无限流的迭代
     */
    @Test
    public void test4(){
        // 生成 10 个偶数
//        Stream.iterate(0, n -> n + 2)
//                .limit(10)
//                .forEach(System.err::println);

        // 生成 20 个斐波那契数列
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .map(t -> t[0])
                .forEach(System.err::println);
    }



    /**
     * 文件流
     */
    @Test
    public void test3(){
        long uniqueWords = 0;

        try (Stream<String> lines = Files.lines(Paths.get("D:\\coding\\github.com\\study\\java8\\src\\main\\file\\test1.txt"), Charset.defaultCharset());) {
            uniqueWords = lines.flatMap( line -> Arrays.stream(line.split(","))).distinct().count();
            System.out.println(uniqueWords);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 用数组创建的流
     */
    @Test
    public void test2(){
        int[] numbers = {1,2,3,4,5,6,7,8,9,10};
        System.out.println(Arrays.stream(numbers).sum());
    }

    /**
     * 使用静态方法  Stream.of 显式创建一个流
     * 将字符串转为大写，并打印
     */
    @Test
    public void test1(){
        Stream<String> stringStream = Stream.of("Java 8", "Lambda", "in", "Action");
        stringStream.map(String::toUpperCase).forEach(System.err::println);

        Stream<Object> empty = Stream.empty(); // 创建一个空的流
    }


}
