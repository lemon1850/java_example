package tomcat;

import java.io.*;
import java.net.Socket;

/**
 * Created by tianhe on 2017/8/8.
 */
public class SocketTest {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("www.baidu.com", 80);
        OutputStream os= socket.getOutputStream();
        boolean autoflush = true;
        PrintWriter out = new PrintWriter(socket.getOutputStream(), autoflush);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out.println("GET / HTTP/1.1");
        out.println("HOST: www.baidu.com:80");
        out.println("Connection: CLose");
        out.println();


        boolean loop = true;
        StringBuffer sb = new StringBuffer(44096);
        while(loop){
            if(br.ready()){
                int i=0;
                while(i!=-1){
                    i = br.read();
                    sb.append((char)i);
                }
                loop = false;

            }
        }
        System.out.println(sb.toString());
        socket.close();
    }
}
