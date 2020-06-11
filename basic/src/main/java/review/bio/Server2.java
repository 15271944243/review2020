package review.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
    final static int PORT = 8765;

    /**
     * new Thread的方式
     * @param args
     */
    public static void main(String[] args) {
        ServerSocket server = null;
        try{
            server = new ServerSocket(PORT);
            System.out.println("server start .. ");
            while(true){
                final Socket socket = server.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        BufferedReader in = null;
                        PrintWriter out = null;
                        try {
                            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            out = new PrintWriter(socket.getOutputStream(), true);
                            String body = null;
                            while(true){
                                body = in.readLine();
                                if (body == null) {
                                    break;
                                }
                                System.out.println("Server :" + body);
                                out.println("服务器端回送响的应数据.");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if(in != null){
                                try {
                                    in.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if(out != null){
                                try {
                                    out.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            if(socket != null){
                                try {
                                    socket.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }).start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(server != null){
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            server = null;
        }
    }
}
