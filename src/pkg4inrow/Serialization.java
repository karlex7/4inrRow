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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author karlex7
 */
public class Serialization {
    private final String fileNameBoard="Board.dat";
    private final String fileNamePlayer="Player.dat";
    private final String fileNameScore="Score.dat";
            
    
    
    public void SaveBoard(int[][] board){
        
        FileOutputStream file;
        try {
            file = new FileOutputStream(fileNameBoard);
            ObjectOutputStream out=new ObjectOutputStream(file);
            out.writeObject(board);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int[][] LoadBoard(){
        
            int[][] ucitaniBoard=null;
            FileInputStream  file;
        try {
            file = new FileInputStream(fileNameBoard);
            ObjectInputStream in=new ObjectInputStream(file);
            ucitaniBoard = (int[][]) in.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
        }
            return ucitaniBoard;
    }
    
    public void SavePlayer(boolean RedOnMove){
        FileOutputStream file;
        try {
            file = new FileOutputStream(fileNamePlayer);
            ObjectOutputStream out=new ObjectOutputStream(file);
            out.writeObject(RedOnMove);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean LoadPlayer(){
        boolean redMove=true;
            FileInputStream  file;
        try {
            file = new FileInputStream(fileNamePlayer);
            ObjectInputStream in=new ObjectInputStream(file);
            redMove = (boolean) in.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
        }
            
          return redMove;
    }
    
    public void SaveScore(int redScore,int yellowScore){
        int[] score=new int[2];
        score[0]=redScore;
        score[1]=yellowScore;
        FileOutputStream file;
        try {
            file = new FileOutputStream(fileNameScore);
            ObjectOutputStream out=new ObjectOutputStream(file);
            out.writeObject(score);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int[] LoadScore(){
        int[] score=null;
            FileInputStream  file;
        try {
            file = new FileInputStream(fileNameScore);
            ObjectInputStream in=new ObjectInputStream(file);
            score = (int[]) in.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
        }
            return score;
    }
}
