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

    public SawtoothWave(Vector2 origin, float frequency) {
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
    public SawtoothWave(Vector2 origin, float frequency, float amplitude, float speed, float waveLength, float scaleFactor) {
        super(origin, frequency, amplitude, speed, waveLength, scaleFactor);
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

    public String toString() {
        return ("Type: Sawtooth Wave"
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
        Vector2 originCopy = new Vector2(this.origin.getX(), this.origin.getY());
        return new SawtoothWave(originCopy, this.frequency, this.amplitude, this.speed, this.waveLength, this.scaleFactor);
    }

    public String getInstanceOf() {
        return "SawtoothWave";
    }

}
