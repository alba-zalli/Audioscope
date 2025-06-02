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

    //public ArrayList<Vector2> points = new ArrayList<>();

    public SineWave(Vector2 origin, int frequency) {
        super(origin, frequency);

    }

    public SineWave(Vector2 origin, int frequency, float amplitude, float speed, float waveLength, float scaleFactor) {
        super(origin, frequency, amplitude, speed, waveLength, scaleFactor);
    }


    public void initilizePointList(int resolutionPerCycle, float cycles) {
        float displayCycles = cycles + 3.0f; // The cycles param accepts the amount of full cycles that fills the screen, but we need to add 1 more so when it updates it doesnt go off screen for a bit, and then + 2 more just in case.
        float C = origin.getY(); // Vertical shift (baseline of the wave)
        float K = (float) ( (2 * Math.PI) / waveLength) * scaleFactor; // Frequency constant for 1 cycle
        float totalResolution = resolutionPerCycle * displayCycles; // Adjusts for the amount of points to generate by the defined amount of cycles it should coveer
        float increment = waveLength / resolutionPerCycle; // Horizontal distance between points
        float D = origin.getX(); // The sine graphs Horizontal offset

        // Add origin to first index
        points.clear();
        points.add(origin);

        //add new points to list
        for (int i = 1; i <= totalResolution; i++) {
            // Gets the previous point in the list (reminder, this loop starts at 1 instead of zero)
            Vector2 previousPt = points.get(i - 1);
           
            float newX = (previousPt.getX() + increment); // We do this so each point is evenely spaced out from eachother giving us the next x value to input into the sine function bellow
            float newY = (float) ( (1/scaleFactor) * amplitude * Math.sin(K * (newX - D)) + C); //This is the sine function. This will determine our point along the y - axis

            //Add new point to list
            points.add(i, new Vector2(newX, newY));
        }
        System.out.println("Point list reached initilization for sine wave"); // Debugging statement
    }
            //startPos, int frequency, int amplitude, int speed, float waveLength
    public String toString() {
        return ("Type: Sine Wave"
                + "\n[Origin] :" + origin.toString()
                + "\n[Frequency}: " + frequency
                + "\n[Amplitude]: " + amplitude
                + "\n[Speed]: " + speed
                + "\n[Wave Length]: " + waveLength);
    }

    public boolean equals(Waveform otherWave) {
        return false; //Not functional yet
    }

    public boolean clone(Waveform otherWave) {
        return false; //Not functional yet
    }
}
