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

    protected float frequency;
    protected float amplitude;
    protected float speed;
    protected ArrayList<Vector2> points = new ArrayList<>();
    protected float waveLength;
    protected Vector2 origin;
    protected float scaleFactor;
    protected float adjustedAmp;

    public Waveform(Vector2 origin, float frequency) {
        this.frequency = frequency;
        this.origin = origin;
        speed = 10;
        amplitude = 1;

    }

    public Waveform(Vector2 origin, float frequency, float amplitude, float speed, float waveLength, float scaleFactor) {
        this(origin, frequency);
        this.amplitude = amplitude;
        this.speed = speed;
        this.waveLength = waveLength;
        this.scaleFactor = scaleFactor;
        adjustedAmp = amplitude / scaleFactor;
    }

    public float getFrequency() {
        return frequency;
    }

    public void setFrequency(float frequency) {
        this.frequency = frequency;
    }

    public float getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(float amplitude) {
        this.amplitude = amplitude;
    }

    public void setScaleFactor(float scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    public float getScaleFactor() {
        return scaleFactor;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
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

    public void setWave(float xLoco) {
        float ogOrigin_X = points.get(0).getX();
        points.get(0).setX(xLoco);

        for (int i = 1; i < points.size(); i++) {

            Vector2 origin = points.get(0);
            Vector2 currPoint = points.get(i);
            float distFromOld_X = currPoint.getX() - ogOrigin_X;
            currPoint.setX(origin.getX() + distFromOld_X);
        }
    }

    public void translateWave(float amount) {
        for (int i = 0; i < points.size(); i++) {
            points.get(i).addX(amount);
        }
    }

    /**
     * Handles animating the wave. It moves the wave by the amount the speed
     * attribute indicates by calling translateWave function. It also handles
     * updating the wave to avoid the origin point of the wave (the very start)
     * from going off screen and breaking its continuity This function is called
     * every frame in the DrawingSurface class.
     */
    public void animate(float deltaTime) {
        float adjustedScale = scaleFactor / 100f;
        float adjDelta = deltaTime * adjustedScale;

        translateWave(speed * adjDelta);

         if (origin.getX() >= 0) { // Must be -waveLength to prevent the ends of the wave from showing
            setWave(-waveLength); // Will be moved 1 cycle to the left. (which will be off screen)

        }
    }

    public abstract boolean equals(Waveform otherWave);

    public abstract Waveform clone();

    public abstract String toString();

    public Object getInstanceOf() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void initilizePointList(float resolutionPerCycle, float f) {
    }

}
