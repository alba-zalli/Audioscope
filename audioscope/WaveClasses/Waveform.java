/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package audioscope.WaveClasses;

import audioscope.Vector2;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Abstract waveform method helps draw wave June 11 2025. 
 * Every wave class has this as its parent!
 *
 * @author Ameer Abd El-Fatah and Alba Zalli
 */
public abstract class Waveform {

    // Attributes
    protected float frequency; // wave frequency (in hz)
    protected float amplitude; // wave height (in pixels)
    protected float speed; // wave speed (in pixels per second)
    protected ArrayList<Vector2> points = new ArrayList<>(); // Holds the points that make the waves (to show in a 2d space). NOTE the very first point in this list is always the origin point
    protected float waveLength; // length of 1 wave cycle (in pixels)
    protected Vector2 origin; // Origin point of the wave, every points position is based off its distance from this point
    protected float scaleFactor; // The scaling factor (how much to zoom) 

    /**
     * Primary constructor: Creates a basic Waveform with a specified origin,
     * scaleFactor, and waveLength. With speed, amplitude, waveLength, and scale
     * factor set to default values
     *
     * @param origin the starting position (Vector2) of the waveform
     * @param frequency the number of cycles per second (in Hz)
     *
     */
    public Waveform(Vector2 origin, float frequency) {
        this.frequency = frequency;
        this.origin = origin;
        speed = 50;
        amplitude = 50;
        waveLength = 200;
        scaleFactor = 1;

    }

    /**
     * Secondary constructor: Creates a Waveform with all attributes set.
     *
     * @param origin the starting position (Vector2) of the waveform
     * @param frequency the number of cycles per second (in Hz)
     * @param amplitude the waves amplitude (in pixels)
     * @param speed the speed at which the waveform moves (in pixels per second)
     * @param waveLength the length of 1 cycle of the wave (in pixels)
     * @param scaleFactor the scaling factor
     */
    public Waveform(Vector2 origin, float frequency, float amplitude, float speed, float waveLength, float scaleFactor) {
        this(origin, frequency); // Constructor chaining
        this.amplitude = amplitude;
        this.speed = speed;
        this.waveLength = waveLength;
        this.scaleFactor = scaleFactor;
    }

    /**
     * Returns the frequency of the waveform.
     *
     * @return the frequency in Hertz (Hz)
     */
    public float getFrequency() {
        return frequency;
    }

    /**
     * Setter for frequency attribute
     *
     * @param frequency the new frequency
     */
    public void setFrequency(float frequency) {
        this.frequency = frequency;
    }

    /**
     * Returns the amplitude of the waveform.
     *
     * @return the amplitude (height of the wave in pixels)
     */
    public float getAmplitude() {
        return amplitude;
    }

    /**
     * Setter for amplitude attribute
     *
     * @param amplitude the new amplitude
     */
    public void setAmplitude(float amplitude) {
        this.amplitude = amplitude;
    }

    /**
     * Setter for scaleFactor attribute
     *
     * @param scaleFactor the new scaleFactor
     */
    public void setScaleFactor(float scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    /**
     * Getter for scaleFactor attribute
     *
     * @return the scaleFactor attribute
     */
    public float getScaleFactor() {
        return scaleFactor;
    }

    /**
     * Getter for speed attribute
     *
     * @return the speed attribute
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Setter for speed attribute
     *
     * @param speed the new speed
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * Getter for origin attribute
     *
     * @return the origin attribute
     */
    public Vector2 getOrigin() {
        return origin;
    }

    /**
     * Setter for origin attribute
     *
     * @param origin the new origin
     */
    public void setOrigin(Vector2 origin) {
        this.origin = origin;
    }

    /**
     * Getter for waveLength attribute
     *
     * @return the waveLength attribute
     */
    public float getWaveLength() {
        return waveLength;
    }

    /**
     * Setter for waveLength attribute
     *
     * @param waveLength the new waveLength
     */
    public void setWaveLength(float waveLength) {
        this.waveLength = waveLength;
    }

    /**
     * Draws a line connecting every generated point on the wave to the next.
     *
     * @param g - The Graphics object we are using
     */
    public void drawWave(Graphics g) {

        for (int i = 0; i < points.size() - 1; i++) {
            int x1 = (int) points.get(i).getX();
            int y1 = (int) points.get(i).getY();

            int x2 = (int) points.get(i + 1).getX();
            int y2 = (int) points.get(i + 1).getY(); // Funfact, if you accidentally do .getX(); (like i did) it makes a funky cool graph lol

            //Draw
            g.drawLine(x1, y1, x2, y2);

        }

    }

    /**
     * Sets the waves location to an exact value. Done by setting the origin
     * point to the desired location (along the x axis)) then finds the distance
     * (along the x axis) from the original origin for every point so every
     * point is the same distance from the origin point
     *
     * @param xLoco - The new location along the x axis to move everything to!
     */
    public void setWave(float xLoco) {
        float ogOrigin_X = points.get(0).getX();
        points.get(0).setX(xLoco); // The very first index of the list is the origin point

        for (int i = 1; i < points.size(); i++) {

            Vector2 origin = points.get(0);
            Vector2 currPoint = points.get(i);
            float distFromOld_X = currPoint.getX() - ogOrigin_X;
            currPoint.setX(origin.getX() + distFromOld_X);
        }
    }

    /**
     * Shifts the wave by a given amount by adding the given amount to every
     * point on the wave.
     *
     * @param amount - The amount to shift the wave
     */
    public void translateWave(float amount) {
        for (int i = 0; i < points.size(); i++) {
            points.get(i).addX(amount);
        }
    }

    /**
     * Handles animating the wave. It moves the wave by the amount the speed
     * attribute indicates by calling translateWave function. It also handles
     * updating the wave to avoid the origin point of the wave (the very start)
     * from going off screen and breaking its continuity. This function is
     * called every frame in the DrawingSurface class.
     *
     * @param deltaTime - The time it took from the previous frame to the
     * current frame. Does this so lower or higher frame rates does not affect
     * speed (and so speed is pixels per second).
     */
    public void animate(float deltaTime) {
        float adjustedScale = scaleFactor / 100f; // WHY 100??
        float adjDelta = deltaTime * adjustedScale; // MAY BE INACCURATE DID I DO THIS OR WAS IT ALBA? WHY EXACTLY?

        translateWave(speed * adjDelta);

        // Since origin is the very first point on the wave, if it reaches zero, there is no other point before it, so we must update its position to be -waveLength for the animation to look continuious!
        if (origin.getX() >= 0 || origin.getX() < -waveLength) { // Must be -waveLength to prevent the ends of the wave from showing
            setWave(-waveLength); // Will be moved 1 cycle to the left. (which will be off screen)

        }
    }

    /**
     * Equals method
     *
     * @param otherWave to test equality with
     * @return return equality statement
     */
    public abstract boolean equals(Waveform otherWave);

    /**
     * Abstract clone method
     *
     * @return wave clone
     */
    public abstract Waveform clone();

    /**
     * Abstract toString method
     *
     * @return string representation of a wave
     */
    public abstract String toString();

    /**
     * Get instance of abstract method
     *
     * @return string of the wave instance
     */
    public abstract String getInstanceOf();

    /**
     * Abstract resolution method call
     *
     * @param resolutionPerCycle represents resolution or amount of points drawn
     * in wave cycle
     * @param f float representation
     */
    public abstract void initilizePointList(float resolutionPerCycle, float f);

}
