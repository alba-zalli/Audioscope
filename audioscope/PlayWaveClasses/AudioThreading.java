/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package audioscope.PlayWaveClasses;

import javax.sound.sampled.*;

/**
 * This AudioThreading class generates and plays audio in seperate threads
 * allowing more then 1 sound to play.
 *
 * @author Ameer Abd El-Fatah
 */
public class AudioThreading extends Thread {

    // Waveform type
    private volatile String waveType = "Sine";

    // Waveform frequency in hertz
    private volatile float frequency = 0f; // Can be changed safely from outside

    // Volume (between 0.0 to 1.0)
    private volatile float volume = 0.1f;

    private final int SAMPLE_RATE = 44100; // Sampling rate (how many samples to create per second)
    private final int BUFFER_DURATION_MS = 1000; // Buffer duration in milliseconds, Shorter buffer = faster response
    private final int numSamples = (int) ((BUFFER_DURATION_MS / 1000.0) * SAMPLE_RATE); // The amount of samples to create according to the buffer duration.
    private final byte[] buffer = new byte[2 * numSamples]; // the buffer array, where we write our waveform audio samples so it can be outputted by our speakers!

    private boolean running = true;

    /**
     * Main execution method of the thread. This sets up the audio line and
     * loops to continously to write the sounds for the wave form.
     */
    @Override
    public void run() {
        // Define the audio format
        AudioFormat format = new AudioFormat(SAMPLE_RATE, 16, 1, true, false);

        try (SourceDataLine line = AudioSystem.getSourceDataLine(format)) {
            line.open(format);
            line.start();

            //This loop bellow will contiously generate new audio info everytime its done writing.
            // If is running = false, this loop exists and stops playing.
            while (running) {
                generateSineWaveBuffer(frequency); // Generate the sound buffer
                line.write(buffer, 0, buffer.length); // Output audio
            }

            line.drain();
            line.stop(); // Stop audio line

        } catch (LineUnavailableException e) {
            System.out.println("Audio error: " + e);
        }
    }

    /**
     * Fills the buffer array with the generated audio according to the
     * waveform. Can write Sine, Square, Triangle, and sawtooth waves!
     *
     * @param freq The frequency of the waveform in Hz
     */
    private void generateSineWaveBuffer(float freq) {
        double twoPiF = 2.0 * Math.PI * freq;
        for (int i = 0; i < numSamples; i++) {
            double t = i / (double) SAMPLE_RATE; // Time in secoonds
            short sample = (short) (Math.sin(twoPiF * t) * volume * Short.MAX_VALUE);

            switch (waveType) {
                case "Square":
                    // Square wave formula: Just the sine formula but whenever sines y value is positive, the square wave is at +1
                    // and whenever the sines y value is a negative, the square waves y value is -1;
                    sample = (short) ((Math.sin(twoPiF * t) >= 0 ? 1 : -1) * volume * Short.MAX_VALUE);
                    break;
                case "Triangle":
                    // Triangle wave formula: Users math.abs and math.floor to createe the triangles linear ramp between -1 and +1.
                    sample = (short) ((2 * Math.abs(2 * ((t * freq) - Math.floor((t * freq) + 0.5))) - 1) * volume * Short.MAX_VALUE);
                    break;
                case "Sawtooth":
                    // Sawtooth wave formula: raises linearly and drops instantaniously
                    sample = (short) ((2 * ((t * freq) - Math.floor(t * freq)) - 1) * volume * Short.MAX_VALUE);
                    break;
                default:
                    break;
            }
            buffer[2 * i] = (byte) (sample & 0xFF); // Lower byte
            buffer[2 * i + 1] = (byte) ((sample >> 8) & 0xFF); // Upper byte
        }
    }

    /**
     * Setter for frequency
     *
     * @param frequency what to set frequency to
     */
    public void setFrequency(float frequency) {
        this.frequency = frequency;
    }

    /**
     * Setter for waveType
     *
     * @param waveType what to set waveType to.
     */
    public void setWaveType(String waveType) {
        this.waveType = waveType;

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
     * Setter for volume
     *
     * @param volume the volume(between 0.0 and 1.0, 1.0 being 100% and 0 being
     * 0%)
     */
    public void changeVolume(float volume) {
        this.volume = volume;
    }

    /**
     * Getter for volume
     *
     * @return the volume
     */
    public float getVolume() {
        return volume;
    }

    /**
     * Setter for running
     *
     * @param running what to set this.running to
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * Getter running
     *
     * @return - the boolean value of running attribute
     */
    public boolean getRunning() {
        return running;
    }

}
