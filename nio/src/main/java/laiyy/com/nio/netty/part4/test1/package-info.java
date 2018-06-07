/**
 * @author laiyy
 * @date 2018/6/7 11:37
 * @description
 *
 * 分隔符解码器，使用 $_ 座椅分隔符
 *
 * 运行结果：server 和 client 各打印了 10 次接收到的消息，由此可以得出，DelimiterBasedFrameDecoder 确实可以实现使用某字符、字符串作为码流结束标识进行解码
 *
 * 在将 DelimiterBasedFrameDecoder 去掉后，就会发生粘包：
 * 由于没有分隔符解码器，导致服务端一次性读取了客户端的所有信息；
 * 而客户端有分隔符解码器，所以会将服务器的响应解码为 N 个响应，并依次输出
 *
 */
package laiyy.com.nio.netty.part4.test1;