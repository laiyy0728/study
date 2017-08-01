package future;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by laiyy
 * Date 2017/7/28.
 */
public class TestFuture {

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

    private Future<Double> getPriceAsync(String product){
        CompletableFuture<Double> completableFuture = new CompletableFuture<>(); // 创建一个 CompletableFuture 对象，它会包含计算结果
        new Thread( () -> { // 开启新的线程，进行异步计算
            double price = calculatePrice(product);
            completableFuture.complete(price);  // 需要等待计算结果时，设置 Future 的返回值
        } ).start();
        return completableFuture; // 无需等待，直接返回结果
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
    private void delay(){
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
        executorService.submit( () -> {
            return null;
        } );
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
