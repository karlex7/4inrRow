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
    boolean NotPause=false; //pazuza zbog prvog poteza
    
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
            while (true) {
                
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            con.setLabelTextRed(counter);
                            if (NotPause) {
                                counter--;
                            }
                        }
                    });
                Thread.sleep(1000);
            }
        }catch(Exception e){
            
        }
    }
    void reset(){
        counter=TIME;
    }
    
   
    
    
}
    
