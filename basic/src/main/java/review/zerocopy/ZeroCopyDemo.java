package review.zerocopy;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * Java NIO FileChannel 类中的 transferTo() 方法底层就依赖了操作系统的sendfile()实现零拷贝
 * @author: xiaoxiaoxiang
 * @date: 2020/12/30 09:40
 */
public class ZeroCopyDemo {

    public static void main(String[] args) throws IOException {
        transferToTest();
        transferFromTest();
    }

    private static void transferToTest() throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("/Users/xiaoxiaoxiang/IdeaProjects/review2020/basic/src/main/java/review/zerocopy/from.txt", "rw");
        RandomAccessFile toFile = new RandomAccessFile("/Users/xiaoxiaoxiang/IdeaProjects/review2020/basic/src/main/java/review/zerocopy/to.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();
        FileChannel toChannel = toFile.getChannel();
        long position = 0;
        long size = fromChannel.size();
        fromChannel.transferTo(position, size, toChannel);
    }

    private static void transferFromTest() throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("/Users/xiaoxiaoxiang/IdeaProjects/review2020/basic/src/main/java/review/zerocopy/from2.txt", "rw");
        RandomAccessFile toFile = new RandomAccessFile("/Users/xiaoxiaoxiang/IdeaProjects/review2020/basic/src/main/java/review/zerocopy/to2.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();
        FileChannel toChannel = toFile.getChannel();
        long position = 0;
        long size = fromChannel.size();
        toChannel.transferFrom(fromChannel, 0, size);
        fromChannel.transferTo(position, size, toChannel);
    }



}
