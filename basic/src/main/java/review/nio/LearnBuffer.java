package review.nio;

import java.nio.IntBuffer;
import java.util.Random;

/**
 * 学习buffer的使用
 * @author: xiaoxiaoxiang
 * @date: 2020/5/31 21:30
 */
public class LearnBuffer {

    /**
     * 学习buffer的使用
     * @param args
     */
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);
        System.out.println("buffer capacity: " +buffer.capacity());
        System.out.println("buffer limit: " +buffer.limit());

        for (int i=0; i<5; i++) {
            buffer.put(new Random().nextInt(20));
        }
        System.out.println("before flip buffer position: " +buffer.position());
        System.out.println("before flip buffer limit: " +buffer.limit());
        buffer.flip();
        System.out.println("after flip buffer position: " +buffer.position());
        System.out.println("after flip buffer limit: " +buffer.limit());

        System.out.println("-------start loop-------");
        while(buffer.hasRemaining()) {
            System.out.println("buffer position: " + buffer.position());
            System.out.println("buffer limit: " + buffer.limit());
            System.out.println("buffer capacity: " + buffer.capacity());
            System.out.println(buffer.get());
        }
    }
}
