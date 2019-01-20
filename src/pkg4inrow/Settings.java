/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4inrow;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

/**
 *
 * @author Karlo
 */
public class Settings {
    int FastGame=5;
    int StandardGame=10;
    int SlowGame=15;
    private final String settingsFolderPath="file:C:/Users/Karlo/Documents/VUA projects/4inrRow/Settings/";
    private List<String> listFiles=new ArrayList<String>();
    
    public Settings(){
        CheckIfSettingsExist();
    }
    
    public int GetFastTime(){
        return FastGame;
    }
    public int GetStandardTime(){
        return StandardGame;
    }
    public int GetSlowTime(){
        return SlowGame;
    }
    
    public void UpdateSettings(){
        SaveSettings();
        LoadSettings();
    }
    private void SaveSettings(){
        FileOutputStream fileFast;
        FileOutputStream fileStandard;
        FileOutputStream fileSlow;
        try {
            fileFast = new FileOutputStream(settingsFolderPath+listFiles.get(0));
            ObjectOutputStream outFast=new ObjectOutputStream(fileFast);
            outFast.writeObject(FastGame);
            
            fileStandard = new FileOutputStream(settingsFolderPath+listFiles.get(1));
            ObjectOutputStream outStandard=new ObjectOutputStream(fileStandard);
            outStandard.writeObject(StandardGame);
            
            fileSlow = new FileOutputStream(settingsFolderPath+listFiles.get(2));
            ObjectOutputStream outSlow=new ObjectOutputStream(fileSlow);
            outSlow.writeObject(SlowGame);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void LoadSettings() {
        int Fast;
        int Standard;
        int Slow;
        FileInputStream  fileFast;
        FileInputStream  fileStandard;
        FileInputStream  fileSlow;
        try {
            fileFast = new FileInputStream(settingsFolderPath+listFiles.get(0));
            ObjectInputStream inFast=new ObjectInputStream(fileFast);
            Fast = (int) inFast.readObject();
            
            fileStandard = new FileInputStream(settingsFolderPath+listFiles.get(1));
            ObjectInputStream inStandard=new ObjectInputStream(fileStandard);
            Standard = (int) inStandard.readObject();
            
            fileSlow = new FileInputStream(settingsFolderPath+listFiles.get(2));
            ObjectInputStream inSlow=new ObjectInputStream(fileSlow);
            Slow = (int) inSlow.readObject();
            
            FastGame=Fast;
            StandardGame=Standard;
            SlowGame=Slow;
            System.out.println("Fast: "+FastGame);
            System.out.println("Standard: "+StandardGame);
            System.out.println("Slow: "+SlowGame);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Sad tu dolje staviti jndi neku provjeru
    public void CheckIfSettingsExist(){
        Hashtable env = new Hashtable();env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.fscontext.RefFSContextFactory");
        env.put(Context.PROVIDER_URL, settingsFolderPath);
		try {
			Context ctxt = new InitialContext(env);
			NamingEnumeration flist = ctxt.listBindings("");
			while (flist.hasMore()) {
				Binding b = (Binding) flist.next();
                                listFiles.add(b.getName());
                                System.out.println(b.getName());
			}
		} catch (NamingException ne) {
			System.out.println(ne);
                        System.out.println("GRESKA");
		}
    }
}
