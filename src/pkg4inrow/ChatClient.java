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

/**
 *
 * @author Karlo
 */
public class ChatClient {
    ChatServiceImpl chat;
    Registry registry;
    ChatService stub;
    List<String> messages;
    public ChatClient() throws RemoteException{
        start();
    }
    
    void start() throws RemoteException{
        chat = new ChatServiceImpl("Klijent");
        registry = LocateRegistry.getRegistry();
        stub = (ChatService) UnicastRemoteObject.exportObject(chat, 0);
        registry.rebind("client", stub);
        System.out.println("client ready, searching for server...");
    }
    public void sendMessage(String msg) throws RemoteException{
        ChatService server;
            try {
                server = (ChatService) registry.lookup("server");
                if (server != null) {
                    server.send("\n"+msg);
                    //messages=new ArrayList();
                    messages=server.getAllMessages();
                }
            } catch (NotBoundException ex) {
                System.out.println("still no server...");
            } catch (AccessException ex) {
                ex.printStackTrace();
            }
    }
    public List<String> getAllMessages(){
        return messages;
    }
}
