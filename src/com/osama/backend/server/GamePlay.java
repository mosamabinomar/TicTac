package com.osama.backend.server;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by osama on 5/25/16.
 */
public class GamePlay {
    Socket player1;
    Socket player2;
    private DataOutputStream bw1;
    private DataOutputStream bw2;
    public GamePlay(Socket player1,Socket player2){
        this.player1=player1;
        this.player2=player2;
        try{
           bw1=new DataOutputStream(player1.getOutputStream());
            bw2=new DataOutputStream(player2.getOutputStream());
            bw2.writeUTF("match");
            bw1.writeUTF("match");
            System.out.println("It wents smoothly");

        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public void move(int box,Socket player){
            try {
                if(player==player1) {
                    System.out.println("Carry on ");

                    bw2.writeUTF("move"+box);
                }
                else if(player==player2){
                    bw1.writeUTF("move"+box);
                    System.out.println("Carry on 2");

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
    public void wins(Socket player,int winSum){
        try{
            if(player==player1){
                bw2.writeUTF("wins"+winSum);
            }
            else if(player==player2){
                bw1.writeUTF("wins"+winSum);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
