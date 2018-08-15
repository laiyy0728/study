package com.laiyy.elsearch.simple.demo1;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

/**
 * @author laiyy
 * @date 2018/8/14 14:53
 * @description transport 客户端具有集群嗅探功能，允许动态添加新主机并删除旧主机。
 * 启用嗅探后，transport 客户端将连接到期内部节点列表中的节点，该列表是通过调用构建的 addTransportAddress 实现。
 * 此后，客户端将在这些节点上调用内部集群状态 API 以发现可用的数据节点。
 * 客户端内部节点列表将仅替换为这些数据节点。
 * 默认情况下，该节点列表每 5 秒刷新一次。
 * 注意：嗅探器连接的 ip 地址是在那些节点的 elasticsearch 配置中声明为 publish 地址的 ip 地址
 * <p>
 * 如果该节点不是数据节点，则列表可能不包括它所连接的原始节点。例如：如果最初连接到主节点后，则在嗅探之后，
 * 没有其他请求将转到该主节点，而是转到任何数据节点。transport 客户端排出非数据节点的原因是避免将所有浏览量发送到主节点。
 * <p>
 * transport 客户端级别设置包括：
 * client.transport.ignore_cluster_name：设置为 true，忽略已连接节点的集群名称验证。
 * client.transport.ping_timeout：等待节点 ping 响应的时间。默认为 5 秒
 * client.transport.nodes_sampler_interval：对列出和链接的节点进行采样/ping 的频率。默认 5 秒
 */
public class Demo1 {

    /**
     * 设置 elasticsearch ip
     */
    private static final String HOST = "192.168.67.129";

    /**
     * 使用 API 访问的端口为 9300，使用 HTTP 访问的端口为 9200
     */
    private static final int PORT = 9300;

    public static void main(String[] args) throws Exception {
        // 根据 HOST、POST 获取地址
        TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(HOST), PORT);
        // 采用空设置 elasticsearch（即默认设置）

        // 设置 cluster.name
        Settings settings = Settings.builder().put("cluster.name", "cluster")
                // 启用嗅探
                .put("client.transport.sniff", true)
                .build();

        TransportClient client = new PreBuiltTransportClient(settings).addTransportAddress(transportAddress);

    }

}
