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
 * This class provides functionality to save the users base wave settings and load them back up
 * later
 *
 * The saveFile file only has 1 field and 2 records. The order is waveType, frequency
 *
 * @author Ameer Abd El-Fatah June 11 2025
 */
public class SaveInfo {
    // ------ Private attributes -----
    private float frequency; // Frequency variable that was read from the file
    private String waveType; // WaveType variable that was read from the file
    private File saveFile; // The file to save and write to!

    /**
     * Main Constructor - Creates the data type and stores the file that will be saved to and read from
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
     * @param waveType the type of waveform to save (e.g., "Sine", "Square")
     */
    public void save(float frequency, String waveType) {
        try {
            FileWriter writer = new FileWriter(saveFile);
            writer.write(waveType + "\n" + frequency); // Writes the 2 records
            writer.close(); // Finish writing
            System.out.println("Saved!"); // Debugging statement
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Loads previously saved user presets. Reads from the saveFile and updates
     * the visualizer to math those presets!
     *
     * @param drawingSurface - The DrawingSurface class that holds all the
     * rendering logic for the waves
     */
    public void load(DrawingSurface drawingSurface) {
        // Read the file and set the attribute values 
        try {
            Scanner scanner = new Scanner(this.saveFile);
            while (scanner.hasNextLine()) {
                waveType = scanner.nextLine();
                frequency = Float.parseFloat(scanner.nextLine());
                //amplitude = Float.parseFloat(scanner.nextLine());

            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: " + e);
        }
        // Update visualizer presets!
        drawingSurface.setWaveType(waveType);
        drawingSurface.setFreq(frequency);
        //drawingSurface.setAmplitude(amplitude);

    }

    /**
     * Getter for frequency
     *
     * @return frequency (in Hz)
     */
    public float getFrequency() {
        return frequency;
    }

    public void setFrequency(float frequency) {
        this.frequency = frequency;
    }

    /**
     * Getter for waveType
     *
     * @return waveType
     */
    public String getWaveType() {
        return waveType;
    }

    /**
     * Setter for waveType
     *
     * @param waveType if the wave is Sine, Sawtooth, etc.
     */
    public void setWaveType(String waveType) {
        this.waveType = waveType;
    }

    /**
     * Getter for saveFile
     *
     * @return saveFile
     */
    public File getSaveFile() {
        return saveFile;
    }

    /**
     * Setter for save file
     *
     * @param saveFile the file to save to
     */
    public void setSaveFile(File saveFile) {
        this.saveFile = saveFile;
    }

}
