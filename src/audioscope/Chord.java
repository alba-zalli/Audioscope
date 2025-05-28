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
    private double baseFreq;
    private double note1;
    private double note2;
    private double note3;

    /**
     * Constants for each concrete subclass
     */
    private static double FREQ_RATIO1;
    private static double FREQ_RATIO2;
    private static double FREQ_RATIO3;

    /**
     * Private class variables
     */
    private static double LOW_FREQ = 20; //20 hertz is the lowest frequency heard by humans
    private static double HIGH_FREQ = 20000; //20,000 hertz is the highest frequency heard by humans

    /**
     * Primary constructor
     *
     * @param baseFreq represents the inputted base note
     */
    public Chord(double baseFreq) {
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
    public Chord(double baseFreq, double FREQ_RATIO1, double FREQ_RATIO2) {
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
    public Chord(double baseFreq, double FREQ_RATIO1, double FREQ_RATIO2, double FREQ_RATIO3) {
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
    public double getFreq() {
        return baseFreq;
    }

    /**
     * Set frequency as long as its within the range of human hearing
     *
     * @return Boolean representing if frequency is successfully set
     */
    public boolean setFreq(double baseFreq) {
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
     * Add all notes to now playing array
     *
     * @param nowPlaying represents the array of notes currently playing
     */
    public void addNotes(double[] nowPlaying) {
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
    public void clearNotes(double[] nowPlaying) {
        nowPlaying = null; //clear notes in array
    }
}
