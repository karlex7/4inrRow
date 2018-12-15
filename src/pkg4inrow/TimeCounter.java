/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4inrow;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;

/**
 *
 * @author karlex7
 */
public class TimeCounter implements Runnable {
    
    //Staviti printanje u fxmlCOntroler, a tu naparaviti event(bind) da salje kolko jos ima sekundi svaki put kad se updata
    
    public Thread t;
    public String processName;
    public int counter;
    public int TIME=10;
    public boolean moveRed;
    FXMLDocumentController con;
    public int i;
    
    public TimeCounter(String procName,FXMLDocumentController controller, boolean redMove){
        t=new Thread(this);
        this.processName=procName;
        counter=TIME;
        t.start();
        t.suspend();
        con=controller;
        moveRed=redMove;
    }
    //wait, notify -> na klik gumb, startati threadove na pocetku i pauzirati, kilati threadove na kraju

    @Override
    public void run() {
        try{
            for ( i = TIME; i >=0; i--) {
                System.out.println(processName+": "+i);
                counter=i;
                Platform.runLater(new Runnable() {
    @Override
    public void run() {
        if (moveRed) {
            con.setLabelTextRed(counter);
            if (counter==0) {
                    con.printWinner(2);
                    i=TIME;
                }
        }else{
            con.setLabelTextYellow(counter);
            if (counter==0) {
                    con.printWinner(1);
                    i=TIME;
                }
        }
        
    }
});
                
            Thread.sleep(1000);
            }
        }catch(Exception e){
            
        }
    }

   
    
    
}
    
