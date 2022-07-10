package org.example;

import java.net.Socket;

public class TikTacToeListener implements Runnable{
    private Socket connectionSock=null ;

    TikTacToeListener(Socket sock){
this.connectionSock=sock;
    }

    @Override
    public void run() {
        try{

        }catch (Exception e){
            System.out.println("error:"+ e.toString());
        }
    }
}
