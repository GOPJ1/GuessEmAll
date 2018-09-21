package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public Server( int port ) throws IOException{
        try (ServerSocket serverSocket = new ServerSocket(port)){
            Socket socket = serverSocket.accept();
            DataOutputStream outputStream = new DataOutputStream( socket.getOutputStream() );
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        }catch (Exception e){
            System.out.println( "An error occurred when starting the server." );
        }
    }
}
