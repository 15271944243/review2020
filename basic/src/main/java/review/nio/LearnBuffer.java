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

    public void test2() throws Exception {
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
