/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package audioscope.WaveClasses;

import audioscope.Vector2;

/**
 * Sawtooth wave method Draws a sawtooth wave June 11 2025
 *
 * @author Ameer Abd El-Fatah and Alba Zalli
 */
public class SawtoothWave extends Waveform {

    /**
     * Primary Constructor: Creates SawtoothWavee and initializes the attributes
     *
     * @param origin the starting position (Vector2) of the wave
     * @param frequency the number of wave cycles per second (in Hz)
     */
    public SawtoothWave(Vector2 origin, float frequency) {
        super(origin, frequency); // Calls parent constructor

    }

    /**
     * Secondary Constructor: Creates SawtoothWave with every attribute set in
     * accordance to the parameters.
     *
     * @param origin the starting position (Vector2) of the wave
     * @param frequency the number of wave cycles per second (in Hz)
     * @param amplitude the height of the wave peaks (given in pixels
     * @param speed the speed at which the wave propagates (given in pixels)
     * @param waveLength the wavelength (given in pixels)
     * @param scaleFactor a factor to scale the waves size when rendering
     */
    public SawtoothWave(Vector2 origin, float frequency, float amplitude, float speed, float waveLength, float scaleFactor) {
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
        float displayCycles = cycles + 5.0f;
        float C = origin.getY(); // vertical shift
        float totalResolution = resolutionPerCycle * displayCycles;
        float increment = waveLength / resolutionPerCycle;
        float D = origin.getX(); // horizontal offset

        points.clear(); //clear previous points
        points.add(origin);

        //for total resoltion, go over the points
        for (int i = 1; i <= totalResolution; i++) {
            Vector2 previousPt = points.get(i - 1);
            float newX = previousPt.getX() + increment;

            float phase = ((newX - D) / waveLength) % 1.0f;
            if (phase < 0) {
                phase += 1.0f;
            }
            float newY = (2 * phase - 1) * amplitude + C;

            points.add(i, new Vector2(newX, newY));
        }

        System.out.println("Point list reached initialization for sawtooth wave"); // Debugging statement
    }

    /**
     * toString method that gives the waves attributes as a string
     *
     * @return - the string
     */
    @Override
    public String toString() {
        return ("Type: Sawtooth Wave"
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
        if (this.frequency == otherWave.frequency && this.amplitude == otherWave.amplitude && this.waveLength == otherWave.waveLength && this.speed == otherWave.speed && otherWave.getInstanceOf().equals(getInstanceOf())) {
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
        return new SawtoothWave(originCopy, this.frequency, this.amplitude, this.speed, this.waveLength, this.scaleFactor);
    }

    /**
     * Gets the data type
     *
     * @return the data type as a string
     */
    public String getInstanceOf() {
        return "SawtoothWave";
    }

}
