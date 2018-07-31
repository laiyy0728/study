package com.laiyy.kafka.producer.demo2;

import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.util.Map;

/**
 * @author laiyy
 * @date 2018/7/30 17:17
 * @description 表示自定义用户的 自定义序列化器
 */
public class CustomerSerializer implements Serializer<Customer> {

    @Override
    public void configure(Map<String, ?> map, boolean b) {
        // 不做任何配置
    }

    /**
     *
     * Customer 对象被序列化成：
     * 表示 customerID 的 4 字节整数
     * 表示 customerName 长度的 4 字节证书（如果 customerName 为空，则长度为 0）
     * 表示 customerName 的 N 个字节
     */
    @Override
    public byte[] serialize(String s, Customer customer) {
        try {
            byte[] serializedName;
            int stringSize;
            if (customer == null) {
                return null;
            } else {
                if (customer.getCustomerName() != null) {
                    serializedName = customer.getCustomerName().getBytes("UTF-8");
                    stringSize = serializedName.length;
                } else {
                    serializedName = new byte[0];
                    stringSize = 0;
                }
            }
            ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + stringSize);
            buffer.putInt(customer.getCustomerID());
            buffer.putInt(stringSize);
            buffer.put(serializedName);
            return buffer.array();
        } catch (Exception e) {
            throw new RuntimeException("出错了！！！");
        }
    }

    @Override
    public void close() {
        // 不需要关闭任何东西
    }
}
