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
 * @author cunpl
 */
public class TriangleWave extends Waveform {

    public TriangleWave(Vector2 origin, int frequency) {
        super(origin, frequency);

    }

    /**
     *
     * @param startPos
     * @param frequency
     * @param amplitude
     * @param speed
     * @param waveLength
     */
    public TriangleWave(Vector2 origin, int frequency, float amplitude, float speed, float waveLength, float scaleFactor) {
        super(origin, frequency, amplitude, speed, waveLength, scaleFactor);
    }

    public void initilizePointList(float cycles) {
        
        // Note, a triangle wave is just a sine wave where each point linearly connects to the other point, and those points is the sine waves min, max, and zeros
        int res = 4; // A triangle wave has a fixed resolution, in the code we could be running a sineWave first so this must still accept a resolution param since the wave type can change (prevents remaking lines
        float displayCycles = cycles + 3.0f; // The cycles param accepts the amount of full cycles that fills the screen, but we need to add 1 more so when it updates it doesnt go off screen for a bit, and then + 2 more just in case.

        float C = origin.getY(); // Vertical shift (baseline of the wave)
        float totalResolution = 5 * displayCycles; // Adjusts for the amount of points to generate by the defined amount of cycles it should coveer
        float K = (float) ( (2 * Math.PI) / waveLength); // Frequency constant for 1 cycle
        float increment = waveLength / res; // Gets how many pixels to move along the x axis (must be 1/4 the wave length).
        float D = origin.getX(); 
       

        // Add origin to first index
        points.clear();
        points.add(origin);

        //add new points to list
        for (int i = 1; i <= totalResolution; i++) {
                        // Gets the previous point in the list (reminder, this loop starts at 1 instead of zero)
            Vector2 previousPt = points.get(i - 1);
            
            float newX = (previousPt.getX() + increment); // We do this so each point is evenely spaced out from eachother giving us the next x value to input into the sine function bellow
            float newY = (float) (amplitude * Math.sin(K * (newX - D)) + C); //This is the sine function. This will determine our point along the y - axis

            //Add new point to list
            points.add(i, new Vector2(newX, newY));
        }
        System.out.println("Point list reached initilization for triangle wave"); // Debugging statement
    }

    public String toString() {
        return ("Type: Triangle Wave" 
                + "\n[Origin] :" + origin.toString()
                + "\n[Frequency}: " + frequency
                + "\n[Amplitude]: " + amplitude
                + "\n[Speed]: " + speed
                + "\n[Wave Length]: " + waveLength);
    }

    public boolean equals(Waveform otherWave) {
        return false; //Not functional yet
    }

    
    public Waveform clone() {
        
        return null;
        
    }
}

