/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4inrow;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author Jarvis
 */
public class Drawing {
        int ROWS;
        int COLUMNS;
        int TITLE_SIZE;
        GridPane grid;
    
        public Drawing(int ROWS,int COLUMNS,int TITLE_SIZE,GridPane grid){
            ROWS=this.ROWS;
            COLUMNS=this.COLUMNS;
            TITLE_SIZE=this.TITLE_SIZE;
            grid=this.grid;
        }
        
        GridPane Draw() {
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
         return grid;
     }
        void ResetBoard(int[] discGrid,int[][] trenutniGame) {
        grid.getChildren().clear();
        Draw();
        //Ciscenje polja za crtanje
        for (int i = 0; i < discGrid.length; i++) {
            discGrid[i]=5;
        }
        //ciscenje polja za prvikaz igre
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                trenutniGame[i][j]=0;
            }
        }
    }
        
}
