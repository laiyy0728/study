package com.laiyy.mq.rocket.demo1.transaction;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author laiyy
 * @date 2018/8/7 9:40
 * @description 自定义事务监听器
 * <p>
 * 实现 TransactionListener 接口，executeLocalTransaction 方法用于在发送半消息成功时执行本地事务。
 * 返回值：
 * 1. TransactionStatus.CommitTransaction：提交事务，这意味着允许消费者使用此消息。
 * 2. TransactionStatus.RollbackTransaction：回滚事务，表示该消息将被删除而不允许使用。
 * 3. TransactionStatus.Unknown：中间状态，表示需要MQ检查以确定状态。
 * <p>
 * <p>
 * checkLocalTransaction 方法：用于检查本地事务状态并响应 MQ 检查请求，返回值与 executeLocalTransaction 相同
 */
public class TransactionListenerImpl implements TransactionListener {

    /**
     * 事务下标
     */
    private AtomicInteger transactionIndex = new AtomicInteger(0);

    /**
     * 保存消息的事务id、状态
     */
    private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        // 设置不同的消息类型（demo，没有任何意义）
        int value = transactionIndex.getAndIncrement();
        int status = value % 3;
        localTrans.put(msg.getTransactionId(), status);
        return LocalTransactionState.UNKNOW;
    }

    /**
     * 根据不同的状态，设置不同的提交状态
     * @param msg 消息
     * @return 消息状态
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        Integer status = localTrans.get(msg.getTransactionId());
        if (null != status) {
            // 根据不同的消息类型，设置不同的消息提交状态
            switch (status) {
                case 0:
                    return LocalTransactionState.UNKNOW;
                case 1:
                    return LocalTransactionState.COMMIT_MESSAGE;
                case 2:
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                default:
                    break;
            }
        }
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
