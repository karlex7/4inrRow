/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4inrow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Karlo
 */
public class Settings {
    
    public String UcitajSettings() throws FileNotFoundException{
		String naziv = ("settings.txt");
                String podatak="1";

		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.fscontext.RefFSContextFactory");
		env.put(Context.PROVIDER_URL, "file:C:/Users/Jarvis/Documents/College projects/4inrRow/Settings/");

		try {
                    System.out.println("1 jos ne");
			Context ctx = new InitialContext(env);
                        System.out.println("2 ni tu");
			Object obj = ctx.lookup(naziv);
                        System.out.println("3 ni tu");
			System.out.println(naziv + " je povezan sa : " + obj);
                        
                        
                        Scanner file=new Scanner(new File(obj.toString()));
                        podatak=file.nextLine();
                        System.out.println(podatak);
			ctx.close();
		} catch (NamingException e) {
			System.err.println("Ne postoji ime " + naziv + ": " + e);
		}
                return podatak;
    }
}
