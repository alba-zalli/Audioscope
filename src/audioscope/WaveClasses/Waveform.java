/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package audioscope.WaveClasses;

import audioscope.Vector2;

/**
 *
 * @author AmAbd4146
 */
public abstract class Waveform {

    protected int frequency;
    protected int amplitude;
    protected int speed;
    protected Vector2 startPos;

    public Waveform(Vector2 startPos, int frequency) {
        this.frequency = frequency;
        this.startPos = startPos;
        speed = 50;
        amplitude = 1;
    }

    public Waveform(Vector2 startPos, int frequency, int amplitude, int speed) {
        this(startPos, frequency);
        this.amplitude = amplitude;
        this.speed = speed;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(int amplitude) {
        this.amplitude = amplitude;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Vector2 getStartPos() {
        return startPos;
    }

    public void setStartPos(Vector2 startPos) {
        this.startPos = startPos;
    }
    
    public abstract void drawWave();

    public abstract boolean equals(Waveform otherWave);

    public abstract boolean clone(Waveform otherWave);

    public abstract String toString();
    
   

}
