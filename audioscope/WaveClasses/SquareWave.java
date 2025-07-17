/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package audioscope.WaveClasses;

import audioscope.Vector2;

/**
 * Square wave class Draws a square wave June 11 2025
 *
 * @author Alba Zalli
 */
public class SquareWave extends Waveform {

    /**
     * Primary Constructor: Creates SquareWave and initializes the attributes
     *
     * @param origin the starting position (Vector2) of the wave
     * @param frequency the number of wave cycles per second (in Hz)
     */
    public SquareWave(Vector2 origin, float frequency) {
        super(origin, frequency); // Calls parent constructor

    }

    /**
     * Secondary Constructor: Creates SquareWave with every attribute set in
     * accordance to the parameters.
     *
     * @param origin the starting position (Vector2) of the wave
     * @param frequency the number of wave cycles per second (in Hz)
     * @param amplitude the height of the wave peaks (given in pixels
     * @param speed the speed at which the wave propagates (given in pixels)
     * @param waveLength the wavelength (given in pixels)
     * @param scaleFactor a factor to scale the wave's size when rendering
     */
    public SquareWave(Vector2 origin, float frequency, float amplitude, float speed, float waveLength, float scaleFactor) {
        super(origin, frequency, amplitude, speed, waveLength, scaleFactor); // Calls parent constructor
    }

    /**
     * Generates a list of points from the waves origin point. These points
     * represent the visual shape of the wave which are later connected with
     * lines using graphics 2d. These points are stored in the pointList
     * attribute! (Look in the wave form parent class for it)
     *
     * @param resolutionPerCycle - The amount of points 1 wave cycle should have
     * @param cycles - The amount of cycles to display
     */
    @Override
    public void initilizePointList(float resolutionPerCycle, float cycles) {
        float displayCycles = cycles + 5.0f; // The cycles param accepts the amount of full cycles that fills the screen, but we need to add 1 more so when it updates it doesnt go off screen for a bit, and then + 2 more just in case.
        float C = origin.getY(); // Vertical shift (baseline of the wave)
        float K = (float) ((2 * Math.PI) / waveLength); // Frequency constant for 1 cycle
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
            float phase = (newX - D) / waveLength;
            float newY = (float) (amplitude * Math.signum(Math.sin(2 * Math.PI * phase)) + C);

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
    @Override
    public String toString() {
        return ("Type: Square Wave"
                + "\n[Origin] :" + origin.toString()
                + "\n[Frequency}: " + frequency
                + "\n[Amplitude]: " + amplitude
                + "\n[Speed]: " + speed
                + "\n[Wave Length]: " + waveLength
                + "\n[Scale Factor]: " + scaleFactor);
    }
    /**
     * Checks if the 2 waves are equal
     *
     * @param otherWave - the wave to compare to.
     * @return true if equal the frequency, amplitude, and waveLength, and speed
     * values are equal, false otherwise
     */
    @Override
    public boolean equals(Waveform otherWave) {
        if (this.frequency == otherWave.frequency && this.amplitude == otherWave.amplitude && this.waveLength == otherWave.waveLength && this.speed == otherWave.speed && otherWave.getInstanceOf().equals(getInstanceOf()) ) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Clones the waves properties
     *
     * @return the cloned wave.
     */
    @Override
    public Waveform clone() {
        Vector2 originCopy = new Vector2(this.origin.getX(), this.origin.getY());
        return new SquareWave(originCopy, this.frequency, this.amplitude, this.speed, this.waveLength, this.scaleFactor);
    }

    /**
     * Gets the data type
     *
     * @return the data type as a string
     */
    @Override
    public String getInstanceOf() {
        return "SquareWave";
    }



}
