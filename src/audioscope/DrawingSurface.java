package audioscope;

import audioscope.ChordClasses.Augmented;
import audioscope.ChordClasses.Diminished;
import audioscope.ChordClasses.Major;
import audioscope.ChordClasses.Major7th;
import audioscope.ChordClasses.Minor;
import audioscope.ChordClasses.Sus2nd;
import audioscope.WaveClasses.SawtoothWave;
import audioscope.WaveClasses.SineWave;
import audioscope.WaveClasses.SquareWave;
import audioscope.WaveClasses.TriangleWave;
import audioscope.WaveClasses.Waveform;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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

    Vector2 origin = new Vector2(0, 250);

    float frequency = 2;
    float scaleFactor = 0.1f;
    float amplitude = 90;
    float speed = 10000;
    float waveLength = (speed / frequency); // Measured in pixels, i.e. 500 pixels per cycle
    float width = getWidth();

    float cycles;
    float resolutionPerCycle = 200;

    //tracks wave type
    String waveType;
    boolean isSine;
    boolean isTriangle;
    boolean isSawtooth;
    boolean isSquare;
    boolean selectedWave;
    boolean isPlaying;

    //tracks chord type
    String chordType;

    long lastTime = System.nanoTime();

    SineWave sine1, sine2, sine3, sine4;
    TriangleWave triangle1, triangle2, triangle3, triangle4;
    SawtoothWave sawtooth1, sawtooth2, sawtooth3, sawtooth4;
    SquareWave square1, square2, square3, square4;

    public void play() {
        isPlaying = true;
    }

    public void pause() {
        isPlaying = false;
    }

    public void setScaleFactor(float scaleFactor) {
        this.scaleFactor = scaleFactor;
        sine1.setScaleFactor(scaleFactor);
        if (sine2 != null) {
            sine2.setScaleFactor(scaleFactor);
        }
        if (sine3 != null) {
            sine3.setScaleFactor(scaleFactor);

        }
        if (sine4 != null) {
            sine4.setScaleFactor(scaleFactor);
        }
        System.out.println("Scale factor: " + scaleFactor);
        repaint();
    }
    
    public float getScaleFactor(){
        return this.scaleFactor;
    }

    public void getChordType(String chordType) {
        this.chordType = chordType;
        Chord chord = null;
        sine2 = sine3 = sine4 = null;
        triangle2 = triangle3 = triangle4 = null;

        if (chordType.equals("Major") && sine1 != null) {
            chord = new Major(frequency);

        } else if (chordType.equals("Minor") && sine1 != null) {
            chord = new Minor(frequency);

        } else if (chordType.equals("Diminished") && sine1 != null) {
            chord = new Diminished(frequency);

        } else if (chordType.equals("Augmented") && sine1 != null) {
            chord = new Augmented(frequency);

        } else if (chordType.equals("Sus2nd") && sine1 != null) { // add 4th note option!!
            chord = new Sus2nd(frequency);

        } else {
            System.out.println("Major7th");
            chord = new Major7th(frequency);
        }
        chord.createNotes();
        this.width = getWidth();

        //make this modular 
        if (waveType.equals("Sine")) {
            sine2 = (SineWave) createHarmonicWave(sine1, chord.getNote1());
            sine3 = (SineWave) createHarmonicWave(sine1, chord.getNote2());
            if (chord instanceof Major7th) {
                sine4 = (SineWave) createHarmonicWave(sine1, chord.getNote3());
            }
        } else if (waveType.equals("Triangle")) {
            triangle2 = (TriangleWave) createHarmonicWave(triangle1, chord.getNote1());
            triangle3 = (TriangleWave) createHarmonicWave(triangle1, chord.getNote2());
            if (chord instanceof Major7th) {
                triangle4 = (TriangleWave) createHarmonicWave(triangle1, chord.getNote3());
            }
        } else if (waveType.equals("Square")) {
            square2 = (SquareWave) createHarmonicWave(square1, chord.getNote1());
            square3 = (SquareWave) createHarmonicWave(square1, chord.getNote2());
            if (chord instanceof Major7th) {
                square4 = (SquareWave) createHarmonicWave(square1, chord.getNote3());
            }
        } else if (waveType.equals("Sawtooth")) {
            sawtooth2 = (SawtoothWave) createHarmonicWave(sawtooth1, chord.getNote1());
            sawtooth3 = (SawtoothWave) createHarmonicWave(sawtooth1, chord.getNote2());
            if (chord instanceof Major7th) {
                sawtooth4 = (SawtoothWave) createHarmonicWave(sawtooth1, chord.getNote3());
            }
        }

        repaint();
    }

    private Waveform createHarmonicWave(Waveform baseWave, float freq) {
        Waveform harmonic = baseWave.clone();
        harmonic.setFrequency(freq);
        harmonic.setWaveLength(speed / freq);
        harmonic.initilizePointList(resolutionPerCycle, width / baseWave.getWaveLength() * 2);
        return harmonic;
    }

    public float getFreq() {
        return frequency;
    }

    public float getAmplitude() {
        return amplitude;
    }

    public String getWaveType() {
        return waveType;
    }

    public void setFreq(float frequency) {
        this.frequency = frequency;
        repaint();
    }

    public void setAmplitude(float amplitude) {
        this.amplitude = amplitude;
        repaint();
    }

    public void setWaveType(String waveType) {
        this.waveType = waveType;
        repaint();
    }

    public void getFrequency(int freq) {
        System.out.println(freq);
        this.frequency = freq;
        this.waveLength = speed / freq;
        this.cycles = getWidth() / waveLength * 2;
        if (isSine && sine1 != null) {
            sine1.setFrequency(freq);
            sine1.setWaveLength(waveLength);
            sine1.initilizePointList(resolutionPerCycle, cycles);
        } else if (isTriangle && triangle1 != null) {
            triangle1.setFrequency(freq);
            triangle1.setWaveLength(waveLength);
            triangle1.initilizePointList(resolutionPerCycle, cycles);
        } else if (isSawtooth && sawtooth1 != null) {
            sawtooth1.setFrequency(freq);
            sawtooth1.setWaveLength(waveLength);
            sawtooth1.initilizePointList(resolutionPerCycle, cycles);
        } else if (isSquare && square1 != null) {
            square1.setFrequency(freq);
            square1.setWaveLength(waveLength);
            square1.initilizePointList(resolutionPerCycle, cycles);
        }
        getChordType(chordType);
    }

    public void getWaveType(String waveType) {
        // The variable below gets the amount of cycles to display to cover the length of the screen
        this.waveType = waveType;
        cycles = getWidth() / waveLength * 2;
        isSine = false;
        isTriangle = false;
        isSawtooth = false;
        selectedWave = true;
        isPlaying = true;
        timer.start();
        switch (waveType) {
            case "Sine":
                //if the wave is a sine wave
                isSine = true;
                sine1 = new SineWave(origin, frequency, amplitude, speed, waveLength, scaleFactor);
                sine1.initilizePointList(resolutionPerCycle, cycles);
                System.out.println("Clicked Sine wave"); // Debugging
                break;
            case "Triangle":
                //if the wave is a triangle wave
                isTriangle = true;
                triangle1 = new TriangleWave(origin, frequency, amplitude, speed, waveLength, scaleFactor);
                triangle1.initilizePointList(resolutionPerCycle, cycles);
                System.out.println("Clicked Triangle wave");
                break;
            case "Sawtooth":
                isSawtooth = true;
                sawtooth1 = new SawtoothWave(origin, frequency, amplitude, speed, waveLength, scaleFactor);
                sawtooth1.initilizePointList(resolutionPerCycle, cycles);
                System.out.println("Clicked Sawtooth wave");
                break;
            case "Square":
                isSquare = true;
                square1 = new SquareWave(origin, frequency, amplitude, speed, waveLength, scaleFactor);
                square1.initilizePointList(resolutionPerCycle, cycles);
                System.out.println("Clicked Square wave");
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
        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - lastTime) / 1_000_000_000.0f;
        lastTime = currentTime;

        if (isPlaying) {
            if (isSine) {
                if (sine1 != null) {
                    sine1.animate(deltaTime);
                }
                if (sine2 != null) {
                    sine2.animate(deltaTime);
                }
                if (sine3 != null) {
                    sine3.animate(deltaTime);
                }
                if (sine4 != null) {
                    sine4.animate(deltaTime);
                }

            } else if (isTriangle) {
                if (triangle1 != null) {
                    triangle1.animate(deltaTime);
                }
                if (triangle2 != null) {
                    triangle2.animate(deltaTime);
                }
                if (triangle3 != null) {
                    triangle3.animate(deltaTime);
                }
                if (triangle4 != null) {
                    triangle4.animate(deltaTime);
                }

            } else if (isSawtooth) { //can simplify
                if (sawtooth1 != null) {
                    sawtooth1.animate(deltaTime);
                }
                if (sawtooth2 != null) {
                    sawtooth2.animate(deltaTime);
                }
                if (sawtooth3 != null) {
                    sawtooth3.animate(deltaTime);
                }
                if (sawtooth4 != null) {
                    sawtooth4.animate(deltaTime);
                }
            } else if (isSquare) {
                if (square1 != null) {
                    square1.animate(deltaTime);
                }
                if (square2 != null) {
                    square2.animate(deltaTime);
                }
                if (square3 != null) {
                    square3.animate(deltaTime);
                }
                if (square4 != null) {
                    square4.animate(deltaTime);
                }
            }
        }
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.BLUE);
        if (isSine) {
            if (sine1 != null) {
                g2d.setColor(Color.BLUE);
                sine1.drawWave(g2d);
            }
            if (sine2 != null) {
                g2d.setColor(Color.RED);
                sine2.drawWave(g2d);
            }
            if (sine3 != null) {
                g2d.setColor(Color.GREEN);
                sine3.drawWave(g2d);
            }
            if (sine4 != null) {
                g2d.setColor(Color.PINK);
                sine4.drawWave(g2d);
            }

        } else if (isTriangle) {
            if (triangle1 != null) {
                g2d.setColor(Color.BLUE);
                triangle1.drawWave(g2d);
            }
            if (triangle2 != null) {
                g2d.setColor(Color.RED);
                triangle2.drawWave(g2d);
            }
            if (triangle3 != null) {
                g2d.setColor(Color.GREEN);
                triangle3.drawWave(g2d);
            }
            if (triangle4 != null) {
                g2d.setColor(Color.PINK);
                triangle4.drawWave(g2d);
            }
        } else if (isSawtooth) {
            if (sawtooth1 != null) {
                g2d.setColor(Color.BLUE);
                sawtooth1.drawWave(g2d);
            }
            if (sawtooth2 != null) {
                g2d.setColor(Color.RED);
                sawtooth2.drawWave(g2d);
            }
            if (sawtooth3 != null) {
                g2d.setColor(Color.GREEN);
                sawtooth3.drawWave(g2d);
            }
            if (sawtooth4 != null) {
                g2d.setColor(Color.PINK);
                sawtooth4.drawWave(g2d);
            }
        } else if (isSquare) {
            if (square1 != null) {
                g2d.setColor(Color.BLUE);
                square1.drawWave(g2d);
            }
            if (square2 != null) {
                g2d.setColor(Color.RED);
                square2.drawWave(g2d);
            }
            if (square3 != null) {
                g2d.setColor(Color.GREEN);
                square3.drawWave(g2d);
            }
            if (square4 != null) {
                g2d.setColor(Color.PINK);
                square4.drawWave(g2d);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
}
