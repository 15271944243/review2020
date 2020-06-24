package review.nio.blockmode;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
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

    private volatile boolean stop;

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
//            serverSocketChannel.configureBlocking(false);
            // 绑定地址
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
            // 把服务器通道注册到多路复用器上，并且监听阻塞事件
//            serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);

            System.out.println("Server start, port :" + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void run() {
//        while(!stop){
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();

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
                    System.out.println("server recive body:" + body);

                    String response = "currentTime=" + new Date().toString();
                    doWrite(socketChannel, response);
                }
                /*new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            } else if (readBytes < 0) {
                                // 关闭链路
//                                socketChannel.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });*/
            } catch (IOException e) {
                e.printStackTrace();
            }
//        }
      /*  if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
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
