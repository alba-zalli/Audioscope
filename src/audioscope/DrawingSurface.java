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
    int speed = 20;
    int waveLength = 500;
    int resolutionPerCycle = 100;
    
    SineWave test = new SineWave(origin, frequency, amplitude, speed);

    
    
    public DrawingSurface() {
        //60 fps
        timer = new Timer(16, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateAnimation();
                repaint();
            }
        });
        timer.start();
    }

    private void updateAnimation() {
        /*
        x += 2; //move 2 pixels every frame
        if (x > getWidth()) {
            x = 0; //reset to start
        }
         */

       test.translateWave(new Vector2(test.getSpeed(), 0), getWidth(), 0);

    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.BLUE);
        System.out.println(getWidth());
        test.initilizePointList(g, resolutionPerCycle, waveLength, 4);
        
        test.setOrigin(new Vector2(0,0));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
}
