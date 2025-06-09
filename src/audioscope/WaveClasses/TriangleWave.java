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

    /**
     * Primary Constructor: Creates a TriangleWave and initializes the
     * attributes
     *
     * @param origin the starting position (Vector2) of the wave
     * @param frequency the number of wave cycles per second (in Hz)
     */
    public TriangleWave(Vector2 origin, int frequency) {
        super(origin, frequency); // Call parent constructor

    }

    /**
     * Secondary Constructor: Creates a TriangleWave with every attribute set in
     * accordance to the parameters.
     *
     * @param origin the starting position (Vector2) of the wave
     * @param frequency the number of wave cycles per second (in Hz)
     * @param amplitude the height of the wave peaks (given in pixels
     * @param speed the speed at which the wave propagates (given in pixels)
     * @param waveLength the wavelength (given in pixels)
     * @param scaleFactor a factor to scale the wave's size when rendering
     */
    public TriangleWave(Vector2 origin, float frequency, float amplitude, float speed, float waveLength, float scaleFactor) {
        super(origin, frequency, amplitude, speed, waveLength, scaleFactor); // Call parent constructor
    }

    public void initilizePointList(float resolutionPerCycle, float cycles) {

        // Note, a triangle wave is just a sine wave where each point linearly connects to the other point, and those points is the sine waves min, max, and zeros
        int res = 4; // A triangle wave has a fixed resolution, in the code we could be running a sineWave first so this must still accept a resolution param since the wave type can change (prevents remaking lines
        float displayCycles = cycles + 5.0f; // The cycles param accepts the amount of full cycles that fills the screen, but we need to add 1 more so when it updates it doesnt go off screen for a bit, and then + 2 more just in case.

        float C = origin.getY(); // Vertical shift (baseline of the wave)
        float totalResolution = 5 * displayCycles; // Adjusts for the amount of points to generate by the defined amount of cycles it should coveer
        float K = (float) ((2 * Math.PI) / waveLength); // Frequency constant for 1 cycle
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

    /**
     * toString method that gives the waves attributes as a string
     *
     * @return - the string
     */
    public String toString() {
        return ("Type: Triangle Wave"
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
        return new TriangleWave(originCopy, this.frequency, this.amplitude, this.speed, this.waveLength, this.scaleFactor);

    }

    /**
     * Gets the data type
     *
     * @return the data type as a string
     */
    public String getInstanceOf() {
        return "TriangleWave";
    }
    
    public void playWave(int durationMs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
