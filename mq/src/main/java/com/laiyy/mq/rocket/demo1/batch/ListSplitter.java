package com.laiyy.mq.rocket.demo1.batch;

import org.apache.rocketmq.common.message.Message;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author laiyy
 * @date 2018/8/6 9:30
 * @description
 */
public class ListSplitter implements Iterator<List<Message>> {

    /**
     * 消息分割大小
     */
    private final int SIZE_LIMIT = 1000 * 1000;

    private final List<Message> messages;

    private int currIndex;

    public ListSplitter(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean hasNext() {
        return currIndex < messages.size();
    }

    /**
     * 每次分割 1000*1000（即 1MB）的消息，批量发送到服务器。不足大小的话全部发送
     * @return 需要发送的消息
     */
    @Override
    public List<Message> next() {
        int nextIndex = currIndex;
        int totalSize = 0;

        for (; nextIndex < messages.size(); nextIndex++) {
            // 根据下标获取消息
            Message message = messages.get(nextIndex);
            // 获取消息的 topic、body 大小
            int tmpSize = message.getTopic().length() + message.getBody().length;
            // 获取消息配置，累加消息 key、value 的大小
            Map<String, String> properties = message.getProperties();
            for (Map.Entry<String, String> entry : properties.entrySet()) {
                tmpSize += entry.getKey().length() + entry.getValue().length();
            }
            tmpSize = tmpSize+ 20;
            if (tmpSize > SIZE_LIMIT) {
                if (nextIndex - currIndex == 0) {
                    nextIndex++;
                }
                break;
            }
            if (tmpSize + totalSize > SIZE_LIMIT) {
                break;
            } else {
                totalSize += tmpSize;
            }
        }
        List<Message> subList = this.messages.subList(currIndex, nextIndex);
        currIndex = nextIndex;
        return subList;
    }
}
