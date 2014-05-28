package com.example.tinnitusmasker;

import java.util.Random;

import android.util.Log;

public class RainMasker extends Masker {
	private final int MAX_PLINK_LENGTH = (int) (0.2 * sampleRate);
	private Random rand = new Random();
	private int plinksPerSec = 1;
	private int minFreq = 500;
	private int maxFreq = 1000;
	
	public RainMasker() {
		super();
	}
	
	protected void generateSample() {
		int numPlinks = plinksPerSec * duration;
		for (int i = 0; i < numPlinks; i++) {
			int plinkTime = rand.nextInt(numSamples);
			int plinkLen = (int)(rand.nextDouble() * MAX_PLINK_LENGTH);
			double plinkFreq = rand.nextDouble() * maxFreq;
			addPlink(plinkTime, plinkLen, plinkFreq);
		}
	}

	/**
	 * Adds a plink sound to `sample` at the specified frequency
	 * 
	 * @param i
	 * @param freq
	 */
	private void addPlink(int pos, int len, double freq) {
		if (pos + len >= numSamples) return;
		
		Log.d("RainMasker.addPlink", "adding a plink");

		double mu = freq;
		double sigma = 100;
		
		for (int i = pos; i < pos + len; i++) {

			/*
			 * Calculate the frequency intensity using the normal distribution
			 * centered at freq as our probability density function.
			 * 
			 * We are choosing mu (the mean) to be freq. We are choosing sigma
			 * (the variance) arbitrarily.
			 */
			
			for (int f = minFreq; f < maxFreq; f++) {
				double freqIntensity = probabilityDensityFunction(mu, sigma, f);		
				// Now that we have the frequency intensity, add this frequency to the sample.
				sample[i] += freqIntensity * Math.sin(2*Math.PI*freq*i);
			}
		}
	}

	/**
	 * Calculates the normal curve
	 * @param mu mean
	 * @param sigma variance
	 * @param x the x value
	 * @return the pdf at x
	 */
	private double probabilityDensityFunction(double mu, double sigma, double x) {
		double exponent = -Math.pow(x - mu, 2) / (2 * sigma * sigma);
		return (1.0 / (sigma * Math.sqrt(2 * Math.PI)))
				* Math.pow(Math.E, exponent);
	}
}
