/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package audioscope;

/**
 * May 28 2025 Chord abstract class
 *
 * @author albaz
 */
public abstract class Chord {

    /**
     * Protected instance variables
     */
    private float baseFreq; //represents base frequency
    private float note1; //represents first harmonic frequency
    private float note2; //represents second harmonic frequency
    private float note3; //represents third harmonic frequency (if 7th chord

    /**
     * Constants for each concrete subclass
     */
    private static float FREQ_RATIO1; //frequency ratios for each harmonic
    private static float FREQ_RATIO2;
    private static float FREQ_RATIO3;

    /**
     * Private class variables
     */
    private static float LOW_FREQ = 1; // 1 hertz is the lowest frequency that can be visually displayed in our program
    private static float HIGH_FREQ = 900; //900 hertz is the highest frequency that can be displayed in our program

    /**
     * Primary constructor
     *
     * @param baseFreq represents the inputted base note
     */
    public Chord(float baseFreq) {
        this.baseFreq = baseFreq;
    }

    /**
     * Secondary constructor
     *
     * @param baseFreq represents the inputted base note
     * @param FREQ_RATIO1 represents the ratio to get a necessary harmonic
     * frequency of this note
     * @param FREQ_RATIO2 represents the ratio to get a necessary harmonic
     * frequency of this note
     */
    public Chord(float baseFreq, float FREQ_RATIO1, float FREQ_RATIO2) {
        this.FREQ_RATIO1 = FREQ_RATIO1;
        this.FREQ_RATIO2 = FREQ_RATIO2;
        this.baseFreq = baseFreq;
    }

    /**
     * Tertiary constructor
     *
     * @param baseFreq represents the inputted base note
     * @param FREQ_RATIO1 represents the ratio to get a necessary harmonic
     * frequency of this note
     * @param FREQ_RATIO2 represents the ratio to get a necessary harmonic
     * frequency of this note
     * @param FREQ_RATIO3 represents the ratio to get a necessary harmonic
     * frequency of this note
     */
    public Chord(float baseFreq, float FREQ_RATIO1, float FREQ_RATIO2, float FREQ_RATIO3) {
        this.FREQ_RATIO1 = FREQ_RATIO1;
        this.FREQ_RATIO2 = FREQ_RATIO2;
        this.FREQ_RATIO3 = FREQ_RATIO3;
        this.baseFreq = baseFreq;
    }

    /**
     * Getter for base frequency
     *
     * @return int representing base frequency
     */
    public float getFreq() {
        return baseFreq;
    }

    /**
     * Set frequency as long as its within the range of human hearing
     *
     * @return Boolean representing if frequency is successfully set
     */
    public boolean setFreq(float baseFreq) {
        if (baseFreq >= LOW_FREQ && baseFreq <= HIGH_FREQ) {
            this.baseFreq = baseFreq;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Create notes that play harmoniously with base frequency
     */
    public void createNotes() {
        this.note1 = baseFreq * FREQ_RATIO1;
        this.note2 = baseFreq * FREQ_RATIO2;
        this.note3 = baseFreq * FREQ_RATIO3;
    }

    /**
     * Getter for first harmonic frequency
     *
     * @return float of first harmonic frequency
     */
    public float getNote1() {
        return note1;
    }

    /**
     * Getter for second harmonic frequency
     *
     * @return float of second harmonic frequency
     */
    public float getNote2() {
        return note2;
    }

    /**
     * Getter for third harmonic frequency
     *
     * @return float of third harmonic frequency
     */
    public float getNote3() {
        return note3;
    }

    /**
     * Add all notes to now playing array
     *
     * @param nowPlaying represents the array of notes currently playing
     */
    public void addNotes(float[] nowPlaying) {
        nowPlaying[0] = baseFreq;
        nowPlaying[1] = note1;
        nowPlaying[2] = note2;
        nowPlaying[3] = note3;

    }

    /**
     * Remove notes to now playing array
     *
     * @param nowPlaying represents the array of notes currently playing
     */
    public void clearNotes(float[] nowPlaying) {
        nowPlaying = null; //clear notes in array
    }
}
