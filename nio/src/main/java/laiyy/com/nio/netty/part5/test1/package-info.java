/**
 * @author laiyy
 * @date 2018/6/7 15:16
 * @description 使用 JDK 的序列化
 *
 * 运行结果：JDK 自带序列化二进制大小为：125，byte array 二进制大小为 24
 *
 * 所以：JDK 序列化后的数据量过大。
 *
 * 在同等情况下，编码后的字节数组越大，存储的时候就越占用空间，存储蛋蛋硬件成本就越高，网络传输时占用更多的宽带，导致系统吞吐量降低。
 */
package laiyy.com.nio.netty.part5.test1;