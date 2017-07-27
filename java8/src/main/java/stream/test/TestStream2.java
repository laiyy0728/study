package stream.test;

import stream.entity.Trader;
import stream.entity.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by laiyy
 * Date 2017/7/20.
 */
public class TestStream2 {

    private List<Transaction> transactions;


    /**
     * 创建一个三元流（勾股数）
     * @throws Exception
     */
    @Test
    public void test9() throws Exception {
        // 第一种生成方式：求两次平方根
//        IntStream.rangeClosed(1, 100) // 取出 0 到 100 中的所有数，生成数值流
//            .boxed() // 转换为对象流
//            .flatMap( a ->
//                IntStream.rangeClosed(a, 100) // 取出从 a 到 100 的所有数（为避免取到重复勾股数，开始数不能 0 开始）
//                    .filter( b -> Math.sqrt( a * a + b * b) % 1 == 0) // 过滤出 a * a + b * b 的开方是整数的数
//                    .mapToObj( b -> new int[] {a, b, (int)Math.sqrt(a * b + b*b)})// 将取到的 b 放到一个三元数组中
//            ).forEach( t -> {
//            System.out.println(t[0] + " ----- " + t[1] + " ----- " + t[2]);
//        });

        // 第二种生成方式：只求一次平方根
        IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap( a ->
                        IntStream.rangeClosed(a, 100)
                            .mapToObj( b -> new double[]{a, b, Math.sqrt( a * a + b * b)})
                                .filter(t -> t[2] % 1 == 0)
                ).forEach( t -> {
            System.out.println((int)t[0] + " ----- " + (int)t[1] + " ----- " + (int)t[2]);
        });
    }

    /**
     * 交易额最小的交易
     */
    @Test
    public void test8(){
        System.out.println(transactions.stream().reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2));
        System.out.println(transactions.stream().min(Comparator.comparing(Transaction::getValue)));
    }

    /**
     * 所有交易中，最高交易额
     */
    @Test
    public void test7(){
        System.out.println(transactions.stream().map(Transaction::getValue).reduce(0, Integer::max));
        System.out.println(transactions.stream().max(Comparator.comparing(Transaction::getValue)));
        transactions.stream().mapToInt(Transaction::getValue).max().ifPresent(System.err::print);
    }

    /**
     * 生活在剑桥的交易员的所有交易额（总额）
     */
    @Test
    public void test6(){
        System.out.println(transactions.stream().filter(transaction -> transaction.getTrader().getCity().equalsIgnoreCase("Cambridge")).map(Transaction::getValue).reduce(0, Integer::sum));
    }

    /**
     * 是否有交易员在米兰工作
     */
    @Test
    public void test5(){
        System.out.println(transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().equalsIgnoreCase("Milan")));
    }

    /**
     * 返回所有交易员的姓名字符串，按字母顺序排序
     */
    @Test
    public void test4(){
        transactions.stream().map( transaction -> transaction.getTrader().getName()).sorted(Comparator.comparing(String::trim)).collect(Collectors.toList()).forEach(System.err::println);
    }

    /**
     * 查找所有来自于剑桥的交易员，并按姓名排序
     */
    @Test
    public void test3(){
        transactions.stream().filter( transaction -> transaction.getTrader().getCity().equalsIgnoreCase("Cambridge")).sorted(Comparator.comparing(transaction -> transaction.getTrader().getName())).collect(Collectors.toList()).forEach(System.err::println);
    }

    /**
     * 交易员都在哪些城市中工作过
     */
    @Test
    public void test2(){
        transactions.stream().map(transaction -> transaction.getTrader().getCity()).distinct().collect(Collectors.toList()).forEach(System.err::println);
    }

    /**
     * 找出 2011 年的所有交易，并按照交易额排序(从低到高)
     */
    @Test
    public void test1(){
        transactions.stream().filter( trader -> trader.getYear() == 2011).sorted(Comparator.comparing(Transaction::getValue)).forEach(System.err::println);
    }

    @Before
    public void before(){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

}
