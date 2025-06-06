/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package audioscope;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cunpl
 */
public class SaveInfo {
    private float frequency;
    private float amplitude;
    private String waveType;
    private File saveFile;
    
    public SaveInfo(float frequency, float amplitude, String waveType, File saveFile){
        this.frequency = frequency;
        this.amplitude = amplitude;
        this.waveType = waveType;
        this.saveFile = saveFile;
    
    }
    
    public void save(float frequency, float amplitude, String waveType, File saveFile){
        try {
            FileWriter writer = new FileWriter(saveFile);
            writer.write(waveType +"\n" + frequency + "\n" + amplitude);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    
    }
    
}
