package review.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;


/**
 * ByteBuf学习demo
 * @author: xiaoxiaoxiang
 * @date: 2020/12/8 08:49
 */
public class ByteBufDemo03 {

    /**
     * ByteBuf提供的3种缓冲区类型:
     * heapBuffer
     * directBuffer
     * compositeBuffer
     * @param args
     */
    public static void main(String[] args) {
        // 使用Unpooled, 即非池化
        CompositeByteBuf compositeBuffer = Unpooled.compositeBuffer();
        ByteBuf HeapBuffer = Unpooled.buffer(10);
        ByteBuf directBuffer = Unpooled.directBuffer(10);
    }
}
