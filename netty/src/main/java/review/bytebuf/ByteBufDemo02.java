package review.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;

import java.nio.charset.StandardCharsets;

/**
 * ByteBuf学习demo
 * @author: xiaoxiaoxiang
 * @date: 2020/12/8 08:49
 */
public class ByteBufDemo02 {

    /**
     * 建议阅读ByteBuf java doc
     * ByteBuf可以自动扩容, NIO的ByteBuffer不行
     * - readerIndex() 返回的是当前的读指针的 readerIndex 位置, writeIndex() 返回的当前写指针 writeIndex 位置
     * - markReaderIndex() 用于保存 readerIndex 的位置，resetReaderIndex() 则将当前 readerIndex 重置为之前保存的位置
     * - isReadable() 用于判断 ByteBuf 是否可读，如果 writerIndex 大于 readerIndex，那么 ByteBuf 是可读的，否则是不可读状态
     * - readableBytes() 可以获取 ByteBuf 当前可读取的字节数，可以通过 writerIndex - readerIndex 计算得到
     * - readBytes(byte[] dst) & writeBytes(byte[] src) & readByte() & writeByte(int value) 方法
     * 在读写时会改变readerIndex 和 writerIndex 指针
     * 而 getByte(int index) & setByte(int index, int value) 方法则不会改变指针位置
     * - release() 引用计数减 1, retain() 引用计数加 1
     * - slice() & duplicate() 底层分配的内存、引用计数都与原始的 ByteBuf 共享; 对它分配出来的 ByteBuf 改动会影响到原始的 ByteBuf 底层数据
     * - copy() 会从原始的 ByteBuf 中拷贝所有信息，所有数据都是独立的，向 copy() 分配的 ByteBuf 中写数据不会影响原始的 ByteBuf
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

//    public static void main(String[] args) {
//        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(6, 10);
//        printByteBufInfo("ByteBufAllocator.buffer(5, 10)", buffer);
//        buffer.writeBytes(new byte[]{1, 2});
//        printByteBufInfo("write 2 Bytes", buffer);
//        buffer.writeInt(100);
//        printByteBufInfo("write Int 100", buffer);
//        buffer.writeBytes(new byte[]{3, 4, 5});
//        printByteBufInfo("write 3 Bytes", buffer);
//        byte[] read = new byte[buffer.readableBytes()];
//        buffer.readBytes(read);
//        printByteBufInfo("readBytes(" + buffer.readableBytes() + ")", buffer);
//        printByteBufInfo("BeforeGetAndSet", buffer);
//        System.out.println("getInt(2): " + buffer.getInt(2));
//        buffer.setByte(1, 0);
//        System.out.println("getByte(1): " + buffer.getByte(1));
//        printByteBufInfo("AfterGetAndSet", buffer);
//    }
//    private static void printByteBufInfo(String step, ByteBuf buffer) {
//        System.out.println("------" + step + "-----");
//        System.out.println("readerIndex(): " + buffer.readerIndex());
//        System.out.println("writerIndex(): " + buffer.writerIndex());
//        System.out.println("isReadable(): " + buffer.isReadable());
//        System.out.println("isWritable(): " + buffer.isWritable());
//        System.out.println("readableBytes(): " + buffer.readableBytes());
//        System.out.println("writableBytes(): " + buffer.writableBytes());
//        System.out.println("maxWritableBytes(): " + buffer.maxWritableBytes());
//        System.out.println("capacity(): " + buffer.capacity());
//        System.out.println("maxCapacity(): " + buffer.maxCapacity());
//    }
}
