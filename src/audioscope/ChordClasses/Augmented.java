/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package audioscope.ChordClasses;
import audioscope.Chord;

/**
 *May 28 2025
 * Augmented chord subclass
 * @author albaz
 */
public class Augmented extends Chord{
    /**
     * Constants
     */
    private static double FREQ_RATIO1=1.25; //major third
    private static double FREQ_RATIO2=  Math.pow(2, 2.0/3.0); //augmented fifth

    
    /**
     * Primary constructor
     * @param baseFreq represents the inputted frequency
     */
    public Augmented (double baseFreq) {
        super(baseFreq, FREQ_RATIO1, FREQ_RATIO2);
    }
}
