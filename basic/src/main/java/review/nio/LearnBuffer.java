package review.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
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
        learnBuffer2();
    }

    /**
     * buffer基础用法
     */
    private static void learnBuffer() {
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

    /**
     * 增加clear()
     */
    private static void learnBuffer2() {
        IntBuffer buffer = IntBuffer.allocate(10);
        System.out.println("buffer capacity: " +buffer.capacity());
        System.out.println("buffer limit: " +buffer.limit());

        for (int i=0; i<8; i++) {
            buffer.put(i);
        }
        System.out.println("before flip buffer position: " +buffer.position());
        System.out.println("before flip buffer limit: " +buffer.limit());
        buffer.clear();
        System.out.println("after clear buffer position: " +buffer.position());
        System.out.println("after clear buffer limit: " +buffer.limit());

        for (int i=10; i<60; i=i+10) {
            buffer.put(i);
        }

        buffer.flip();

        System.out.println("-------start loop-------");
        while(buffer.hasRemaining()) {
            System.out.println("buffer position: " + buffer.position());
            System.out.println("buffer limit: " + buffer.limit());
            System.out.println("buffer capacity: " + buffer.capacity());
            System.out.println(buffer.get());
        }
    }

    /**
     * channel 读写
     * @throws Exception
     */
    public void learnBuffer3() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("input.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("output.txt");
        FileChannel inputChannel = fileInputStream.getChannel();
        FileChannel outputChannel = fileOutputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);
        while (true) {
            buffer.clear();
            int read = inputChannel.read(buffer);
            System.out.println("read:" + read);
            if (read < 0) {
                break;
            }
            buffer.flip();
            outputChannel.write(buffer);
        }
        inputChannel.close();
        outputChannel.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
