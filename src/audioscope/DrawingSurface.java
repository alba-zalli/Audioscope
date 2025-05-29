/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package audioscope;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author albaz
 */


public class DrawingSurface extends JPanel{
    
    
    /**
     * Does the actual drawing
     * @param g - the Graphics object to draw with
     */
     private void doDrawing(Graphics g) {        
        //the Graphics2D class is the class that handles all the drawing
        //must be casted from older Graphics class in order to have access to some newer methods
        Graphics2D g2d = (Graphics2D) g;
        //draw a string on the panel        
        g2d.drawString("Java 2D", 50, 50); //(text, x, y)
        g2d.drawOval(0, 0, 100, 100);  //(x,y,width,height)
        g2d.setColor(Color.red);//change color
        int x, y;
        for(int i = 0; i < 1000; i++){
            //get random x,y location
            x = (int)(Math.random()*getWidth());
            y = (int)(Math.random()*getHeight());
            //draw a really short line (point)
            g2d.drawLine(x,y,x,y);
        }
    }
     
    
    
    /**
     * Overrides paintComponent in JPanel class so that we can do our own custom
     * painting
     */
    public void paintComponent(Graphics g) {        
        super.paintComponent(g);//does the necessary work to prepare the panel for drawing
        doDrawing(g); //invoke our custom drawing method
    }
    
    
}
