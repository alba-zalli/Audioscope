/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package audioscope;

/**
 * May 28 2025
 * Major chord subclass
 * @author albaz
 */
public class Major extends Chord{
    /**
     * Constants
     */
    private static double FREQ_RATIO1=1.25; //major third
    private static double FREQ_RATIO2=1.5; //perfect fifth

    
    /**
     * Primary constructor
     * @param baseFreq represents the inputted frequency
     */
    public Major(double baseFreq) {
        super(baseFreq, FREQ_RATIO1, FREQ_RATIO2);
    }
    
    
}
