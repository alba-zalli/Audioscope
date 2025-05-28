/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package audioscope.ChordClasses;
import audioscope.Chord;

/**
 *May 28 2025
 * Suspended 2nd chord subclass
 * @author albaz
 **/

public class Sus2nd extends Chord{
    /**
     * Constants
     */
    private static double FREQ_RATIO1= 1.125; //suspended 2nd 
    private static double FREQ_RATIO2= 3.0/2.0 ; //perfect fifth

    
    /**
     * Primary constructor
     * @param baseFreq represents the inputted frequency
     */
    public Sus2nd (double baseFreq) {
        super(baseFreq, FREQ_RATIO1, FREQ_RATIO2);
    }
}
