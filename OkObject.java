/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ok;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author Zakki
 */
public class OkObject extends GameObject{
    int w=90;
    int h=30;
    Random rand=new Random();
    int ran[]=new int[1000];
    int ranCount=0;
    int width,height;
    int level;
    public OkObject(float x, float y, ID id,float velX,float velY,int width,int height,int level) {
        super(x, y, id);
        this.width=width;
        this.height=height;
        this.level=level;
        
        setVelX(velX);
        setVelY(velY);

    }
    public void wigle(){
        if(ranCount<level){
            ran[ranCount]=rand.nextInt(1000)+1;
            ranCount++;
        }
        int rndx = rand.nextInt(1000)+1;
        int rndy = rand.nextInt(1000)+1;
        for(int f=0;f<ran.length;f++){
            if(ran[f]==rndx){
                velX=rand.nextFloat();
                
                this.velX*=-1;
                
            }
            if(ran[f]==rndy){
                velY = rand.nextFloat();
                this.velY*=-1;
            }
        }
    }
    @Override
    public void tick() {
        //x+=velX;
        y+=velY;
        if(y<=0 || y>= height-32)
        {
            
            velY*=-1;
            
        }
        if(x<=0 || x>=width-32){
            
            velX*=-1;
        }
        wigle();
    }
    int clicked=0;
    @Override
    public void colision(Graphics g){
        if(clicked==0)
            clicked=1;
        else
            clicked=0;
        level++;
    }
    
    public void setColor(Graphics g)
    {
        if(clicked==0){
            g.setColor(Color.cyan);
        }
        else{
            g.setColor(Color.yellow);
        }
    }
    
    @Override
    public void render(Graphics g) {
        setColor(g);
        g.fill3DRect((int)x,(int) y, 32, 32, true);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }

    
}
