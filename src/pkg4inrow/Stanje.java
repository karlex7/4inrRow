/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4inrow;


public class Stanje {
        int [][] board;
        boolean player;
        int score1, score2;
        
        public Stanje(int [][] Board,boolean Player,int Score1, int Score2){
            board=Board;
            player=Player;
            score1=Score1;
            score2=Score2;
        }
        public Stanje(){
            
        }
        public void save(Stanje stanje) {
            Serialization s=new Serialization();
        s.SaveBoard(stanje.board);
        s.SavePlayer(stanje.player);
        s.SaveScore(stanje.score1,stanje.score2);
    }
        public Stanje load() {
        Serialization sa=new Serialization();
        Stanje s=new Stanje();
        
        s.board=sa.LoadBoard();
        s.player=sa.LoadPlayer();
        int[] score=sa.LoadScore();
        s.score1=score[0];
        s.score2=score[1];
        return s;
    }
}
