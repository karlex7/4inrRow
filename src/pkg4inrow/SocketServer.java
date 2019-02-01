package pkg4inrow;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Karlo
 */
public class SocketServer {
    ServerSocket serv;
    Socket socket;
    ObjectInputStream ois;
    FXMLDocumentController controller;
    
    public SocketServer(FXMLDocumentController con) throws IOException, ClassNotFoundException{
        controller=con;
        serv=new ServerSocket(12346);
        socket=serv.accept();
        ois=new ObjectInputStream(socket.getInputStream());
        System.out.println(ois.readObject());
    }
    
}
