package org.example;

public class Utilities {


    private int[] spaces =new int[9];
    public volatile int playerMove;
    public  Utilities(){
        this.spaces=new int[9];
        this.playerMove=1;
    }
    public String printBoard(){
        String board="#";
        for (int i=1;i<9;i=i+3) {
            board += spaces[i] + "," + spaces[i+1] + "," + spaces[i+2] + ";";

        }
        return board;
    }
    public boolean checkAndMove(int i) {
        if (spaces[i] != 0) {
            return false;
        } else {
            this.spaces[i] = this.playerMove;

            return true;
        }
    }

    public int checkWin(){
        boolean freeSspace=true;
        for (int i=1;i<9;i=i+3) {
            if (spaces[i-1] == spaces[i] && spaces[i] == spaces[i+1] && spaces[i]!=0)
                return spaces[i];
        }
        for (int i=0;i<4;i=i++) {
            if (spaces[i+3] == spaces[i] && spaces[i] == spaces[i+6] && spaces[i]!=0)
                return spaces[i];}
        if (spaces[0] == spaces[3] && spaces[3] == spaces[8] && spaces[0]!=0)
            return spaces[0];
        if (spaces[2] == spaces[4] && spaces[2] == spaces[6] && spaces[2]!=0)
            return spaces[2];
        for (int i=0;i<9;i=i++) {
            if (spaces[i]==0) freeSspace=false;
        }
        if (freeSspace) return 3;
        return 0;}
}
