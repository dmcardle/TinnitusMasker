package com.example.tinnitusmasker;

import java.util.Random;

public class WhiteNoiseMasker extends Masker {
	
	protected Random rand = new Random();
    
	protected void generateSample() {
        for (int i = 0; i < numSamples; i++) {
            sample[i] = rand.nextDouble();
        }
	}
}
