package future;

import org.junit.Before;
import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

/**
 * Created by laiyy
 * Date 2017/7/28.
 */
public class TestFuture {

    private List<Shop> shops;

//    private final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {
//        @Override
//        public Thread newThread(Runnable r) {
//            Thread thread = new Thread(r);
//            thread.setDaemon(true); // 使用守护线程，这种非按时不会阻止程序的关停
//            return thread;
//        }
//    });

    @Test
    public void test2(){
        Discount discount = new Discount();
        System.out.println(discount.getPrice("zhangsan"));
    }

    @Before
    public void initShops() {
        shops = Arrays.asList(
                new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll")
        );
    }

    @Test
    public void test1() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage usage = memoryMXBean.getHeapMemoryUsage();
//        System.out.println((double) usage.getUsed());

        DecimalFormat format = new DecimalFormat("0.00");

        System.out.println(Double.parseDouble(format.format(22.4212183165)));
    }

    @Test
    public void testFindPrices() throws ExecutionException, InterruptedException {
        long start = System.nanoTime();
//        System.out.println(findPrices("Iphone 7 Plus")); // 使用串行流计算， 4032
//        System.out.println(findPricesByParallel("Iphone 7 Plus")); // 使用并行流计算 1068
//        List<CompletableFuture<String>> byParallelAndAsync = findByParallelAndAsync("Iphone 7 Plus");
        List<String> byParaAsyncAndJoin = findByParaAsyncAndJoin("Iphone 7 Plus");// 使用并行流 + 异步请求，并把所有结果合起来
//        System.out.println(byParallelAndAsync); // 使用并行流 + 异步请求计算 1068
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
        byParaAsyncAndJoin.forEach(System.err::println);

    }

    /**
     * 使用并行流 + 异步请求，并把所有结果合起来
     *
     * @param product
     * @return
     */
    public List<String> findByParaAsyncAndJoin(String product) {
        List<CompletableFuture<String>> collect = shops.parallelStream().map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f",
                shop.getName(), shop.getPrice(product)))).collect(Collectors.toList());
        return collect.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    /**
     * 使用并行流 + 异步请求
     *
     * @param product
     * @return
     */
    public List<CompletableFuture<String>> findByParallelAndAsync(String product) {
        return shops.parallelStream().map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f",
                shop.getName(), shop.getPrice(product)))).collect(Collectors.toList());
    }

    /**
     * 使用并行流计算
     *
     * @param product
     * @return
     */
    public List<String> findPricesByParallel(String product) {
        return shops.parallelStream().map(shop -> String.format("%s price is %.2f",
                shop.getName(), shop.getPrice(product))).collect(Collectors.toList());
    }

    /**
     * 使用串行流计算
     *
     * @param product
     * @return
     */
    public List<String> findPrices(String product) {
        return shops.stream().map(shop -> String.format("%s price is %.2f",
                shop.getName(), shop.getPrice(product))).collect(Collectors.toList());
    }

    @Test
    public void test() {
//        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = getPriceAsync("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("异步计算时间 " + invocationTime
                + " msecs");
// 执行更多任务，比如查询其他商店
//        doSomethingElse();
// 在计算商品价格的同时
        try {
            double price = futurePrice.get();   // 从 Future 中读取值，如果没有值则阻塞
            System.out.printf("得到的结果 %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("总时长 " + retrievalTime + " msecs");
    }

    private Future<Double> getPriceAsync(String product) {
        return CompletableFuture.supplyAsync(() -> getPrice(product));


//        CompletableFuture<Double> completableFuture = new CompletableFuture<>(); // 创建一个 CompletableFuture 对象，它会包含计算结果
//        new Thread( () -> { // 开启新的线程，进行异步计算
//            try {
//                double price = calculatePrice(product);
//                completableFuture.complete(price);  // 需要等待计算结果时，设置 Future 的返回值
//            }catch (Exception e){
//                completableFuture.completeExceptionally(e);
//            }
//        } ).start();
//        return completableFuture; // 无需等待，直接返回结果
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    private double calculatePrice(String product) {
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    /**
     * 人为延时
     */
    private void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 在 Java 8 之前使用 Future
     */
    @Test
    public void testFuture() {
        ExecutorService executorService = Executors.newCachedThreadPool(); // 创建 ExecutorService，通过它向线程中提交任务
        Future<Double> future = executorService.submit(new Callable<Double>() { // 向 Executors 里面提交一个 Callable 对象
            @Override
            public Double call() throws Exception {
                return null; // 异步操作的方式执行任务
            }
        });
        executorService.submit(() -> {
            return null;
        });
        // 异步操作的同事做其他事情
        try {
            Double result = future.get(1, TimeUnit.SECONDS); //获取异步操作的结果，如果最终被阻塞，无法得到结果，那么最多等待 1 秒后退出
        } catch (InterruptedException e) { //  计算抛出一个异常
            e.printStackTrace();
        } catch (ExecutionException e) { // 当前线程在等待过程中被中断
            e.printStackTrace();
        } catch (TimeoutException e) { // 在 Future 对象完成之前过期
            e.printStackTrace();
        }

    }

}
