package pkg4inrow;

import javafx.application.Platform;

/**
 *
 * @author karlex7
 */
public class TimeCounter implements Runnable {
    public Thread t;
    public int counter;
    public int TIME=5;
    FXMLDocumentController con;
    boolean NotPause=false;
    
    public TimeCounter(FXMLDocumentController controller){
        t=new Thread(this);
        counter=TIME;
        t.setDaemon(true);
        t.start();
        con=controller;
    }
    @Override
    public void run() {
        try{
            while (counter>=-2) {
                //System.out.println("Time counter: "+counter);
                if (NotPause) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            con.setLabelTextRed(counter);
                            counter--;
                        }
                    });
                }
                
                Thread.sleep(1000);
                if (counter==0) {
                callWinner();
                NotPause=false;
                }
            }
        }catch(Exception e){
            
        }
    }
    
    void start(){
        NotPause=true;
    }
    void restart(){
        counter=TIME;
        start();
    }
    void stop(){
        NotPause=false;
    }
    
    void callWinner(){
        con.callWinner(); //call
    }
   
    
    
}
    
