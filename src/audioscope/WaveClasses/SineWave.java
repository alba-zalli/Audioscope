/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package audioscope.WaveClasses;

import audioscope.Vector2;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author AmAbd4146
 */
public class SineWave extends Waveform {
    ArrayList<Vector2> points;
    
    public SineWave(Vector2 startPos, int frequency){
        super(startPos, frequency);
    
    }

    public SineWave(Vector2 startPos, int frequency, int amplitude, int speed){
        super(startPos, frequency, amplitude, speed);
    }
    public void drawWave(){
    
    }
    
    public void initilizePointList(Graphics g2d,int quality, int distance) {
        int resolution = quality;
        int dist = distance;
        points = new ArrayList<Vector2>();
        points.add(startPos);
        
        int waveLength = dist * resolution;
        int addX = waveLength / resolution;
        
        for (int i = 1; i < resolution; i++) {
            
            Vector2 lastPt = points.get(i - 1);
            int newX = lastPt.getX() + addX;
            int newY = (int) (Math.sin( ( (2 * Math.PI) / (waveLength) ) * newX ) * 40)+ lastPt.getY();
            System.out.println(newY);
            
            points.add(i, new Vector2(newX, newY));
            g2d.drawLine(lastPt.getX(), lastPt.getY(), newX, newY);
        }

    }
    
    public void drawPoints(){
    
    }
    
    public String toString(){
       return "Temp";
    }
    
    public boolean equals(Waveform otherWave){
        return false; //Not functional yet
    }
    
    public boolean clone(Waveform otherWave){
        return false; //Not functional yet
    }
    
}
