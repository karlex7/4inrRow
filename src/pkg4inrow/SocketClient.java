package pkg4inrow;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Karlo
 */
public class SocketClient {
    public SocketClient(FXMLDocumentController con) throws IOException, ClassNotFoundException{
      new SocketClientConnection(con).start();
    }
}
class SocketClientConnection extends Thread{
    FXMLDocumentController controller;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Socket connection;
    int row;
    Thread t;
    public SocketClientConnection(FXMLDocumentController con) throws IOException, ClassNotFoundException{
        t=new Thread(this);
        controller=con;
        startRunning();
    }

    private void startRunning() throws IOException, ClassNotFoundException {
        connectToServer();
        setupStream();
        whileChatting();
    }

    private void connectToServer() throws IOException {
        System.out.println("Attempting connection...");
        connection=new Socket("localhost", 12346);
        System.out.println("Connected to "+connection.getInetAddress().getHostName());
    }

    private void setupStream() throws IOException {
        output=new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input=new ObjectInputStream(connection.getInputStream());
        System.out.println("Streams are setup!");
    }

    private void whileChatting() throws IOException, ClassNotFoundException {
        do {
            row=(Integer)input.readObject();
            System.out.println("recived from server\nRow "+row);
        } while (true);
        
    }
    public void sendRow(int row) throws IOException{
        output.writeObject(row);
        output.flush();
        System.out.println("Client salje "+row);
    }
    
}
