/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package audioscope.WaveClasses;

import audioscope.Vector2;

/**
 *
 * @author cunpl albaz
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

    public void initilizePointList(float resolutionPerCycle, float cycles) {
        float displayCycles = cycles + 5.0f;
        float C = origin.getY(); // vertical shift
        float totalResolution = resolutionPerCycle * displayCycles;
        float increment = waveLength / resolutionPerCycle;
        float D = origin.getX(); // horizontal offset

        points.clear();
        points.add(origin);

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

        System.out.println("Point list reached initialization for sawtooth wave");
    }

    /**
     * toString method that gives the waves attributes as a string
     *
     * @return - the string
     */
    public String toString() {
        return ("Type: Sawtooth Wave"
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

    public void playWave(int durationMs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
