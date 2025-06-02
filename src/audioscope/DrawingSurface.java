package audioscope;

import audioscope.WaveClasses.SawtoothWave;
import audioscope.WaveClasses.SineWave;
import audioscope.WaveClasses.TriangleWave;
import audioscope.WaveClasses.Waveform;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import audioscope.Vector2;


/**
 * Stores animation logic May 29 2025
 *
 * @author albaz
 */
public class DrawingSurface extends JPanel {

    private int x = 0; //xcoordinate for animation
    private Timer timer;

    Vector2 origin = new Vector2(0, 100);
    int frequency = 100;
    int amplitude = 60;
    int speed = 4;
    float waveLength = 500.0f; // Measured in pixels, i.e. 500 pixels per cycle
    float cycles;
    int resolutionPerCycle = 100;
   
    boolean sineWave;
    SineWave w1;

    boolean isSine;
    boolean isTriangle;
    boolean isSawtooth;
    boolean selectedWave;
   

 

    SineWave sine1;
    TriangleWave triangle1;
    SawtoothWave sawtooth1;

    public void getFrequency(int freq){
        
    }
    
    public void getProjectMode(boolean chordMode) { //true is chord mode, false is manual
        if (chordMode) {

        }
    }


    public void getWaveType(String waveType) {
       /*
        if (waveType.equals("Sine")) { //if the wave is a sine wave
            sineWave = true;
            w1 = new SineWave(origin, frequency, amplitude, speed, waveLength);
            timer.start();
        } else if (waveType.equals("Triangle")) { //if the wave is a triangle wave
            sineWave = false;
            w1 = null;
        } else if (waveType.equals("Sawtooth")) {
            sineWave = false;
            w1 = null;
        } else if (waveType.equals("Square")) {
            sineWave = false;
            w1 = null;
        } else {
            sineWave = false;
            w1 = null;
            timer.stop();
*/
            // The variable bellow gets the amount of cycles to display to cover the length of the screen
        cycles = getWidth() / waveLength;
        isSine = false;
        isTriangle = false;
        isSawtooth = false;
        selectedWave = true;
        timer.start();

        switch (waveType) {
            case "Sine":
                //if the wave is a sine wave
                isSine = true;
                sine1 = new SineWave(origin, frequency, amplitude, speed, waveLength);
                sine1.initilizePointList(resolutionPerCycle, cycles);

                System.out.println("Clicked Sine wave"); // Debugging
                break;
            case "Triangle":
                //if the wave is a triangle wave
                isTriangle = true;
                triangle1 = new TriangleWave(origin, frequency, amplitude, speed, waveLength);
                triangle1.initilizePointList(cycles);

                System.out.println("Clicked Triangle wave");
                break;
            case "Sawtooth":
                isSawtooth = true;
                sawtooth1 = new SawtoothWave(origin, frequency, amplitude, speed, waveLength);
                sawtooth1.initilizePointList(cycles);
               
                System.out.println("Clicked Sawtooth wave");
                break;
            default:
                selectedWave = false;
                timer.stop();
                break;
        }
        repaint(); //refresh screen
    }

    /**
     * ANIMATION LOGIC
     */
    public DrawingSurface() {
        //60 fps
        timer = new Timer(16, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateAnimation();
                repaint();
            }
        });
    }

    private void updateAnimation() {
/*
        if (sineWave) {
            //w1.translateWave(new Vector2(w1.getSpeed(), 0), getWidth(), 0);
            w1.animate(waveLength);
*/
        if (isSine) {
            sine1.animate();
        } else if (isTriangle) {
            triangle1.animate();
        } else if (isSawtooth){
            sawtooth1.animate();
        }
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.BLUE);
/*
        if (sineWave) {
            int cycles = (int) Math.ceil((double) getWidth() / waveLength) + 2; // +2 ensures it starts offscreen and scrolls in
            w1.initilizePointList(g, resolutionPerCycle, waveLength);
*/
        if (isSine) {
            sine1.drawWave(g2d);
        } else if (isTriangle) {
            triangle1.drawWave(g2d);
        } else if (isSawtooth){
            sawtooth1.drawWave(g2d);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
}
