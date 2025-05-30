package audioscope;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Stores animation logic
 * May 29 2025
 * @author albaz
 */
public class DrawingSurface extends JPanel {

    private int x = 0; //xcoordinate for animation
    private Timer timer;

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
        x += 2; //move 2 pixels every frame
        if (x > getWidth()) {
            x = 0; //reset to start
        }
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.setColor(Color.BLUE);
        g2d.fillOval(x, 50, 30, 30); //(x, y, width, height)

        g2d.setColor(Color.RED);
        for (int i = 0; i < 100; i++) {
            int px = (int) (Math.random() * getWidth());
            int py = (int) (Math.random() * getHeight());
            g2d.drawLine(px, py, px, py); //dots
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
}
