package review.nio;

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
        new Thread(new NioClient("127.0.0.1", 8765), "NioClient-002").start();
        new Thread(new NioClient("127.0.0.1", 8765), "NioClient-003").start();
    }

    public NioClient(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            this.selector = Selector.open();
            this.socketChannel = SocketChannel.open();
            // 设为非堵塞模式
            this.socketChannel.configureBlocking(false);
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
        while (!stop) {
            try {
                selector.select(1000L);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key = null;
                while(iterator.hasNext()) {
                    key = iterator.next();
                    iterator.remove();
                    try {
                        handleInput(key);
                    } catch (IOException e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
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
            socketChannel.register(selector, SelectionKey.OP_READ);
        } else {
            // 非阻塞模式下，connect操作会返回false
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }

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

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            // 判断是否连接成功
            SocketChannel sc = (SocketChannel) key.channel();
            if (key.isConnectable()) {
                if (sc.finishConnect()) {
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    doWrite(sc);
                } else {
                    System.exit(1);
                }
            }

            if (key.isReadable()) {
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
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
                    System.out.println("client recive body:" + body);
                    this.stop = true;
                } else if (readBytes < 0) {
                    // 关闭链路
                    key.cancel();
                    sc.close();
                }
            }
        }
    }
}
