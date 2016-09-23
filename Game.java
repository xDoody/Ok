/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ok;

import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;



/**
 *
 * @author Zakki
 */
public class Game extends Canvas implements Runnable{
    Thread thread = new Thread();
    boolean running;
    int dummyLevel=2;
    Random rand = new Random();
    Handler handler;
    double width=600 ,height=600;
    public Game(){
        handler= new Handler();
        Display disp =new Display(this,(int)width,(int)height);

        
        float a=(float) (rand.nextInt((int)width)+1);
        float b= (float) (rand.nextInt((int)height)+1);
        float c= rand.nextFloat();
        float d= rand.nextFloat();
        
        handler.addObject(new OkObject(a,b,ID.OkObject,c,d,(int)width,(int)height,dummyLevel));
        this.addMouseListener(
            new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent me){
                    if(
                        (handler.getX() < me.getX() && (handler.getX()+32)>=me.getX())
                        &&
                        (handler.getY() < me.getY() && (handler.getY()+32)>=me.getY())
                       ){
                        handler.colision();
                        }
   
                }
            }
        );  
    }
    //<editor-fold defaultstate="collapsed" desc="Start/Stop">
    public synchronized void  start(){
        //Start a thread and make the running boolean true to start the game loop
        thread = new Thread(this);
        thread.start();
        running=true;
    }
    public synchronized void stop(){
        try{
            //join the thread to stop and set the running boolean false to stop the game loop
            thread.join();
            running=false;
        }
        catch(Exception e){
        }
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Run">
    
    @Override
    public void run() {
        long lastTime=System.nanoTime();
        double amountOfTicks=60.0;
        double ns=100000000/amountOfTicks;
        double delta=0;
        long tim = System.currentTimeMillis();
        while(running)
        {
            long now = System.nanoTime();
            delta +=(now-lastTime)/ns;
            lastTime=now;
            while(delta>=1)
            {
                tick();
                delta--;
            }
            if(running)
                render();
            
        }
        stop();
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Render">
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        //BufferStrategy bs = this.getBufferStrategey();
        if(bs==null)
        {
            //if the bufferstrategy is new , make a new one
            this.createBufferStrategy(3);
            return;
        }
        //initialize graphics
        Graphics g = bs.getDrawGraphics();
        //set the background color to black
        g.setColor(Color.black);
        //fill the background
        g.fillRect(0, 0, (int)width, (int)height);
        handler.render(g);
        //dispose the graphics
        g.dispose();
        //paint the graphis
        bs.show();
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Tick">
    public void tick(){
        handler.tick();
    }
//</editor-fold>

    
    
}
