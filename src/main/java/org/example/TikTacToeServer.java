package org.example;
import java.net.*;
import java.io.*;

public class TikTacToeServer {
    public static int port=6024;
    private Socket[] sockets;

    public static void main(String[] args)
    {
        TikTacToeServer server = new TikTacToeServer();
        server.getConnection();
    }
        private void getConnection()
        {
            try
            {			System.out.println("Waiting for connections.");

                ServerSocket serverSock = new ServerSocket(port);

                Utilities game = new Utilities();

                int playerID = 1;

                for (int i = 0; i < 2; ++i) {
                    Socket connectionSock = serverSock.accept();
                    this.sockets[i] = connectionSock;

                    System.out.println("Player " + Integer.toString(i+1) + " connected successfully.");

                    TicTacToe handler = new TicTacToe(connectionSock, this.sockets, game, playerID);
                  Thread theThread = new Thread(handler);
                    theThread.start();
                    playerID = 2;
                }

                System.out.println("Game starting ");

                Socket connectionSock = serverSock.accept();

                for (int i = 0; i < this.sockets.length; ++i){
                    sockets[i].close();
                }

            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
        }


    public TikTacToeServer()
    {
        sockets = new Socket[2];
    }

    }