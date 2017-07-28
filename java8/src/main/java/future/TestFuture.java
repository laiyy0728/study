package future;

import org.junit.Test;

import java.util.concurrent.Callable;
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


    /**
     * 在 Java 8 之前使用 Future
     */
    @Test
    public void testFuture(){
        ExecutorService executorService = Executors.newCachedThreadPool(); // 创建 ExecutorService，通过它向线程中提交任务
        Future<Double> future = executorService.submit(new Callable<Double>() { // 向 Executors 里面提交一个 Callable 对象
            @Override
            public Double call() throws Exception {
                return null; // 异步操作的方式执行任务
            }
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
