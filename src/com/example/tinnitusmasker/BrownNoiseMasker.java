package com.example.tinnitusmasker;

import java.util.Random;

public class BrownNoiseMasker extends Masker {

	protected Random rand = new Random();

	private double currentAmplitude = 0.; // begin at zero to prevent a click
	private double maxStep = 0.1; // a tenth of our range

	/**
	 * This method implements the Drunkard's Walk algorithm. Starting at the
	 * initial value of zero, we take a randomly-sized step forward or backward
	 * a number of units <= maxStep.
	 * 
	 * In this way, the higher frequencies are prevented from happening, but a
	 * random signal is still produced.
	 */
	@Override
	protected void generateSample() {

		for (int i = 0; i < numSamples; i++) {

			double r = rand.nextDouble(); // in range [0,1)
			r = r * 2 - 1; // [-1, 1)
			r *= maxStep; // [-maxStep, maxStep)

			// Prevent overflow so we don't accidentally introduce unwanted
			// frequencies to our signal.
			if (currentAmplitude >= 1.0 - r)
				currentAmplitude = 1.0;
			else if (currentAmplitude <= -1.0 - r)
				currentAmplitude = -1.0;
			else
				currentAmplitude += r;

			sample[i] = currentAmplitude;
		}
	}

}
