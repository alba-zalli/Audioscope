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

    //Declare/initalize a lot of variables...
    
    
    //visual wave variables
    private Timer timer; //timing wave interaction

    private Vector2 origin = new Vector2(0, 250);

    //transformations of a wave
    private float frequency = 2;
    private float scaleFactor = 0.1f;
    private float amplitude = 90;
    private float speed = 10000;
    private float waveLength = (speed / frequency); // Measured in pixels, i.e. 500 pixels per cycle
    private float width = getWidth(); //get width of draw panel
    
    // Cycles parameters and resolution
    private float cycles;
    private float resolutionPerCycle = 200;

    //tracks wave type
    private String waveType = ""; //tracks wave type
    private boolean selectedWave; //tracks if a wave is selected
    private boolean isPlaying; //tracks if waves are praying

    //tracks chord type
    private Chord chord = null; //set chord to null to begin with
    private String chordType; //track chord type

    //takes the time of last frame (used to calculate delta time
    private long lastTime = System.nanoTime();

    //hold waves in an array. Index at 0 is the baseWave for all the arrays! every other index hold its notes!
    private static int arraySize = 4;
    private static SineWave[] sineWaves = new SineWave[arraySize];
    private static TriangleWave[] triangleWaves = new TriangleWave[arraySize];
    private static SawtoothWave[] sawtoothWaves = new SawtoothWave[arraySize];
    private static SquareWave[] squareWaves = new SquareWave[arraySize];

    // Hold audio threads in an array. Index at 0 is always baseWave sound, every otther index holds the sounds for its notes
    private static final int soundArraySize = 4;
    private static AudioThreading[] waveSounds = new AudioThreading[soundArraySize]; // Note

    // Color list, manually declared with 4 colors with ordered by what colour each wave in the wave arrays will be. Index of 0 is color for base wave, everything after that is color for all other notes!
    private static final Color[] waveColorList = {Color.BLUE, Color.RED, Color.GREEN, Color.PINK};

    /**
     * GETTERS AND SETTERS
     */
    /**
     * Get the amplitude of a wave (in hertz)
     *
     * @return float value representing amplitude
     */
    public float getAmplitude() {
        return amplitude;
    }

    /**
     * Setter for volume, accessed in drawing surface and changes the volume for
     * every waveSound in waveSound array
     *
     * @param volume - The new volume to set to (between 0.0 and 1.0, if 0.0
     * representing as 0% and 1.0 as 100%)
     */
    public void setVolume(float volume) {
        for (int i = 0; i < waveSounds.length; i++) {
            waveSounds[i].changeVolume(volume);

        }
    }

    /**
     * Set the amplitude of the wave (in hertz)
     *
     * @param amplitude represents t
     */
    public void setAmplitude(float amplitude) {
        this.amplitude = amplitude;
        repaint();
    }

    /**
     * Get the base frequency of a wave (in hertz)
     *
     * @return base frequency
     */
    public float getFreq() {
        return frequency;
    }

    /**
     * Getter for chord type
     *
     * @return chord data type
     */
    public Chord getChord() {
        return chord;
    }

    /**
     * Set frequency of a base wave, the wave which the rest of the chord is
     * built around
     *
     * @param frequency represents the base frequency of the wave in hertz
     */
    public void setFreq(float frequency) {
        this.frequency = frequency; //set the frequency
        waveSounds[0].setFrequency(frequency);
        waveLength = speed / frequency; //wavelength is speed divided by frequency
        cycles = getWidth() / waveLength * 2;
        //initialize sine wave if user has selected this option
        switch (waveType) {
            case "Sine": //checks if wave is sine, allows frequency to be set for sine array
                setFreqHelper(sineWaves[0]);
                break;
            case "Triangle": //checks if wave is triangle, if so allows frequency to be set for triangle array
                setFreqHelper(triangleWaves[0]);
                break;
            case "Square": //checks if wave is square, if so allows frequency to be set for square array
                setFreqHelper(squareWaves[0]);
                break;
            case "Sawtooth": //checks if wave is sawtooth, if so allows frequency to be set for sawtooth array
                setFreqHelper(sawtoothWaves[0]);
                break;
            default: //if fits none of these, it is not a valid wave type
                System.out.println("ERROR! Variable: waveType. Is not set to a valid preset!");
                break;

        }
    }

    /**
     * Helper method for setFreq method that helps make code more modular
     *
     * @param wave1 desired wave
     */
    public void setFreqHelper(Waveform wave1) {
        if (wave1 != null) {
            wave1.setFrequency(frequency); //set the wave's frequency
            wave1.setWaveLength(waveLength); //set the wave's wavelength
            wave1.initilizePointList(resolutionPerCycle, cycles); //set the wave's point list
        }
    }

    /**
     * Updates sound type (if its square or sine, etc) for ALL the wave sounds
     * in the array!
     */
    public void updateSoundType() {
        for (int i = 0; i < waveSounds.length; i++) {
            waveSounds[i].setWaveType(waveType);

        }
    }

    /**
     * Sets the type of the wave, wether that is triangle, sine, sawtooth, or
     * square
     *
     * @param waveType string value representing the specific instance of
     * waveform class
     */
    public void setWaveType(String waveType) {
        // The variable below gets the amount of cycles to display to cover the length of the screen
        this.waveType = waveType; //pass wavetype to class variable
        updateSoundType(); //update sound type
        cycles = getWidth() / waveLength * 2;
        isPlaying = true; //the wave is playing
        timer.start(); //start timer
        switch (waveType) {
            case "Sine":
                //if the wave is a sine wave, load the wave into the sineWaves array, and initialize pointlist
                sineWaves[0] = new SineWave(origin, frequency, amplitude, speed, waveLength, scaleFactor);
                sineWaves[0].initilizePointList(resolutionPerCycle, cycles);
                break;
            //if the wave is a triangle wave, load the wave into the triangleWaves array, and initialize pointlist
            case "Triangle":
                triangleWaves[0] = new TriangleWave(origin, frequency, amplitude, speed, waveLength, scaleFactor);
                triangleWaves[0].initilizePointList(resolutionPerCycle, cycles);
                break;
            //if the wave is a sawtooth wave, load the wave into the sawtoothWaves array, and initialize pointlist
            case "Sawtooth":
                sawtoothWaves[0] = new SawtoothWave(origin, frequency, amplitude, speed, waveLength, scaleFactor);
                sawtoothWaves[0].initilizePointList(resolutionPerCycle, cycles);
                break;
            case "Square":
                //if the wave is a square wave, load the wave into the squareWaves array, and initilaize pointslist
                squareWaves[0] = new SquareWave(origin, frequency, amplitude, speed, waveLength, scaleFactor);
                squareWaves[0].initilizePointList(resolutionPerCycle, cycles);
                break;
            default:
                //the wave selected is not valid
                selectedWave = false;
                isPlaying = false;
                System.out.println("ERROR! User selected a wave type that is not a valid option!");
                break;
        }
        repaint(); //refresh screen
    }

    /**
     * Getter for wave type
     *
     * @return string representation of waveType
     */
    public String getWaveType() {
        return waveType;
    }

    /**
     * Setter for scale factor
     *
     * @param scaleFactor is a float representing the visualized wave speed
     */
    public void setScaleFactor(float scaleFactor) {
        this.scaleFactor = scaleFactor; //set global variable to parameter

        switch (waveType) { //if wave type is sine, pass nessary info to scale factor helper
            case "Sine":
                setScaleFactorHelper(sineWaves);
                break;
            case "Triangle": //if wave type is triangle, pass nessary info to scale factor helper
                setScaleFactorHelper(triangleWaves);
                break;
            case "Square": //if wave type is square, pass nessary info to scale factor helper
                setScaleFactorHelper(squareWaves);
                break;
            case "Sawtooth": //if wave type is sawtooth, pass nessary info to scale factor helper
                setScaleFactorHelper(sawtoothWaves);
                break;
            default: //otherwise, leave switch statement
                break;
        }
        repaint();
    }

    /**
     * Helper function for setting the scale factor This function is given an
     * array of Waveform and if the location is not == null, then the scale
     * factor is set to the scale factor attribute!
     *
     * @param waveArray - The inputted waves to change the scale factor of!
     */
    public void setScaleFactorHelper(Waveform[] waveArray) {
        for (int i = 0; i < waveArray.length; i++) {
            if (waveArray[i] != null) {
                waveArray[i].setScaleFactor(scaleFactor);
            }
        }
    }

    /**
     * Getter for scale factor
     *
     * @return the scale factor
     */
    public float getScaleFactor() {
        return this.scaleFactor;
    }

    /**
     * Getter for chord type
     *
     * @return the chord data type
     */
    public String getChordType() {
        return chordType;
    }

    /**
     * Setter for chord type
     *
     * @param chordType is a string representing the current chordType
     */
    public void setChordType(String chordType) {
        this.chordType = chordType;

        switch (chordType) {
            case "Major": //if chordtype is major, create new major chord
                chord = new Major(frequency);
                break;
            case "Minor": //if chordtype is minor, create new minor chord
                chord = new Minor(frequency);
                break;
            case "Diminished": //if chordtype is diminished, create new diminished chord
                chord = new Diminished(frequency);
                break;
            case "Augmented": //if chordtype is augmented, create new augmented chord
                chord = new Augmented(frequency);
                break;
            case "Sus2nd": //if chordtype is suspended 2nd, create new suspended 2nd chord
                chord = new Sus2nd(frequency);
                break;
            case "Major7th": //if chordtype is major 7th, create new major 7th chord
                chord = new Major7th(frequency);
                break;
            default: //otherwise escape switch statement
                break;
        }
        this.width = getWidth();

        //if there is a chord, create notes
        if (chord != null) {
            chord.createNotes();

            // Reset third note freq to 0 to not make sound, if user changes from 7th to other chord, 
            //it updates since all other chords only have 2 notes not 3!
            waveSounds[3].setFrequency(0);

            switch (waveType) {
                case "Sine": //if the wave is sine
                    SineWave baseSine = sineWaves[0]; //set basewave
                    sineWaves[1] = (SineWave) createHarmonicWave(baseSine, chord.getNote1()); //set harmonic waves visually
                    sineWaves[2] = (SineWave) createHarmonicWave(baseSine, chord.getNote2());

                    waveSounds[1].setFrequency(chord.getNote1()); //pass frequency of these notes to audio array
                    waveSounds[2].setFrequency(chord.getNote2());

                    if (chord instanceof Major7th) { //if the chord is major 7th, load the third note
                        sineWaves[3] = (SineWave) createHarmonicWave(baseSine, chord.getNote3());
                        waveSounds[3].setFrequency(chord.getNote3());
                    }
                    System.out.println("HII");

                    break;
                case "Triangle": //if the wave is triangle
                    TriangleWave baseTriangle = triangleWaves[0]; // set basewave
                    triangleWaves[1] = (TriangleWave) createHarmonicWave(baseTriangle, chord.getNote1()); // set harmonic waves visually
                    triangleWaves[2] = (TriangleWave) createHarmonicWave(baseTriangle, chord.getNote2());

                    waveSounds[1].setFrequency(chord.getNote1()); // Pass frequency of these notes to audio array
                    waveSounds[2].setFrequency(chord.getNote2());

                    if (chord instanceof Major7th) { // if the chord is major 7th, load the third note
                        triangleWaves[3] = (TriangleWave) createHarmonicWave(baseTriangle, chord.getNote3());
                        waveSounds[3].setFrequency(chord.getNote3());

                    }
                    break;
                case "Square": // if wave is square
                    SquareWave baseSquare = squareWaves[0]; // set basewave
                    squareWaves[1] = (SquareWave) createHarmonicWave(baseSquare, chord.getNote1()); // set harmonic waves visually
                    squareWaves[2] = (SquareWave) createHarmonicWave(baseSquare, chord.getNote2());

                    waveSounds[1].setFrequency(chord.getNote1()); // Pass frequency of these notes to audio array
                    waveSounds[2].setFrequency(chord.getNote2());

                    if (chord instanceof Major7th) { // if chord is major 7th, load the third note
                        squareWaves[3] = (SquareWave) createHarmonicWave(baseSquare, chord.getNote3());
                        waveSounds[3].setFrequency(chord.getNote3());
                    }
                    break;
                case "Sawtooth": // If wave is sawtooth
                    SawtoothWave baseSawtooth = sawtoothWaves[0]; // Set the basewave
                    sawtoothWaves[1] = (SawtoothWave) createHarmonicWave(baseSawtooth, chord.getNote1());// set harmonic waves visually 
                    sawtoothWaves[2] = (SawtoothWave) createHarmonicWave(baseSawtooth, chord.getNote2());

                    waveSounds[1].setFrequency(chord.getNote1()); // Pass frequency of these notes to audio array
                    waveSounds[2].setFrequency(chord.getNote2());

                    if (chord instanceof Major7th) { // if the chord is major 7th, load the third note
                        sawtoothWaves[3] = (SawtoothWave) createHarmonicWave(baseSawtooth, chord.getNote3());
                        waveSounds[3].setFrequency(chord.getNote3());
                    }
                    break;
                default: // Default otherwise
                    break;
            }
        }
        repaint();
    }

    /**
     * SOUND INTERACTION
     */
    /**
     * Initialize sound waves Only ones once when AudioscopeGUI is created!
     */
    public static void initilizeSound() {
        for (int i = 0; i < waveSounds.length; i++) { //for each spot in the wavesound array
            if (waveSounds[i] == null) { //check if null
                waveSounds[i] = new AudioThreading(); //create a new thread to run the audio in
                waveSounds[i].start(); //start thread
            }
        }
    }

    /**
     * Create harmonic waves modeled after a base frequency
     *
     * @param baseWave represents the base wave to model harmonic wave after
     * @param freq represents specified base frequency
     * @return the harmonic wave based after base wave
     */
    private Waveform createHarmonicWave(Waveform baseWave, float freq) {
        Waveform harmonic = baseWave.clone(); //clone wave

        harmonic.setFrequency(freq); //set frequency
        harmonic.setWaveLength(speed / freq); //set wavelength

        //initialize point list for visual interaction
        harmonic.initilizePointList(resolutionPerCycle, width / baseWave.getWaveLength() * 2);
        return harmonic; //return the new wave
    }

    /**
     * Clear the harmonies based off a base wave
     */
    public void clearHarmonicWave() {
        chordType = null;
        chord = null;
        // Stop sound for harmonic waves
        for (int i = 1; i < waveSounds.length; i++) {
            if (waveSounds[i] != null) {
                waveSounds[i].setFrequency(0); // Stop harmonic sound
            }
        }

        // Remove harmonic visuals
        switch (waveType) {
            case "Sine":
                //clear sine waves harmonics part of the array if sine wave is the wave type
                sineWaves[1] = null;
                sineWaves[2] = null;
                sineWaves[3] = null;
                break;
            case "Triangle":
                //clear triangle waves harmonics part of the array if sine wave is the wave type
                triangleWaves[1] = null;
                triangleWaves[2] = null;
                triangleWaves[3] = null;
                break;
            case "Square":
                //clear square waves harmonics part of the array if sine wave is the wave type
                squareWaves[1] = null;
                squareWaves[2] = null;
                squareWaves[3] = null;
                break;
            case "Sawtooth":
                //clear sawtooth waves harmonics part of the array if sine wave is the wave type
                sawtoothWaves[1] = null;
                sawtoothWaves[2] = null;
                sawtoothWaves[3] = null;
                break;
            default: // Otherwise, escape switch statement
                break;
        }

        repaint(); // Update UI
    }

    /**
     * PLAY AND PAUSE LOGIC
     */
    /**
     * Play wave visually
     */
    public void play() {
        isPlaying = true;
    }

    /**
     * Pause wave visually
     */
    public void pause() {
        isPlaying = false;
    }

    /**
     * ANIMATION LOGIC
     */
    /**
     * Drawing surface where waves are animated
     */
    public DrawingSurface() {
        //60 fps
        timer = new Timer(16, new ActionListener() { //set to 60 fps
            public void actionPerformed(ActionEvent e) {
                updateAnimation();
                repaint();
            }
        });
        timer.start(); //start timer
    }

    /**
     * Works with updateAnimation class to make code more modular
     *
     * @param waveArray represents current waves to animate
     * @param deltaTime represents change in time
     */
    private void animationHelper(Waveform[] waveArray, float deltaTime) {
        for (int i = 0; i < waveArray.length; i++) {
            if (waveArray[i] != null) {
                waveArray[i].animate(deltaTime);
            }
        }
    }

    /**
     * Updates animation
     */
    private void updateAnimation() {
        long currentTime = System.nanoTime(); //takes current time

        //calculate change in time from program start to method call
        float deltaTime = (currentTime - lastTime) / 1_000_000_000.0f;
        lastTime = currentTime;

        if (isPlaying) { //if the animation is playing, animate each wave accordingly
            switch (waveType) {
                case "Sine":
                    animationHelper(sineWaves, deltaTime);
                    break;
                case "Triangle":
                    animationHelper(triangleWaves, deltaTime);
                    break;
                case "Sawtooth":
                    animationHelper(sawtoothWaves, deltaTime);
                    break;
                case "Square":
                    animationHelper(squareWaves, deltaTime);
                    break;
                default: // If none valid, escape switch statement
                    break;
            }
        }
    }

    private void drawHelper(Waveform[] waves, Graphics2D g2d) {
        for (int i = 0; i < waves.length; i++) { //for each index in wave array
            if (waves[i] != null) { //check if index is null
                g2d.setColor(waveColorList[i]); //set colour accordingly
                waves[i].drawWave(g2d); //draw wave

            }
        }
        // 4th wave only if chord is major7th since major7th has 3 notes.
        // If user changes from major7th it must be set to null to stop dispalying it!
        if (chord != null && !(chord instanceof Major7th)) {
            waves[3] = null;

        }
    }

    /**
     * Drawing method
     *
     * @param g represents graphics component
     */
    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        //set rendering to smooth waves and minimize pixelation
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //set background colour to white
        g2d.setColor(Color.WHITE);

        //fill based on the size of the parent component
        g2d.fillRect(0, 0, getWidth(), getHeight());

        switch (waveType) { //go through each wave type and draw accordingly
            case "Sine":
                drawHelper(sineWaves, g2d);
                break;
            case "Triangle":
                drawHelper(triangleWaves, g2d);
                break;
            case "Sawtooth":
                drawHelper(sawtoothWaves, g2d);
                break;
            case "Square":
                drawHelper(squareWaves, g2d);
                break;
            default: //if no option is selected, exit
                break;
        }
    }

    @Override
    protected void paintComponent(Graphics g
    ) {
        super.paintComponent(g);
        doDrawing(g);
    }
}
