/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package audioscope;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
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

    public SaveInfo(File saveFile) {

        this.saveFile = saveFile;
        try {
            Scanner scanner = new Scanner(this.saveFile);
            while (scanner.hasNextLine()) {
                waveType = scanner.nextLine();
                frequency = Float.parseFloat(scanner.nextLine());
                amplitude = Float.parseFloat(scanner.nextLine());
                
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: " + e);
        }

        System.out.println("Constructer initialized!");

    }

    public void save(float frequency, float amplitude, String waveType, File saveFile) {
        try {
            FileWriter writer = new FileWriter(saveFile);
            writer.write(waveType + "\n" + frequency + "\n" + amplitude);
            writer.close();
            System.out.println("Saved!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void load(DrawingSurface drawingSurface) {
        drawingSurface.setFreq(frequency);
        drawingSurface.setAmplitude(amplitude);
        drawingSurface.setWaveType(waveType);
        
        
        
    }

}
