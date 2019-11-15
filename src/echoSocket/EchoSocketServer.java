package echoSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoSocketServer {

    private final ServerSocket mServerSocket;

    public EchoSocketServer(int port) throws IOException {
        // 1. 创建一个 ServerSocket 并监听端口 port
        mServerSocket = new ServerSocket(port);
    }

    public void run() throws IOException {
        // 2. 开始接受客户连接
        Socket client = mServerSocket.accept();
        handleClient(client);
    }

    private void handleClient(Socket socket) throws IOException {
        // 3. 使用 socket 进行通信 ...
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        byte[] buffer = new byte[1024];
        int n;
        StringBuffer sb = new StringBuffer();
        while ((n = in.read(buffer)) > 0) {
            sb.append(new String(buffer));
            out.write(sb.toString().getBytes());
            System.out.println("接收内容：" + sb.toString());
            sb = new StringBuffer();
        }
    }


    public static void main(String[] argv) {
        try {
            EchoSocketServer server = new EchoSocketServer(9877);
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
