package org.example;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TicTacToe implements Runnable {
    private Thread thread;
    public int playerId;
    Utilities utilities;
    private Socket socket;
    private Socket[] sockets;

    private int[] spaces = new int[9];


    public TicTacToe(Socket socket, Socket[] sockets, Utilities game, int playerId) {
        this.socket = socket;
        this.sockets = sockets;
        this.playerId = playerId;
        this.utilities = game;
    }

    @Override
    public void run() {
        try {
            StartTheGame();
            BufferedReader playerInput = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

            switch (this.playerId) {
                case 1 -> Message("you are player X");
                case 2 -> Message("you are player 0");

            }
            while (this.utilities.checkWin() == 0) {
                Message(this.utilities.printBoard() );
                if (this.utilities.playerMove == this.playerId) {
                    Message(this.utilities.printBoard() );
                    Message("please enter a position you want to move to ");
                    String hismove=playerInput.readLine().trim();
                    if (!this.utilities.checkAndMove(Integer.parseInt(hismove)))
                        Message("invalid move ");
                      Message("-" + "\r\n");

                }else {
                    Message("wait for your turn ");
                    while (this.utilities.playerMove != this.playerId) {
                        Thread.sleep(500);
                    }
                    Message("-" + "\r\n");

                }
            }
            Message(this.utilities.printBoard());
            int result=this.utilities.checkWin();
            Message(Integer.toString(result) + "\r\n");
            if (result == this.playerId) {
                Message(" YOU WIN!" + "\r\n");
            } else if (result == 3) {
                Message(" Nobody win!" + "\r\n");
            } else {
                Message("s YOU LOSE!" + "\r\n");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


        private void Message (String message){
            try {
                DataOutputStream clientOutputStream = new DataOutputStream(this.socket.getOutputStream());
                clientOutputStream.writeBytes(message+"\n");

            } catch (IOException e) {
                System.out.println(e.getMessage());

            }
        }

public static void  StartTheGame(){
    System.out.println( "Let start the game:" );
    System.out.println( "   0| 1 | 2 " );
    System.out.println( "  --+--+---" );
    System.out.println( "   3 | 4 | 5" );
    System.out.println( "  --+--+---" );
    System.out.println( "   6 | 7 | 8 " );

}



}

