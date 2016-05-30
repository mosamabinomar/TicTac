package com.osama.backend.Interface;

import com.osama.frontend.GameViewController;
import javafx.scene.canvas.Canvas;
import org.jetbrains.annotations.Contract;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by osama on 5/13/16.
 * Controls the interface explicitly.
 */
public class UIController {
    private int[] clickedBoxes = new int[9];
    private static final int Box1X = 40;
    private static final int Box1Y = 40;
    private static final int Box2Y = 130;
    private static final int Box3Y = 190;
    private static final int Box2X = 130;
    private static final int Box3X = 190;
    private int player = 1;
    private ServerConnector server;
    private boolean notWinner=true;
    private Drawer drawer;
    private int index = 0;
    private GameViewController manageInterface;
    private Boolean gameStatus;

    public void setGameStatus(boolean gameStatus) {
        this.gameStatus=gameStatus;
        check();
    }

    public void setPlayer(int player) {
        this.player = player;
        manageInterface.setPlayerID(player);

    }

    private void check() {
        System.out.println("This method called and game status is"+gameStatus);
        if(gameStatus){
            manageInterface.setEnable();
        }
    }

    public UIController(GameViewController a){
        gameStatus=false;
        manageInterface=a;
        server=new ServerConnector();
        server.createConnection();
        server.setaController(this);
        server.arrangeMatch();
        drawer=new Drawer();

    }

    public boolean determineMove(int clickedX,int clickedY){
        boolean status=false;
        if (index <= clickedBoxes.length && notWinner) {
            manageInterface.setClickable(false);
            if (clickedX < Box1X && clickedY < Box1Y) {
                if (!isClicked(-1)) {    //because by default array is initialized to 0 so can't use zero
                    clickedBoxes[index++] = -1;
                    drawer.drawImage(0, player);
                    server.drawMove(0);
                    if(Winner.isWinner(0,player)){
                        drawer.drawWinLine(Winner.getWinBoxes());
                        server.wins(Winner.getWinBoxes());
                        status=true;

                    }
                }
            } else if (clickedX < Box2X && clickedY < Box1Y) {
                if (!isClicked(1)) {
                    clickedBoxes[index++] = 1;
                    drawer.drawImage(1, player);
                    server.drawMove(1);

                    if (Winner.isWinner(1, player)) {
                        drawer.drawWinLine(Winner.getWinBoxes());
                        server.wins(Winner.getWinBoxes());
                        status=true;

                    }
                }
            }else if (clickedX < Box3X && clickedY < Box1Y) {
                if (!isClicked(2)) {
                    clickedBoxes[index++] = 2;
                    drawer.drawImage(2, player);
                    server.drawMove(2);
                    if(Winner.isWinner(2,player)){
                        drawer.drawWinLine(Winner.getWinBoxes());
                        server.wins(Winner.getWinBoxes());
                        status=true;

                    }

                }
            } else if (clickedX < Box1X && clickedY < Box2Y) {
                if (!isClicked(3)) {
                    clickedBoxes[index++] = 3;
                    server.drawMove(3);
                    drawer.drawImage(3, player);
                    if(Winner.isWinner(3,player)){
                        drawer.drawWinLine(Winner.getWinBoxes());
                        server.wins(Winner.getWinBoxes());
                        status=true;

                    }

                }
            } else if (clickedX < Box2X && clickedY < Box2Y) {
                if (!isClicked(4)) {
                    clickedBoxes[index++] = 4;
                    server.drawMove(4);
                    drawer.drawImage(4, player);
                    if(Winner.isWinner(4,player)){
                        drawer.drawWinLine(Winner.getWinBoxes());
                        server.wins(Winner.getWinBoxes());
                        status=true;

                    }


                }
            } else if (clickedX < Box3X && clickedY < Box2Y) {
                if (!isClicked(5)) {
                    clickedBoxes[index++] = 5;
                    server.drawMove(5);
                    drawer.drawImage(5, player);
                    if(Winner.isWinner(5,player)){
                        drawer.drawWinLine(Winner.getWinBoxes());
                        server.wins(Winner.getWinBoxes());
                        status=true;

                    }

                }
            } else if (clickedX < Box1X && clickedY < Box3Y) {
                if (!isClicked(6)) {
                    clickedBoxes[index++] = 6;
                    server.drawMove(6);
                    drawer.drawImage(6, player);
                    if(Winner.isWinner(6,player)){
                        drawer.drawWinLine(Winner.getWinBoxes());
                        server.wins(Winner.getWinBoxes());
                        status=true;

                    }

                }

            } else if (clickedX < Box2X && clickedY < Box3Y) {
                if (!isClicked(7)) {
                    clickedBoxes[index++] = 7;
                    server.drawMove(7);
                    drawer.drawImage(7, player);
                    if(Winner.isWinner(7,player)){
                        drawer.drawWinLine(Winner.getWinBoxes());
                        server.wins(Winner.getWinBoxes());
                        status=true;

                    }

                }
            } else if (clickedX < Box3X && clickedY < Box3Y) {
                if (!isClicked(8)) {
                    clickedBoxes[index++] = 8;
                    server.drawMove(8);
                    drawer.drawImage(8, player);
                    if(Winner.isWinner(8,player)){
                        drawer.drawWinLine(Winner.getWinBoxes());
                        server.wins(Winner.getWinBoxes());
                        status=true;

                    }

                }
            }
        } else {
            //do nothing yet
        }
        return status;
    }

    @Contract(pure = true)
    private boolean isClicked(int boxNumber) {
        boolean status = false;
        for (int a :
                clickedBoxes) {
            if (a == boxNumber) {
                status = true;
            }
        }
        return status;
    }
    public Canvas drawGame(){
        return drawer.drawGame();
    }
    public int getWinner(){
        notWinner=false;
        return player;
    }
    public int getPlayer(){
        return player;
    }

    public int getIndex() {
        return index;
    }

    public void clearGameDrawing(){
        Winner.resetAll();
        notWinner=true;
        player=0;
        clickedBoxes=null;
        clickedBoxes=new int[9];
    }
    public void drawBox(int box){
        manageInterface.setClickable(true);
        if(player==1){
            drawer.drawImage(box,2);
        }
        else
            drawer.drawImage(box,1);
        clickedBoxes[index++]=box;

    }
    public void wins(int winBoxes){
        int[] temp=new int[3];
        if(winBoxes==3){
            temp[0]=0;
            temp[1]=1;
            temp[2]=2;
        }
        else if(winBoxes==13){
            temp[0]=3;
            temp[1]=4;
            temp[2]=5;
        }
        else if(winBoxes==21){
            temp[0]=6;
            temp[1]=7;
            temp[2]=8;
        }
        else if(winBoxes==9){
            temp[0]=0;
            temp[1]=3;
            temp[2]=6;
        }
        else if(winBoxes==12){
            temp[0]=1;
            temp[1]=4;
            temp[2]=7;
        }
        else if(winBoxes==15){
            temp[0]=2;
            temp[1]=5;
            temp[2]=8;
        }
        else if(winBoxes==14){
            temp[0]=0;
            temp[1]=4;
            temp[2]=8;
        }
        else if(winBoxes==16){
            temp[0]=2;
            temp[1]=4;
            temp[2]=6;
        }
        drawer.drawWinLine(temp);
        //display winner name on screen
        manageInterface.setStatus(getWinner());
    }
    public void otherPlayerQuit(){

            manageInterface.showAlert();
    }
}
