/**
 * @author laiyy
 * @date 2018/6/7 11:27
 * @description
 *
 * 分隔符解码器、定长解码器
 *
 * TCP 以流的形式进行数据传输，上层协议为了对消息进行区分，采用以下四种方式：
 * 1、消息长度固定：累计读取到长度综合为定长 LEN 的报文后，就认为读取到了一个完整的消息；将计数器置位，重新读取下一个数据报
 * 2、将回车、换行符作为消息结束符，例如：FTP 协议，在文本协议中比较广泛
 * 3、将特殊的分隔符作为消息的结束位，回车、换行符就是一种特殊的结束分隔符
 * 4、通过在消息头中定义长度字段来标识消息总长度
 *
 *
 * Netty 基于以上四种方式，提供了四种解码器解决问题。 在 part3 中使用了 LineBasedFrameDecoder、StringDecoder
 * 在 part4 种将使用 DelimiterBasedFrameDecoder、FixedLengthFrameDecoder
 *
 * DelimiterBasedFrameDecoder：分隔符解码器
 * FixedLengthFrameDecoder：定长解码器（消息长度固定、在消息头中标识消息长度）
 *
 */
package laiyy.com.nio.netty.part4;