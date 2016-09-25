/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ok;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author Zakki
 */
public class Handler {
     public LinkedList<GameObject> object = new LinkedList<>();
     int l = object.size();
    public void tick()
    {
    for(int i=0;i<object.size();i++)
        {
            GameObject tempObject = object.get(i);
            
            tempObject.tick();
        }
    }
    public void render(Graphics g)
    {
    for(int i=0;i<object.size();i++)
    {
        GameObject tempObject= object.get(i);
        tempObject.render(g);
    }
    }
    public void addObject(GameObject object)
    {
    this.object.add(object);
    }
    public void removeObject(GameObject object)
    {
    this.object.remove(object);
    }
    public Rectangle getBounds(){
        int i =object.size();
        GameObject tempObject = object.get(i);
        return tempObject.getBounds();
            
    }
    public int getX(){
        
        GameObject tempObject= object.get(l);
        return (int)tempObject.getX();
    }
    public int getY(){
        GameObject tempObject= object.get(l);
        return (int)tempObject.getY();
    }
    public void colision(Graphics g){
        GameObject tempObject= object.get(l);
        tempObject.colision(g);
    }
}
