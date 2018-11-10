/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4inrow;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Jarvis
 */

public class Rules {
    int boja;
    boolean winnerOnce;
    int rPoints;
    int yPoints;
    Label redPoints;
    Label yellowPoints;
    int [][] trenutniGame;
    int x;
    int y;
    
    
    public Rules(boolean winnerOnce,int rPoints,int yPoints,Label redPoints,Label yellowPoints,int [][] trenutniGame,int X,int Y){
        this.winnerOnce=winnerOnce;
        rPoints=this.rPoints=rPoints;
        this.yPoints=yPoints;
        this.redPoints=redPoints;
        this.yellowPoints=yellowPoints;
        this.trenutniGame=trenutniGame;
        this.x=X;
        this.y=Y;
    }
    
    boolean checkIfWinner() {
        if (checkColumns(x,y)||checkRows(x,y)||checkDiagonalLeft(x,y)||checkDiagonalRight(x,y)) {
            return true;
        }
        winnerOnce=true;
        return false;
    }
   
    private boolean checkColumns(int x,int y) {
    //COLUMNS
        //X ostaje isti, Y se povecava
        boja=trenutniGame[x][y];
        int count=0;
        for (int i = 0; i < 6; i++) {
            if (trenutniGame[x][i]==boja) {
                count++;
                if (count==4) {
                    return true;
                }
            }
            else{
            count=0;
            }
        }
        return false;
    }

    private boolean checkRows(int x,int y) {
     //ROWS
        //Y ostaje isti, X se povecava
        boja=trenutniGame[x][y];
        int count=0;
        for (int i =0 ; i <7; i++) {
            if (trenutniGame[i][y]==boja) {
                count++;
                if (count==4) {
                    return true;
                }
            }
            else{
            count=0;
            }
        }
        return false;
    }

    private boolean checkDiagonalLeft(int x,int y) {
        //DIAGONAL LEFT
        //X se smanjuje za 1 a Y povecava
        boja=trenutniGame[x][y];
        int count=0;
        
        while (x!=0&&y!=0) {
            x++;
            y--;
        }
        while (x<=6 && y<=5 && x>=0 && y>=0) {
            if (trenutniGame[x][y]==boja) {
                    count++;
                    if (count==4) {
                        return true;
                    }
                }
            else{
                count=0;
                }
            //System.out.printf("LEFT DIAGONAL: %d, %d\n",x,y);
            x--;
            y++;
        }
        return false;
    }

    private boolean checkDiagonalRight(int x,int y) {
        boja=trenutniGame[x][y];
        int count=0;
        
        while (x!=0&&y!=0) {
            x--;
            y--;
        }
        while (x<=6 && y<=5) {
            if (trenutniGame[x][y]==boja) {
                    count++;
                    if (count==4) {
                        return true;
                    }
                }
            else{
                count=0;
                }
            x++;
            y++;
        }
        return false;
    }
    
    
}

