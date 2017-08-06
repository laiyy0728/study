package future.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by laiyy
 * Date 2017/8/4.
 */
public class TestDiscount {

    private List<Shop> shops = Arrays.asList(
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll")
    );

    private static final String product = "IPhone 7 Plus";

    @Test
    public void testStream(){
        CompletableFuture[] futures = findPricesStream(product).map(f -> f.thenAcceptAsync(System.err::println)).toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures).join();
    }

    private Stream<CompletableFuture<String>> findPricesStream(String product) {
        return shops.stream().map( shop -> CompletableFuture.supplyAsync( () -> shop.getPrice(product), executor))
                .map( future -> future.thenApply(Quote::parse))
                .map( future -> future.thenCompose( quote -> CompletableFuture.supplyAsync( () -> Discount.applyDiscount(quote), executor )));
    }

    private static final Random random = new Random();

    /**
     * 模拟一个 0.5-2.5 秒的随机延迟
     */
    public static void randomDeply(){
        int deply = 500 + random.nextInt(2000);
        try {
            Thread.sleep(deply);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setDaemon(true); // 使用守护线程，这种非按时不会阻止程序的关停
            return thread;
        }
    });

    @Test
    public void test() {
        for (int index = 0; index < 10; index++) {
            long start = System.currentTimeMillis();
            String product = "Iphone 7 Plus";
            System.out.println(findPrices(product));  // 运行 10 次，2037,2000,2001,2001,2001,2002,2001,2001,2001,2001
//            System.out.println(findPrices(product)); // 运行 10 次，2038，2001,2002,2000,2001，2001,2000,2001,2002,2001
            long end = System.currentTimeMillis();
            System.out.println(end - start);
        }
    }




    /**
     * 使用 CompletableFuture 实现
     */
    private List<String> findPrices(String product) {
        List<CompletableFuture<String>> completableFutures = shops.stream().map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))  // 以异步方式取得每个 shop 中产品的原始价格
                .map(future -> future.thenApply(Quote::parse))  // 使用龙外一个异步任务构造期望的 Future，申请折扣
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor))) // Quote 对象存在时，对其返回值进行转换
                .collect(Collectors.toList());

        return completableFutures.stream().map(CompletableFuture::join).collect(Collectors.toList()); // 等待流中所有 Future 执行完毕，并提取各自的返回值
    }

    /**
     * 使用 Stream 流实现
     * @param product
     * @return
     */
//    private List<String> findPrices(String product) {
//        return shops.parallelStream()
//                .map( shop -> shop.getPrice(product) )
//                .map( Quote::parse )
//                .map( Discount::applyDiscount )
//                .collect(Collectors.toList());
//    }


}
