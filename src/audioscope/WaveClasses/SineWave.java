/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package audioscope.WaveClasses;

import audioscope.Vector2;
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
    
    public void initilizePointList(int quality, int distance){
        int resolution = quality;
        int dist = distance;
        points = new ArrayList<Vector2>();
        points.add(startPos);
        for (int i = 1; i < resolution; i++) {
            Vector2 lastPt = points.get(i - 1);
            Vector2 newPt = new Vector2(lastPt.getX() + dist, (float) Math.sin(dist));
            points.add(i, newPt);
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
