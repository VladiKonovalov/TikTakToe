package org.example;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TikTacToeClient {
    public static void main(String[] args) throws IOException {
        try {
            String hostname = "localhost";
            int port=TikTacToeServer.port;
        boolean myTurn=true;
        System.out.println("connecting ..");
            Socket  connectionSock = new Socket(hostname, port);
            DataOutputStream serverOutput = new DataOutputStream(connectionSock.getOutputStream());
            TikTacToeListener listener=new TikTacToeListener(connectionSock);
            Thread thread=new Thread(listener);
            thread.start();
            Scanner input= new Scanner(System.in);
            while (serverOutput!=null){
                String move= input.nextLine();
                if(!myTurn){
                    System.out.println("waiting for oponent move ");
                }else  if (move.equals("0")|| move.equals("1`")|| move.equals("2")){
                       serverOutput.writeBytes(move+"\n");
                   }
                else if (move.equals("esc")) serverOutput.close();
                   else  System.out.println("invalid input or move  ");


            }

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }




    }
}
