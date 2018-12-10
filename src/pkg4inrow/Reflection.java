/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4inrow;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
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
            System.out.println("\nConstructor name: "+ c.getName());
        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Method[] m=cls.getDeclaredMethods();
        System.out.println("\nImena metoda: ");
        for (Method method : m) {
            System.out.println(method.getName());
        }
        System.out.println("\nImena fieldova:");
        Field[] fields=cls.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }

    private void IspisRules() {
     Rules r=new Rules();
     
     System.out.println("-------------------------");
        Class cls = r.getClass();
        System.out.println("Class name: "+cls.getName());
        try {
            Constructor c=cls.getConstructor();
            System.out.println("\nConstructor name: "+ c.getName());
        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Method[] m=cls.getDeclaredMethods();
        System.out.println("\nImena metoda: ");
        for (Method method : m) {
            System.out.println(method.getName());
        }
        System.out.println("\nImena fieldova:");
        Field[] fields=cls.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }

    }
}
