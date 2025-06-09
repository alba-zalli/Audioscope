/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package audioscope.WaveClasses;

import audioscope.Vector2;

/**
 *
 * @author albaz
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
    //startPos, int frequency, int amplitude, int speed, float waveLength

    /**
     * toString method that gives the waves attributes as a string
     *
     * @return - the string
     */
    public String toString() {
        return ("Type: Square Wave"
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
        //DUMMY CODE
        Vector2 originCopy = new Vector2(this.origin.getX(), this.origin.getY());
        return new SquareWave(originCopy, this.frequency, this.amplitude, this.speed, this.waveLength, this.scaleFactor);
    }

    /**
     * Gets the data type
     *
     * @return the data type as a string
     */
    public String getInstanceOf() {
        return "SquareWave";
    }

    public void setScaleFactor(float scaleFactor) {
        this.scaleFactor = scaleFactor;
    }
    
    public void playWave(int durationMs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
