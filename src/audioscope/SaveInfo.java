/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package audioscope;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * A utility class that handles saving and loading waveform data such as
 * frequency, amplitude, and wave type to and from a file.
 *
 * This class provides functionality to save the users settings in the
 * visualizer and load them back up
 * 
 * The saveFile file only has 1 field and 3 records. The order is waveType, frequency, amplitude
 *
 * @author cunpl
 */
public class SaveInfo {

    private float frequency;
    private float amplitude;
    private String waveType;
    private File saveFile;

    /**
     * Main Constructor - Stores the file that will be saved to and read from
     *
     * @param saveFile the file to read and store the waveform data to
     */
    public SaveInfo(File saveFile) {

        this.saveFile = saveFile;
        System.out.println("Constructer initialized!"); // Debugging statement

    }

    /**
     * Saves the users presents to the saveFile.
     *
     * @param frequency the frequency value to save
     * @param amplitude the amplitude value to save
     * @param waveType the type of waveform to save (e.g., "Sine", "Square")
     * @param saveFile the file to write the waveform data to -- DELETE LATER!!
     */
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
    
    /**
     * Loads previously saved user presets. Reads from the saveFile and updates the visualizer to math those presets!
     * 
     * @param drawingSurface - The DrawingSurface class that holds all the rendering logic for the wavee
     */
    
    public void load(DrawingSurface drawingSurface) {
        // Read the file and set the attribute values 
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
        // Update visualizer presets!
        drawingSurface.setWaveType(waveType);
        drawingSurface.setFreq(frequency);
        drawingSurface.setAmplitude(amplitude);

    }

}
