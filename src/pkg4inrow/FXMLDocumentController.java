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
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author Jarvis
 */
public class FXMLDocumentController implements Initializable {
    private static final int TITLE_SIZE=100;
    private static final int COLUMNS=7;
    private static final int ROWS=6;
    private static final int WIDTH=700;
    private static final int HEIGHT=700;
    private static boolean redMove=true;
    private int[] discGrid=new int[COLUMNS];//Tu stavljam kolko je popunjenih mjesta u redovima
    private int[][] trenutniGame=new int[COLUMNS][ROWS];//Prikaz trenutnog boarda sa bojama 1=RED, 2=YELLOW
    private static boolean winnerOnce=true;//Prikazuje mi winnera 2 puta, ovo je fix
    private static int rPoints=0;
    private static int yPoints=0;
    private final String fileName="Board.dat";
       
    @FXML
    private Pane pane;
    @FXML
    private GridPane grid;
    @FXML
    private Label redPoints;
    @FXML
    private Label yellowPoints;
    @FXML
    private Button saveBtnID;
    @FXML
    private Button loadBtnID;
    @FXML
    private Button resetBtnID;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SetPane();
        NapuniDiscGrid();
        Draw();
        pane.getChildren().addAll(makeColumns());
        
    }    
     private void Draw() {
         for (int y = 0; y < ROWS; y++) {
             for (int x = 0; x < COLUMNS; x++) {
                Shape shape=new Rectangle(TITLE_SIZE,TITLE_SIZE);
                Circle circle=new Circle((TITLE_SIZE/2)-5);
                circle.setCenterX(TITLE_SIZE/2);
                circle.setCenterY(TITLE_SIZE/2);
                shape=Shape.subtract(shape, circle);
                shape.setFill(Color.LIGHTBLUE);
                grid.add(shape, x, y);
             }
         }
         
     }
    
    private List<Rectangle> makeColumns(){
        List<Rectangle> list=new ArrayList<>();
        for (int x = 0; x < COLUMNS; x++) {
            Rectangle rect=new Rectangle(TITLE_SIZE,(ROWS)*TITLE_SIZE);
            rect.setTranslateX(x*(TITLE_SIZE));
            rect.setFill(Color.TRANSPARENT);
            
            rect.setOnMouseEntered(e->rect.setFill(Color.rgb(152, 251, 152,0.3)));
            rect.setOnMouseExited(e->rect.setFill(Color.TRANSPARENT));
            final int column=x;
            rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    placeDisc(column);
                    //checkIfWinner(column,discGrid[x]);
                }
            });
            list.add(rect);
        }
        
        return list;
    }

    private void SetPane() {
        pane.setPrefSize(WIDTH, HEIGHT);
        
    }

    private void placeDisc(int x) {
        if (discGrid[x]>=0) {
            if (redMove) {
            Circle circle=new Circle((TITLE_SIZE/2));
                circle.setCenterX(TITLE_SIZE/2);
                circle.setCenterY(TITLE_SIZE/2);
                circle.setFill(Color.RED);
                grid.add(circle,x,discGrid[x]);
                redMove=false;
                trenutniGame[x][discGrid[x]--]=1;
            }else{
                Circle circle=new Circle((TITLE_SIZE/2));
                circle.setCenterX(TITLE_SIZE/2);
                circle.setCenterY(TITLE_SIZE/2);
                circle.setFill(Color.YELLOW);
                grid.add(circle,x,discGrid[x]);
                redMove=true;
                trenutniGame[x][discGrid[x]--]=2;
                }
            
            checkIfWinner(x,discGrid[x]);
            
            }
        }

    private void NapuniDiscGrid() {
        for (int i = 0; i < discGrid.length; i++) {
            discGrid[i]=5;
        }
}

    private void checkIfWinner(int x, int y) {
        y++;
        System.out.println("MAIN");
        System.out.println(x);
        System.out.println(y);
        
        Rules r=new Rules(winnerOnce,rPoints,yPoints,redPoints,yellowPoints,trenutniGame,x,y);
        
        if (r.checkIfWinner()) {
            printWinner(trenutniGame[x][y]);
        }
        
        winnerOnce=true;
        
        
    }
   
    private void printWinner(int boja) {
        if (winnerOnce) {
            printBoard();
            ResetBoard();
        System.out.println("Winner");
        if (boja==1) {
        Alert alert = new Alert(AlertType.INFORMATION, "THE WINNER IS RED!", ButtonType.OK);
        alert.show();
        rPoints++;
        redPoints.setText(Integer.toString(rPoints));
        }else{
        Alert alert = new Alert(AlertType.INFORMATION, "THE WINNER IS YELLOW!", ButtonType.OK);
        alert.show();
        yPoints++;
        yellowPoints.setText(Integer.toString(yPoints));
        }
        winnerOnce=false;
        }
        
    }

    private void ResetBoard() {
        grid.getChildren().clear();
        Draw();
        NapuniDiscGrid();
        //ciscenje polja za prvikaz igre
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                trenutniGame[i][j]=0;
            }
        }
    }

    private void printBoard() {
        for (int i = 0; i < COLUMNS; i++) {
            for (int j = 0; j < ROWS; j++) {
                System.out.print(trenutniGame[i][j]);
            }
            System.out.println("");
        }
    }

    @FXML
    private void OnClickSave(ActionEvent event){
        //SERIJALIZACIJA
        try{
        FileOutputStream file=new FileOutputStream(fileName);
        ObjectOutputStream out=new ObjectOutputStream(file);
        out.writeObject(trenutniGame);
        printBoard();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void OnClickLoad(ActionEvent event) {
        //DESERIJALIZACIJA
        try{
            int[][] ucitaniBoard=null;
            FileInputStream  file=new FileInputStream(fileName);
            ObjectInputStream in=new ObjectInputStream(file);
            ucitaniBoard = (int[][]) in.readObject();
            trenutniGame=ucitaniBoard;
            UcitajBoardOdArray();
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onClickReset(ActionEvent event) {
        ResetBoard();
    }

    private void UcitajBoardOdArray() {
        for (int i = 0; i < COLUMNS; i++) {
            for (int j = ROWS-1; j >0; j--) {
                if (trenutniGame[i][j]==1) {
                    Circle circle=new Circle((TITLE_SIZE/2));
                    circle.setCenterX(TITLE_SIZE/2);
                    circle.setCenterY(TITLE_SIZE/2);
                    circle.setFill(Color.RED);
                    grid.add(circle,i,discGrid[i]);
                    discGrid[i]--;
                    
                }
                if (trenutniGame[i][j]==2) {
                    Circle circle=new Circle((TITLE_SIZE/2));
                    circle.setCenterX(TITLE_SIZE/2);
                    circle.setCenterY(TITLE_SIZE/2);
                    circle.setFill(Color.YELLOW);
                    grid.add(circle,i,discGrid[i]);
                    discGrid[i]--;
                }
            }
        }
        
    }
    
    }

    

   

   
    
    

