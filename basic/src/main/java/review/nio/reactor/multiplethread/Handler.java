package review.nio.reactor.multiplethread;

import review.juc.NamedThreadFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 在单reactor单线程的基础上增加了线程池处理事件,即单reactor多线程
 * @author: xiaoxiaoxiang
 * @date: 2020/6/24 14:49
 */
public class Handler implements EventHandler {

    /**
     * 定义线程池
     */
    private static ExecutorService executorService = new ThreadPoolExecutor(10, 10,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024), new NamedThreadFactory("nio-handle"),
            new ThreadPoolExecutor.AbortPolicy());


    @Override
    public void process(SelectionKey selectionKey) throws IOException {
        executorService.execute(() -> {
            try {
                process2(selectionKey);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void process2(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isReadable()) {
            processRead(selectionKey);
        }
    }

    private void processRead(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        // client端口后,会发送一条数据,但是readBytes的值是-1
        int readBytes = sc.read(readBuffer);
        if (readBytes > 0) {
            // 有数据则进行读取 读取之前需要进行复位方法(把position 和limit进行复位)
            readBuffer.flip();
            // 根据缓冲区的数据长度创建相应大小的byte数组，接收缓冲区的数据
            byte[] bytes = new byte[readBuffer.remaining()];
            // 接收缓冲区数据
            readBuffer.get(bytes);
            // 打印结果
            String body = new String(bytes).trim();
            System.out.println("server recive body:" + body);

            String response = "currentTime=" + new Date().toString();
            processWrite(sc, response);
        } else if (readBytes < 0) {
            // 关闭链路
            key.cancel();
            sc.close();
        }
    }

    /**
     * 由于是本地测试demo代码,所以针对写事件,并没有去注册OP_WRITE;
     * 在真实生产环境,还是需要注册OP_WRITE事件的,可以参考netty源码
     * @param socketChannel
     * @param response
     * @throws IOException
     */
    private void processWrite(SocketChannel socketChannel, String response) throws IOException {
        byte[] bytes = response.getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        socketChannel.write(writeBuffer);
        if (!writeBuffer.hasRemaining()) {
            System.out.println("response 2 client succeed:" + response);
        }
    }
}
