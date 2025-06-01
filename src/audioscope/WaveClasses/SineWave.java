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
public class SineWave extends Waveform {

    public ArrayList<Vector2> points = new ArrayList<>();

    public SineWave(Vector2 startPos, int frequency) {
        super(startPos, frequency);

    }

    public SineWave(Vector2 startPos, int frequency, int amplitude, int speed) {
        super(startPos, frequency, amplitude, speed);
    }

    public void drawWave() {

    }

    /**
     * Moves the wave at a designated newOrigin point (moves the entire wave at
     * once), only moves it along the x-axis
     *
     * @param newOrigin - The new origin point as a Vector2
     */
    public void setOrigin(Vector2 newOrigin) {
        // Get the first point in the list (original origin)
        Vector2 origin = points.get(0);

        // Set the X-coordinate of the origin to the new origin's X
        origin.setX(newOrigin.getX());

        // Loop through the remaining points to update their X-positions
        for (int i = 1; i < points.size(); i++) {
            // Get the previous point after its X has already been adjusted
            Vector2 lastPt = points.get(i - 1);

            // Corrects the selected points distance to have the correct distance from the previous point
            points.get(i).setX(lastPt.getX() - (origin.getX() * i));
        }
    }

    public void translateWave(Vector2 shiftVector, int bounds, int start) {
        for (int i = 0; i < points.size(); i++) {
            Vector2 currPoint = points.get(i);
            currPoint.add(shiftVector);

            if (points.get(0).getX() > bounds) {
                setOrigin(new Vector2(start, 0));//reset to start
            }
        }
    }

    public void animate(int waveLength) {
        // Shifts the origin point, since the draw function is being called each frame, the sinewave will then be recreated with this new origin point which "moves" it! 
        startPos.addX(speed);
               
        if (startPos.getX() >= -waveLength) { // Must be -waveLength to prevent the ends of the wave from showing
            startPos.setX(-waveLength * 2); // * 2 so its always 1 cycle away from -waveLength

        }

    }
    
    public void shiftPoints(int x){
        
        for (int i = 0; i < points.size(); i++) {
            points.get(i).addX(x);
            
        }
    
    }

    public void initilizePointList(Graphics g2d, int resolutionPerCycle, int waveLength, int cycles) {
        int C = startPos.getY(); // Vertical shift (baseline of the wave)
        double K = (2 * Math.PI) / waveLength; // Frequency constant for 1 cycle
        int totalResolution = resolutionPerCycle * cycles; // Adjusts for the amount of points to generate by the defined amount of cycles it should coveer
        int increment = waveLength / resolutionPerCycle; // Horizontal distance between points
        int D = startPos.getX(); // Horizontal offset

        // Add startPos to first index
        points.clear();
        points.add(startPos);

        //add new points to list
        for (int i = 1; i <= totalResolution; i++) {

            Vector2 lastPt = points.get(i - 1);

            int newX = lastPt.getX() + increment;
            int newY = (int) (amplitude * Math.sin(K * (newX - D)) + C);

            //Add new point to list
            points.add(i, new Vector2(newX, newY));

            //Draw
            g2d.drawLine(lastPt.getX(), lastPt.getY(), newX, newY);
        }

    }

    public String toString() {
        return "Temp";
    }

    public boolean equals(Waveform otherWave) {
        return false; //Not functional yet
    }

    public boolean clone(Waveform otherWave) {
        return false; //Not functional yet
    }

    public int getSpeed() {
        return this.speed;
    }

}
