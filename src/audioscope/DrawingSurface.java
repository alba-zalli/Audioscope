package audioscope;

import audioscope.ChordClasses.Augmented;
import audioscope.ChordClasses.Diminished;
import audioscope.ChordClasses.Major;
import audioscope.ChordClasses.Major7th;
import audioscope.ChordClasses.Minor;
import audioscope.ChordClasses.Sus2nd;
import audioscope.PlayWaveClasses.AudioThreading;
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
 * Stores animation logic for waves May 29 2025
 *
 * @author albaz
 */
public class DrawingSurface extends JPanel {
/*
    private MediaPlayer mediaPlayer;
    private boolean fxInitialized = false;
*/
    private int x = 0; //xcoordinate for animation
    private Timer timer;

    private Vector2 origin = new Vector2(0, 250);

    private float frequency = 2;
    private float scaleFactor = 0.1f;
    private float amplitude = 90;
    final float speed = 10000;
    private float waveLength = (speed / frequency); // Measured in pixels, i.e. 500 pixels per cycle
    private float width = getWidth();

    private float cycles;
    private float resolutionPerCycle = 200;

    //tracks wave type
    private String waveType;
    private static boolean isSine;
    private static boolean isTriangle;
    private static boolean isSawtooth;
    private static boolean isSquare;
    private boolean isPlaying;

    //tracks chord type
    private Chord chord = null;
    private String chordType;

    //takes the time of starting
    private long lastTime = System.nanoTime();

    //initialize all possible waves
    private SineWave sine1, sine2, sine3, sine4;
    private TriangleWave triangle1, triangle2, triangle3, triangle4;
    private SawtoothWave sawtooth1, sawtooth2, sawtooth3, sawtooth4;
    private SquareWave square1, square2, square3, square4;

    
    /**
     * GETTERS AND SETTERS
     */
    /**
     * Get the base frequency of a wave (in hertz)
     *
     * @return base frequency
     */
    public float getFreq() {
        return frequency;
    }

    public Chord getChord() {
        return chord;
    }

    /**
     * Set frequency of a base wave, the wave which the rest of the chord is
     * built around
     *
     * @param freq represents the base frequency of the wave in hertz
     */
    public void setFreq(float freq) {
        this.frequency = freq; //set the frequency
        baseWaveSound.setFrequency(frequency);
        this.waveLength = speed / freq; //wavelength is speed divided by frequency
        //find the cycles that can be displayed on screen by dividing the 
        //width of the screen by the diameter of two cycles
        this.cycles = getWidth() / waveLength * 2;
        //initialize sine wave if user has selected this option
        if (isSine && sine1 != null) {
            setFreqHelper(sine1, freq);
        } else if (isTriangle && triangle1 != null) { //initialize triangle wave if user has selected this option
            setFreqHelper(triangle1, freq);
        } else if (isSawtooth && sawtooth1 != null) { //initialize sawtooth wave if user has selected this option
            setFreqHelper(sawtooth1, freq);
        } else if (isSquare && square1 != null) { //initialize square wave if user has selected this option
            setFreqHelper(square1, freq);
            setWaveType("Square");
        }
        //setChordType(chordType); //set chord type
    }

    /**
     * Helper method for setFreq method that helps make code more modular
     *
     * @param wave1 desired wave
     * @param freq base frequency
     */
    public void setFreqHelper(Waveform wave1, float freq) {
        wave1.setFrequency(freq); //set the wave's frequency
        wave1.setWaveLength(waveLength); //set the wave's wavelength
        wave1.initilizePointList(resolutionPerCycle, cycles); //set the wave's point list
    }

    /**
     * MAY BE DELETED!! Get the amplitude of a wave (in hertz)
     *
     * @return float value representing amplitude
     */
    public float getAmplitude() {
        return amplitude;
    }

    /**
     * MAY BE DELETED!!! Set the amplitude of the wave (in hertz)
     *
     * @param amplitude represents t
     */
    public void setAmplitude(float amplitude) {
        this.amplitude = amplitude;
        repaint();
    }

    public void setWaveType(String waveType) {
        // The variable below gets the amount of cycles to display to cover the length of the screen
        this.waveType = waveType;
        cycles = getWidth() / waveLength * 2;
        isSine = false;
        isTriangle = false;
        isSawtooth = false;
        isPlaying = true;
        timer.start();
        switch (waveType) {
            case "Sine":
                //if the wave is a sine wave
                isSine = true;
                System.out.println(frequency);
                sine1 = new SineWave(origin, frequency, amplitude, speed, waveLength, scaleFactor);
                sine1.initilizePointList(resolutionPerCycle, cycles);
                
                baseWaveSound.setFrequency(frequency);
                
                
                System.out.println("Clicked Sine wave"); // Debugging
                //playAudios();
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
                timer.stop();
                System.out.println("ERROR! User (somehow) selected a wave type that is not a valid option!");
                break;
        }
        repaint(); //refresh screen
    }

    public String getWaveType() {
        return waveType;
    }
    
