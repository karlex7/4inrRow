package pkg4inrow;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Karlo
 */
public class SocketClient {
    Socket clientSocket;
    ObjectOutputStream oos;
    FXMLDocumentController controller;
    public SocketClient(FXMLDocumentController con) throws IOException{
        controller=con;
        clientSocket=new Socket("localhost", 12346);
        oos=new ObjectOutputStream(clientSocket.getOutputStream());
    }
    
    public void sendRowFromClient(int row) throws IOException{
        oos.writeObject(row);
        oos.flush();
    }
}
