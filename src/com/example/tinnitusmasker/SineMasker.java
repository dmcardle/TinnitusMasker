package com.example.tinnitusmasker;

public class SineMasker extends Masker {
    protected final double freqOfTone = 220; // in Hz
    
	@Override    
	protected void generateSample() {
        for (int i = 0; i < numSamples; i++) {
            sample[i] = Math.sin(2 * Math.PI * i / (sampleRate/freqOfTone));
        }
	}

}
