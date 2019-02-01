package pkg4inrow;

import com.sun.corba.se.impl.io.InputStreamHook;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Karlo
 */

public class SocketServer{
    
    public SocketServer(FXMLDocumentController con) throws IOException, ClassNotFoundException{
        new SocketServerConnect(con).start();
    }
}

class SocketServerConnect extends Thread{
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket connection;
    FXMLDocumentController controller;
    
    public SocketServerConnect(FXMLDocumentController con) throws IOException, ClassNotFoundException{
        startRunning();
    }
    
    private void startRunning() throws IOException, ClassNotFoundException{
        server=new ServerSocket(12346);
        while (true) {
            waitForConnection();
            setUpStreams();
            whileChatting();
        }
    }

    private void waitForConnection() throws IOException {
        System.out.println("Waiting for someone to connect...");
        connection=server.accept();
        System.out.println("Now connected to "+connection.getInetAddress().getHostName());
    }

    private void setUpStreams() throws IOException {
        output= new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input=new ObjectInputStream(connection.getInputStream());
        System.out.println("Streams are setup!");
    }

    private void whileChatting() throws IOException, ClassNotFoundException {
        System.out.println("You are now connected!");
        //tu mi se treba ucitati broj
        int row=1;
        sendRow(row);
        do {
            //tu se treba staviti disk na grid
            row=(Integer)input.readObject();
            System.out.println("Recived from client\nRow: "+row);
        } while (true);
        
    }

    public void sendRow(int row) throws IOException {
        output.writeObject(row);
        output.flush();
        System.out.println("Server salje "+row);
    }
    
}
