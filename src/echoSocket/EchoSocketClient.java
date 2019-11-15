package echoSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EchoSocketClient {

    private final Socket mSocket;

    public EchoSocketClient(String host, int port) throws IOException {
        // 创建 socket 并连接服务器
        mSocket = new Socket(host, port);
    }

    public void run() throws IOException {
        // 和服务端进行通信
        Thread readerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    readResponse();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        readerThread.start();

        OutputStream out = mSocket.getOutputStream();
        byte[] buffer = new byte[1024];
        int n;
        while ((n = System.in.read(buffer)) > 0) {
            out.write(buffer, 0, n);
        }
    }

    private void readResponse() throws IOException {
        InputStream in = null;
        try {
            in = mSocket.getInputStream();
            byte[] buffer = new byte[1024];
            int n;
            while ((n = in.read(buffer)) > 0) {
                System.out.write(buffer, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
    }


    public static void main(String[] argv) {
        try {
            // 由于服务端运行在同一主机，这里我们使用 localhost
            EchoSocketClient client = new EchoSocketClient("localhost", 9877);
            client.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
