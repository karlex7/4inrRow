/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4inrow;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Karlo
 */
public class ChatServiceImpl implements ChatService {
    private String name;
    List<String> messages=new ArrayList();
    public ChatServiceImpl(String name) throws RemoteException{
        this.name=name;
    }
    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public void send(String message) throws RemoteException {
        String temp=name+": "+message;
        System.out.println(temp);
        messages.add(temp);
    }

    @Override
    public List<String> getAllMessages() throws RemoteException {
        return messages;
    }
    
}
