/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package audioscope.ChordClasses;
import audioscope.Chord;

/**
 * May 28 2025
 * Major chord subclass
 * @author albaz
 */
public class Major extends Chord{
    /**
     * Constants
     */
    private static float FREQ_RATIO1=(float) 1.25; //major third
    private static float FREQ_RATIO2=(float) 1.5; //perfect fifth

    
    /**
     * Primary constructor
     * @param baseFreq represents the inputted frequency
     */
    public Major(float baseFreq) {
        super(baseFreq, FREQ_RATIO1, FREQ_RATIO2);
    }
    
    
}
