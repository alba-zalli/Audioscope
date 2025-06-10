/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package audioscope.WaveClasses;

import audioscope.Vector2;
import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 *
 * @author AmAbd4146
 */
public class SineWave extends Waveform {



    //boolean isPlaying = false;
    /**
     * Primary Constructor: Creates SineWave and initializes the attributes
     *
     * @param origin the starting position (Vector2) of the wave
     * @param frequency the number of wave cycles per second (in Hz)
     */
    public SineWave(Vector2 origin, float frequency) {
        super(origin, frequency); // Call parent constructor

    }

    /**
     * Secondary Constructor: Creates SineWave with every attribute set in
     * accordance to the parameters.
     *
     * @param origin the starting position (Vector2) of the wave
     * @param frequency the number of wave cycles per second (in Hz)
     * @param amplitude the height of the wave peaks (given in pixels
     * @param speed the speed at which the wave propagates (given in pixels)
     * @param waveLength the wavelength (given in pixels)
     * @param scaleFactor a factor to scale the wave's size when rendering
     */
    public SineWave(Vector2 origin, float frequency, float amplitude, float speed, float waveLength, float scaleFactor) {
        super(origin, frequency, amplitude, speed, waveLength, scaleFactor); // Call parent constructor


    }

    public void playAudios() {



    }
    
    public void setSpeed(){
       // mediaPlayer.setRate(2);
    }
    
    public void initilizePointList(float resolutionPerCycle, float cycles) {
        float displayCycles = cycles + 5.0f; // The cycles param accepts the amount of full cycles that fills the screen, but we need to add 1 more so when it updates it doesnt go off screen for a bit, and then + 2 more just in case.
        float C = origin.getY(); // Vertical shift (baseline of the wave)
        float K = (float) ((2 * Math.PI) / waveLength); // Frequency constant for 1 cycle
        int totalResolution = Math.round(resolutionPerCycle * displayCycles); // Adjusts for the amount of points to generate by the defined amount of cycles it should coveer
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
            float newY = (float) (amplitude * Math.sin(K * (newX - D)) + C); //This is the sine function. This will determine our point along the y - axis

            //Add new point to list
            points.add(new Vector2(newX, newY));
        }
        System.out.println("Point list reached initilization for sine wave"); // Debugging statement
    }

    /**
     * toString method that gives the waves attributes as a string
     *
     * @return - the string
     */
    public String toString() {
        return ("Type: Sine Wave"
                + "\n[Origin] :" + origin.toString()
                + "\n[Frequency}: " + frequency
                + "\n[Amplitude]: " + amplitude
                + "\n[Speed]: " + speed
                + "\n[Wave Length]: " + waveLength);
    }

    /**
     * Checks if the 2 waves are equal
     *
     * @param otherWave - the wave to compare to.
     * @return true if equal, false otherwise
     */
    public boolean equals(Waveform otherWave) {
        return false; //Not functional yet
    }

    /**
     * Clones the waves properties
     *
     * @return the cloned wave.
     */
    public Waveform clone() {
        Vector2 originCopy = new Vector2(this.origin.getX(), this.origin.getY());
        return new SineWave(originCopy, this.frequency, this.amplitude, this.speed, this.waveLength, this.scaleFactor);
    }

    /**
     * Gets the data type
     *
     * @return the data type as a string
     */
    public String getInstanceOf() {
        return "SineWave";
    }
}
