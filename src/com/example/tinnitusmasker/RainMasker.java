package com.example.tinnitusmasker;

public class RainMasker extends Masker {
	private final double baseFreq = 220; // in Hz
	private final int numOctaves = 10;
	
	private final int fluctuationFreq = 1;
	private final double fluctuationAmt = 20.0;
	private int time = 0;
	
	protected void generateSample() {
		
        for (int i = 0; i < numSamples; i++) {
        	
        	time++;
        	
        	double freq = baseFreq + fluctuationAmt * Math.sin(2*Math.PI * fluctuationFreq * time);
        	
        	// add each overtone to this amplitude
    		double amp = 0;
    		for (int oct=1; oct<=numOctaves; oct++) {
    			double weight = 1.0 / numOctaves;
    			amp += weight * Math.sin(2 * Math.PI * i / (sampleRate/(oct*freq)));
    		}
        	
            sample[i] = amp;
        }
	}
}
