/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4inrow;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author karlex7
 */
public class Reflection {
    
        public Reflection(){
            
        }
        public void ReflectionIspis(){
            IspisControllera();
            IspisRules();
        }

    private void IspisControllera() {
    FXMLDocumentController f=new FXMLDocumentController();
        
        System.out.println("-------------------------");
        Class cls = f.getClass();
        System.out.println("Class name: "+cls.getName());
        try {
            Constructor c=cls.getConstructor();
            System.out.println("Constructor name: "+ c.getName());
        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Method[] m=cls.getMethods();
        System.out.println("Imena metoda: ");
        for (Method method : m) {
            System.out.println(method.getName());
        }
        
    }

    private void IspisRules() {
     Rules r=new Rules();
     
     System.out.println("-------------------------");
        Class cls = r.getClass();
        System.out.println("Class name: "+cls.getName());
        try {
            Constructor c=cls.getConstructor();
            System.out.println("Constructor name: "+ c.getName());
        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Method[] m=cls.getMethods();
        System.out.println("Imena metoda: ");
        for (Method method : m) {
            System.out.println(method.getName());
        }
    }
}
