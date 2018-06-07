/**
 * @author laiyy
 * @date 2018/6/7 10:49
 * @description
 * 使用 Netty 的编解码器用于处理半包问题
 *
 * 结束符解码器：
 *
 * LineBasedFrameDecoder：
 * 遍历 ByteBuf 中的所有可读字节，判断是否否有 \n 或者 \r\n，如果有，以此位置为结束位，从可读索引到结束位区间的字节组成一行。
 * 以换行符为结束标志的解码器，支持携带结束符或不携带结束符两种解码方式，同时支持配置当行最大长度。
 * 如果连续读取到最大长度后仍然没有发现换行，就会抛出异常，同时忽略掉之前读到的异常码流。
 *
 *
 * StringDecoder：
 * 将接收到的对象转换为字符串，然后继续调用后面的 Handler。
 *
 * LineBasedFrameDecoder + StringDecoder 组合就是按行切换的文本解码器。
 *
 */
package laiyy.com.nio.netty.part3.test2;