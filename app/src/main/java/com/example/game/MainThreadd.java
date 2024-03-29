package com.example.game;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThreadd extends Thread {

    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean running;
    private static Canvas canvas;

    public MainThreadd(SurfaceHolder surfaceHolder, GameView gameView){
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
    }

    @Override
    public void run(){
        while(running){
            //startTime = System.nanoTIme();
            canvas = null;
        }
        try{
            canvas = this.surfaceHolder.lockCanvas();
            synchronized (surfaceHolder){
                this.gameView.update();
                this.gameView.draw(canvas);
            }
        }catch(Exception e){} finally{
            if(canvas != null){
                try{
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    public void setRunning(boolean isRunning){
        running = isRunning;
    }
}
