/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package audioscope.WaveClasses;

import audioscope.Vector2;

/**
 *
 * @author AmAbd4146
 */
public class SineWave extends Waveform {

    public SineWave(Vector2 startPos, int frequency){
        super(startPos, frequency);
    
    }

    public SineWave(Vector2 startPos, int frequency, int amplitude, int speed){
        super(startPos, frequency, amplitude, speed);
    }
    public String toString(){
       return "Temp";
    }
}
