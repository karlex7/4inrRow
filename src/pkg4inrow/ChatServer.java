package pkg4inrow;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Karlo
 */
public class ChatServer {
    ChatServiceImpl server;
    Registry registry;
    ChatService stub;
    List<String> messages;
    public ChatServer() throws RemoteException{
        start();
    }
    
    void start() throws RemoteException{
        server = new ChatServiceImpl("Server");
        registry = LocateRegistry.createRegistry(1099);
        // Registry registry = LocateRegistry.getRegistry();
        stub = (ChatService) UnicastRemoteObject.exportObject(server, 0);
        registry.rebind("server", stub);
        System.out.println("server ready, waiting for client...");
    }
    public void sendMessage(String msg) throws RemoteException{
        ChatService client;
            try {
                client = (ChatService) registry.lookup("client");
                if (client != null) {
                    client.send("\n"+msg);
                    messages=client.getAllMessages();
                }
            } catch (NotBoundException ex) {
                System.out.println("still no client...");
            } catch (AccessException ex) {
                Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public List<String> getAllMessages(){
        return messages;
    }
}
