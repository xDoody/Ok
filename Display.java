/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ok;


import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;


/**
 *
 * @author Zakki
 */
public final class Display extends JFrame{
    JFrame frame;
    public Display(Game game,int width,int height){
        frame = new JFrame("Ok");
        createAndShowGUI(game,width,height);
        
    }
    
    public void createAndShowGUI(Game game,int width,int height){

        frame.setPreferredSize(new Dimension(width,height));
        frame.setMaximumSize(new Dimension(width,height));
        frame.setMinimumSize(new Dimension(width,height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        
        frame.setVisible(true);
        game.start();

        
        
    }



    
    
}
