package review.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;

public class NioServer implements Runnable {

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

//    private volatile boolean stop;

    public static void main(String[] args) {
        new Thread(new NioServer(8765), "NioServer-001").start();
    }

    public NioServer(int port) {
        try {
            // 打开路复用器
            selector = Selector.open();
            // 打开服务器通道
            serverSocketChannel = ServerSocketChannel.open();
            // 设置服务器通道为非阻塞模式
            serverSocketChannel.configureBlocking(false);
            // 绑定地址
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
            // 把服务器通道注册到多路复用器上，并且监听阻塞事件
            serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);

            System.out.println("Server start, port :" + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void run() {
        while(true){
            try {
                //1 必须要让多路复用器开始监听
                selector.select(1000L);
                //2 返回多路复用器已经选择的结果集
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                //3 进行遍历
                SelectionKey key = null;
                while(iterator.hasNext()) {
                    key = iterator.next();
                    iterator.remove();
                    try {
                        handleEvent(key);
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

//        if (selector != null) {
//            try {
//                selector.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    private void handleEvent(SelectionKey key) throws IOException {
        if (key.isValid()) {
            // 处理新接入的请求消息
            if (key.isAcceptable()) {
                handleAccept(key);
            }
            if (key.isReadable()) {
                handleRead(key);
            }
        }
    }

    private void handleAccept(SelectionKey key) throws IOException {
        System.out.println("connect accept,time" + System.currentTimeMillis());
        //1 获取服务通道
        ServerSocketChannel ssc =  (ServerSocketChannel) key.channel();
        //2
        SocketChannel sc = ssc.accept();
        //3 设置阻塞模式
        sc.configureBlocking(false);
        //4 注册到多路复用器上，并设置读取标识
        sc.register(this.selector, SelectionKey.OP_READ);
    }

    private void handleRead(SelectionKey key) throws IOException {
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
            doWrite(sc, response);
        } else if (readBytes < 0) {
            // 关闭链路
            key.cancel();
            sc.close();
        }
    }

    private void doWrite(SocketChannel socketChannel, String response) throws IOException {
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
