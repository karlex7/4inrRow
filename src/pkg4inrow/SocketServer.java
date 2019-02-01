package pkg4inrow;

import com.sun.corba.se.impl.io.InputStreamHook;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author Karlo
 */

public class SocketServer implements Runnable{
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket connection;
    FXMLDocumentController controller;
    Thread t;
    int row;
    
    
    public SocketServer(FXMLDocumentController con) throws IOException, ClassNotFoundException{
        controller=con;
        t=new Thread(this);
        t.setDaemon(true);
        t.start();
    }
    
    @Override
    public void run() {
        try {
            System.out.println("Start");
            startRunning();
        } catch (IOException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        //int row;
        //sendRow(row);
        do {
            //tu se treba staviti disk na grid
            row=(Integer)input.readObject();
            Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                controller.placeDisc(row);
                            } catch (IOException ex) {
                                Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
            
            System.out.println("Recived from client\nRow: "+row);
        } while (true);
        
    }

    public void sendRow(int row) throws IOException {
        output.writeObject(row);
        output.flush();
        System.out.println("Server salje "+row);
    }

    
    
}
