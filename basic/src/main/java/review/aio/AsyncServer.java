package review.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

public class AsyncServer implements Runnable {

    private int port;

    CountDownLatch latch;

    AsynchronousServerSocketChannel serverSocketChannel;

    public AsyncServer(int port) {
        this.port = port;
        try {
            serverSocketChannel = AsynchronousServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));

            System.out.println("Server start, port :" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AsyncServer asyncServer = new AsyncServer(8765);
        new Thread(asyncServer, "AioServer-001").start();
    }

    @Override
    public void run() {
        latch = new CountDownLatch(1);
        doAccept();
        try {
            // 让线程阻塞,防止服务端执行完成后退出
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doAccept() {
        serverSocketChannel.accept(this, new AcceptCompletionHandler());
    }
}
