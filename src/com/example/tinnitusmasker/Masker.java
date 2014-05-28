package com.example.tinnitusmasker;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.util.Log;

public abstract class Masker {
	protected final int duration = 1; // seconds
    protected final int sampleRate = 44100;
    
    protected final int numSamples = duration * sampleRate;
    protected final double sample[] = new double[numSamples];
    private final byte generatedSnd[] = new byte[2 * numSamples];
    
    private Thread thread = null;
    private volatile boolean playing = false;
    
	public void play() {
		if (thread != null) return;
		playing = true;
		
		final AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                sampleRate,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                generatedSnd.length*2, // two chunks to allow double buffering
                AudioTrack.MODE_STREAM);
		
		// We're streaming, so just begin playing now.
		audioTrack.play();
		
		
        thread = new Thread(new Runnable() {
            public void run() {
            	
            	while (playing) {
            		//Log.w("Masker.java", "generating second #" + ((double)offsetInBytes/sampleRate));
            		generate();
                    audioTrack.write(generatedSnd, 0, generatedSnd.length);
            	}
            	
            	// Clean up
            	audioTrack.stop();
            	audioTrack.release();
            }
        });
        thread.start();
	}
	
	public void pause() { 
		try {
			playing = false;
			thread.join();
			thread = null;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Fill out the `sample` array with doubles.
	 */
	protected abstract void generateSample();
	
	private void convert() {
        // convert to 16 bit pcm sound array
        // assumes the sample buffer is normalised.
        int idx = 0;
        for (final double dVal : sample) {
            // scale to maximum amplitude
            final short val = (short) ((dVal * Short.MAX_VALUE));
            // in 16 bit wav PCM, first byte is the low order byte
            generatedSnd[idx++] = (byte) (val & 0x00ff);
            generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);

        }
	}
	
	public void generate() {
		generateSample();
        convert();
	}
	
}
