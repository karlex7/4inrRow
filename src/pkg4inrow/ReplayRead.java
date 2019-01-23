package pkg4inrow;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;

/**
 *
 * @author Jarvis
 */
public class ReplayRead implements Runnable{
    String fileName="replay.xml";
    List<Integer> lista=new ArrayList();
    FXMLDocumentController con;
    public Thread t;
    int i;
    
    
    
    public ReplayRead(List<String> listaStringova, FXMLDocumentController Con){
        for (int i = 0; i < listaStringova.size(); i++) {
            String s=listaStringova.get(i);
            int j=Integer.parseInt(s);
            lista.add(j);
        }
        t=new Thread(this);
        t.setDaemon(true);
        con=Con;
        t.start();
        start();//ucitavanje xml
    }

    ReplayRead() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void start(){
        
    }

    @Override
    public void run() {
        i=0;
        try{
            while (i<lista.size()) {
                Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            con.placeDisc(lista.get(i));
                            i++;
                        }
                    });
                Thread.sleep(1000);
            }
            for (int i = 0; i < lista.size(); i++) {
                
                
            }
        }catch(Exception e){
            
        }
    }
    
    

    

}
