package review.nio.blockmode;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioClient implements Runnable {

    private String host;

    private int port;

    private Selector selector;

    private SocketChannel socketChannel;

    private volatile boolean stop;

    public static void main(String[] args) {
        new Thread(new NioClient("127.0.0.1", 8765), "NioClient-001").start();
//        new Thread(new NioClient("127.0.0.1", 8765), "NioClient-002").start();
//        new Thread(new NioClient("127.0.0.1", 8765), "NioClient-003").start();
    }

    public NioClient(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            this.selector = Selector.open();
            this.socketChannel = SocketChannel.open();
            // 设为非堵塞模式
//            this.socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void run() {
        try {
            doConnect();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        if (socketChannel != null) {
            try {
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void doConnect() throws IOException {
        InetSocketAddress address = new InetSocketAddress(this.host, this.port);
        boolean connect = socketChannel.connect(address);
        System.out.println("connect:" + connect + ",time" + System.currentTimeMillis());
        if (connect) {
//            socketChannel.register(selector, SelectionKey.OP_READ);
        } else {
            // 非阻塞模式下，connect操作会返回false
//            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }

        doWrite(socketChannel);

        doRead(socketChannel);
    }

    /**
     * http://blog.sina.com.cn/s/blog_783ede0301013g5n.html
     * 参考上面对文档,如果要在生产使用java原生NIO(本人都是用对Netty),最好注册SelectionKey.OP_WRITE事件
     * @param socketChannel
     * @throws IOException
     */
    private void doWrite(SocketChannel socketChannel) throws IOException {
        byte[] req = "query time order".getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        writeBuffer.put(req);
        writeBuffer.flip();
        socketChannel.write(writeBuffer);
        if (!writeBuffer.hasRemaining()) {
            System.out.println("Send order 2 server succeed");
        }
    }

    private void doRead(SocketChannel socketChannel) throws IOException {
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        int readBytes = socketChannel.read(readBuffer);
        if (readBytes > 0) {
            // 有数据则进行读取 读取之前需要进行复位方法(把position 和limit进行复位)
            readBuffer.flip();
            // 根据缓冲区的数据长度创建相应大小的byte数组，接收缓冲区的数据
            byte[] bytes = new byte[readBuffer.remaining()];
            // 接收缓冲区数据
            readBuffer.get(bytes);
            // 打印结果
            String body = new String(bytes).trim();
            System.out.println("client recive body:" + body);
            this.stop = true;
        } else if (readBytes < 0) {
            // 关闭链路
        }
    }
}
