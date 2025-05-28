/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package audioscope.ChordClasses;
import audioscope.Chord;

/**
 * May 28 2025
 * Minor chord subclass
 * @author albaz
 */
public class Minor extends Chord{
    /**
     * Constants
     */
    private static double FREQ_RATIO1=1.2; //minor third
    private static double FREQ_RATIO2=1.5; //perfect fifth

    
    /**
     * Primary constructor
     * @param baseFreq represents the inputted frequency
     */
    public Minor(double baseFreq) {
        super(baseFreq, FREQ_RATIO1, FREQ_RATIO2);
    }
    
    
}
