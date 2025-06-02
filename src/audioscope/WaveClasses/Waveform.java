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
public abstract class Waveform {

    protected int frequency;
    protected int amplitude;
    protected int speed;
    protected ArrayList<Vector2> points = new ArrayList<>();
    protected float waveLength;
    protected Vector2 origin;

    public Waveform(Vector2 origin, int frequency) {
        this.frequency = frequency;
        this.origin = origin;
        speed = 10;
        amplitude = 1;

    }

    public Waveform(Vector2 origin, int frequency, int amplitude, int speed, float waveLength) {
        this(origin, frequency);
        this.amplitude = amplitude;
        this.speed = speed;
        this.waveLength = waveLength;
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

    public Vector2 getOrigin() {
        return origin;
    }

    public void setOrigin(Vector2 origin) {
        this.origin = origin;
    }

    public float getWaveLength() {
        return waveLength;
    }

    public void setWaveLength(float waveLength) {
        this.waveLength = waveLength;
    }

    public void drawWave(Graphics g2d) {

        for (int i = 0; i < points.size() - 1; i++) {
            int x1 = (int) points.get(i).getX();
            int y1 = (int) points.get(i).getY();

            int x2 = (int) points.get(i + 1).getX();
            int y2 = (int) points.get(i + 1).getY(); // Funfact, if you accidentally do .getX(); (like i did) it makes a funky cool graph lol

            //Draw
            g2d.drawLine(x1, y1, x2, y2);

        }

    }

    public void translateWave(float amount) {
        for (int i = 0; i < points.size(); i++) {
            points.get(i).addX(amount);
        }
    }
   
    /**
     * Handles animating the wave. It moves the wave by the amount the speed attribute indicates by calling translateWave function.
     * It also handles updating the wave to avoid the origin point of the wave (the very start) from going off screen and breaking its continuity
     * This function is called every frame in the DrawingSurface class.
     */

    public void animate(float deltaTime) {
        //float actualSpeed = frequency * waveLength;
        translateWave(speed * deltaTime);
        if (origin.getX() >= 0) { // Must be -waveLength to prevent the ends of the wave from showing
            translateWave(-waveLength); // Will be moved 1 cycle to the left. (which will be off screen)
        }
    }
    //public abstract void initilizePointList(int resolutionPerCycle, float cycles);
    public abstract boolean equals(Waveform otherWave);

    public abstract boolean clone(Waveform otherWave);

    public abstract String toString();

}
