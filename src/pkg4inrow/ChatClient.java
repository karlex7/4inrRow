package pkg4inrow;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

/**
 *
 * @author Karlo
 */
public class ChatClient {
    ChatServiceImpl chat;
    Registry registry;
    ChatService stub;
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
                    server.send("hello from client on : " + new Date()+"\n"+msg);
                }
            } catch (NotBoundException ex) {
                System.out.println("still no server...");
            } catch (AccessException ex) {
                ex.printStackTrace();
            }
    }
}
