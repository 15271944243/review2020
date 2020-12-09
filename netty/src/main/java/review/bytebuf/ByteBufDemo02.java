package review.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.StandardCharsets;

/**
 * ByteBuf学习demo
 * @author: xiaoxiaoxiang
 * @date: 2020/12/8 08:49
 */
public class ByteBufDemo02 {

    /**
     * ByteBuf可以自动扩容, NIO的ByteBuffer不行
     * @param args
     */
    public static void main(String[] args) {
        // 使用Unpooled, 即非池化
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello world", StandardCharsets.UTF_8);
        // 判断当前byteBuf的数据是在堆上还是直接内存上
        if (byteBuf.hasArray()) {
            // hasArray() == true 表示是堆上的缓冲, 实际数据是存储在数组里
            byte[] content = byteBuf.array();
            System.out.println(new String(content, StandardCharsets.UTF_8));
            // UnpooledByteBufAllocator$InstrumentedUnpooledUnsafeHeapByteBuf(ridx: 0, widx: 11, cap: 64)
            // ridx: 读索引 widx: 写索引 cap: 容量
            System.out.println(byteBuf);
            // 读索引位置
            System.out.println(byteBuf.readerIndex());
            // 写索引位置
            System.out.println(byteBuf.writerIndex());
            // 容量
            System.out.println(byteBuf.capacity());
            // 数组偏移量
            System.out.println(byteBuf.arrayOffset());
            // 可读字节数
            int length = byteBuf.readableBytes();
            for (int i=0; i < length; i++) {
                // getByte不会移动readerIndex, 而readByte会移动readerIndex
//                System.out.println((char) byteBuf.readByte());
                System.out.println((char) byteBuf.getByte(i));
            }
            System.out.println(byteBuf.getCharSequence(0, 3, StandardCharsets.UTF_8));
        }
    }
}