    public void setScaleFactor(float scaleFactor) {
        this.scaleFactor = scaleFactor;
        if (isSine) {
            setScaleFactorHelper(sine1, sine2, sine3, sine4);
        } else if (isTriangle) {
            setScaleFactorHelper(triangle1, triangle2, triangle3, triangle4);
        } else if (isSquare) {
            setScaleFactorHelper(square1, square2, square3, square4);
        } else if (isSawtooth) {
            setScaleFactorHelper(sawtooth1, sawtooth2, sawtooth3, sawtooth4);
        }
        repaint();
    }

    public void setScaleFactorHelper(Waveform wave1, Waveform wave2, Waveform wave3, Waveform wave4) {
        wave1.setScaleFactor(scaleFactor);
        if (wave2 != null) {
            wave2.setScaleFactor(scaleFactor);
        }
        if (wave3 != null) {
            wave3.setScaleFactor(scaleFactor);
        }
        if (wave4 != null) {
            wave4.setScaleFactor(scaleFactor);
        }
    }

    public float getScaleFactor() {
        return this.scaleFactor;
    }

    public void setChordType(String chordType) {
        this.chordType = chordType;

        sine2 = sine3 = sine4 = null;
        triangle2 = triangle3 = triangle4 = null;
        sawtooth2 = sawtooth3 = sawtooth4 = null;
        square2 = square3 = square4 = null;

        if (chordType.equals("Major") && waveType != null) {
            chord = new Major(frequency);

        } else if (chordType.equals("Minor") && waveType != null) {
            chord = new Minor(frequency);

        } else if (chordType.equals("Diminished") && waveType != null) {
            chord = new Diminished(frequency);

        } else if (chordType.equals("Augmented") && waveType != null) {
            chord = new Augmented(frequency);

        } else if (chordType.equals("Sus2nd") && waveType != null) { // add 4th note option!!
            chord = new Sus2nd(frequency);

        } else if (chordType.equals("Major7th") && waveType != null) {
            System.out.println("Major7th");
            chord = new Major7th(frequency);
        }
        if (chord != null) {
            chord.createNotes();
        }
        this.width = getWidth();

        //make this modular
        switch (waveType) {
            case "Sine":
                sine2 = (SineWave) createHarmonicWave(sine1, chord.getNote1());
                sine3 = (SineWave) createHarmonicWave(sine1, chord.getNote2());
                
                note1Sound.setFrequency(chord.getNote1());
                note2Sound.setFrequency(chord.getNote2());
                
                if (chord instanceof Major7th) {
                    sine4 = (SineWave) createHarmonicWave(sine1, chord.getNote3());
                    note3Sound.setFrequency(chord.getNote3());
                }
                System.out.println("HII");

                break;
            case "Triangle":
                triangle2 = (TriangleWave) createHarmonicWave(triangle1, chord.getNote1());
                triangle3 = (TriangleWave) createHarmonicWave(triangle1, chord.getNote2());
                if (chord instanceof Major7th) {
                    triangle4 = (TriangleWave) createHarmonicWave(triangle1, chord.getNote3());
                }
                break;
            case "Square":
                square2 = (SquareWave) createHarmonicWave(square1, chord.getNote1());
                square3 = (SquareWave) createHarmonicWave(square1, chord.getNote2());
                if (chord instanceof Major7th) {
                    square4 = (SquareWave) createHarmonicWave(square1, chord.getNote3());
                }
                break;
            case "Sawtooth":
                sawtooth2 = (SawtoothWave) createHarmonicWave(sawtooth1, chord.getNote1());
                sawtooth3 = (SawtoothWave) createHarmonicWave(sawtooth1, chord.getNote2());
                if (chord instanceof Major7th) {
                    sawtooth4 = (SawtoothWave) createHarmonicWave(sawtooth1, chord.getNote3());
                }
                break;
            default:
                break;
        }

        repaint();
    }

    public String getChordType() {
        return chordType;
    }

    private Waveform createHarmonicWave(Waveform baseWave, float freq) {
        Waveform harmonic = baseWave.clone();
        harmonic.setFrequency(freq);
        harmonic.setWaveLength(speed / freq);
        harmonic.initilizePointList(resolutionPerCycle, width / baseWave.getWaveLength() * 2);
        return harmonic;
    }

    /**
     * PLAY AND PAUSE LOGIC
     */
    public void play() {
        isPlaying = true;
    }

    public void pause() {
        isPlaying = false;
    }
    
    /**
     * AUDIO LOGIC
     */
    
    private final AudioThreading baseWaveSound = new AudioThreading();
    private final AudioThreading note1Sound = new AudioThreading();
    private final AudioThreading note2Sound = new AudioThreading();
    private final AudioThreading note3Sound = new AudioThreading();
    
    public void initilizeSound(){
        if(isSine){
            baseWaveSound.stopAudio();
            note1Sound.stopAudio();
            note2Sound.stopAudio();
            note3Sound.stopAudio();
            setWaveType("Sine");
        }
        else if(isSquare){
            baseWaveSound.stopAudio();
            note1Sound.stopAudio();
            note2Sound.stopAudio();
            note3Sound.stopAudio();
            setWaveType("Square");
        }
        baseWaveSound.start();
        note1Sound.start();
        note2Sound.start();
        note3Sound.start();
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
