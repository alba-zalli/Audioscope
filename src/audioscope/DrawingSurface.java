package audioscope;

import audioscope.WaveClasses.SineWave;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Stores animation logic May 29 2025
 *
 * @author albaz
 */
public class DrawingSurface extends JPanel {

    private int x = 0; //xcoordinate for animation
    private Timer timer;

    Vector2 origin = new Vector2(20, 100);
    int frequency = 100;
    int amplitude = 60;
    int speed = 50;
    int waveLength = 500;
    int resolutionPerCycle = 100;

    private boolean sineWave;
    SineWave w1;

    public void getWaveType(String waveType) {
        if (waveType.equals("Sine")) { //if the wave is a sine wave
            sineWave = true;
            w1 = new SineWave(origin, frequency, amplitude, speed);
            timer.start();
        } else if (waveType.equals("Triangle")) { //if the wave is a triangle wave
            sineWave = false;
            w1 = null;
        } else {
            sineWave = false;
            w1 = null;
            timer.stop();
        }
        repaint(); //refresh screen
    }

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
        if (sineWave) {
            //w1.translateWave(new Vector2(w1.getSpeed(), 0), getWidth(), 0);
            w1.animate(waveLength);
        }
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.BLUE);
        if (sineWave) {
            w1.initilizePointList(g, resolutionPerCycle, waveLength, speed);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
}
