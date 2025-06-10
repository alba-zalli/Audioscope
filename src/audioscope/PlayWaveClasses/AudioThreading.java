/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package audioscope.PlayWaveClasses;

import javax.sound.sampled.*;

/**
 * Threads audio to accompany sound waves
 *
 * @author alba z and cunpl
 */
public class AudioThreading extends Thread {

    private volatile float frequency = 0f; // Can be changed safely from outside
    private volatile float volume = 0.1f;

    public static volatile String waveType;

    private final int SAMPLE_RATE = 44100;
    private final int BUFFER_DURATION_MS = 1000; // Shorter buffer = faster response
    private final int numSamples = (int) ((BUFFER_DURATION_MS / 1000.0) * SAMPLE_RATE);
    private final byte[] buffer = new byte[2 * numSamples];

    private boolean running = true;

    @Override
    public void run() {
        AudioFormat format = new AudioFormat(SAMPLE_RATE, 16, 1, true, false);

        try (SourceDataLine line = AudioSystem.getSourceDataLine(format)) {
            line.open(format);
            line.start();

            while (running) {
                if ("Sine".equals(waveType)) {
                    generateSineWaveBuffer(frequency);
                } else if ("Square".equals(waveType)) {
                    generateSquareWaveBuffer(frequency);
                } else {
                    generateSineWaveBuffer(frequency); // default fallback
                }
                line.write(buffer, 0, buffer.length);
            }

            line.drain();
            line.stop();

        } catch (LineUnavailableException e) {
            System.out.println("Audio error: " + e);
        }
    }

    private void generateSineWaveBuffer(float freq) {
        double twoPiF = 2.0 * Math.PI * freq;
        for (int i = 0; i < numSamples; i++) {
            double t = i / (double) SAMPLE_RATE;
            short sample = (short) (Math.sin(twoPiF * t) * volume * Short.MAX_VALUE);
            buffer[2 * i] = (byte) (sample & 0xFF);
            buffer[2 * i + 1] = (byte) ((sample >> 8) & 0xFF);
        }
    }

    private void generateSquareWaveBuffer(float freq) {
        double twoPiF = 2.0 * Math.PI * freq;
        for (int i = 0; i < numSamples; i++) {
            double t = i / (double) SAMPLE_RATE;
            short sample = (short) ((Math.sin(twoPiF * t) >= 0 ? 1 : -1) * volume * Short.MAX_VALUE);
            buffer[2 * i] = (byte) (sample & 0xFF);
            buffer[2 * i + 1] = (byte) ((sample >> 8) & 0xFF);
        }
    }

    public static void setWaveType(String waveType) {
        AudioThreading.waveType = waveType;
    }

    public void setFrequency(float newFreq) {
        this.frequency = newFreq;
    }

    public void stopAudio() {
        this.running = false;
    }
}
