package com.laiyy.mq.rocket.demo1.transaction;

import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author laiyy
 * @date 2018/8/7 9:38
 * @description 事务消息生产者
 * <p>
 * 使用TransactionMQProducer类创建生成器客户端，并指定唯一的producerGroup，
 * 并且可以设置自定义线程池来处理检查请求。执行本地事务后，
 * 需要根据执行结果回复MQ，回复状态：
 * （1）TransactionStatus.CommitTransaction：提交事务，这意味着允许消费者使用此消息。
 * （2）TransactionStatus.RollbackTransaction：回滚事务，表示该消息将被删除而不允许使用。
 * （3）TransactionStatus.Unknown：中间状态，表示需要MQ检查以确定状态
 */
public class TransactionProducer {

    public static void main(String[] args) throws Exception{
        // 创建一个事务监听器实例
        TransactionListenerImpl listener = new TransactionListenerImpl();
        // 创建一个事务消息生产者
        TransactionMQProducer producer = new TransactionMQProducer("test");
        // 创建一个线程池用于处理检查请求
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2000), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("client-transaction-msg-check-thread");
                return thread;
            }
        });

        // 设置线程池（4.3-Release 有问题，如果设置了自定义的线程池，会由于在 start 的时候，初始化不会自动初始化 checkRequestQueue，从而导致在 shutdown 的时候报 NPE)
        producer.setExecutorService(threadPool);
        // 设置事务监听器
        producer.setTransactionListener(listener);
        // 设置 name server
        producer.setNamesrvAddr("192.168.67.129:9876");
        // 启动生产者
        producer.start();

        String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int index = 0; index < 10; index++) {
            try {
                Message message = new Message("TestTopic", tags[index % tags.length], "KEY" + index, ("Hello World" + index).getBytes(RemotingHelper.DEFAULT_CHARSET));
                TransactionSendResult result = producer.sendMessageInTransaction(message, null);
                System.err.println(result);

                Thread.sleep(10);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        producer.shutdown();

    }

}
