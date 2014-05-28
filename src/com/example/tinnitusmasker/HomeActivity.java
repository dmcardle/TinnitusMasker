package com.example.tinnitusmasker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.tinnitusmasker.R;

/**
 * This Activity lets the user select a Masker (e.g. White Noise) and play or
 * pause the selection.
 * 
 * @see SystemUiHider
 */
public class HomeActivity extends Activity {

	private Masker masker = new WhiteNoiseMasker();
	private boolean playing = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_home);		
		
		/* Set up the play/pause button */
		final View playButton = findViewById(R.id.play_button);
		playButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Button butt = (Button) playButton;
				
				if (playing) {
					playing = false;
					masker.pause();
					
					butt.setText(getString(R.string.play_button));
					
					//Toast toast = Toast.makeText(getApplicationContext(), "Pausing!", Toast.LENGTH_SHORT);
					//toast.show();
				} else {
					playing = true;
					masker.play();
					
					butt.setText(getString(R.string.pause_button));
					
					//Toast toast = Toast.makeText(getApplicationContext(), "Playing!", Toast.LENGTH_SHORT);
					//toast.show();
				}
			}
		});
	}
	
	/**
	 * Handles the selection of a Masker.
	 * 
	 * @param view This is radio button we are checking.
	 */
	public void onRadioButtonClicked(View view) {
	    // Is the button now checked?
	    boolean checked = ((RadioButton) view).isChecked();
	    if (!checked)
	    	return;
	    

		Toast toast = Toast.makeText(getApplicationContext(), "Selecting " + view.getId() + "!", Toast.LENGTH_SHORT);
		toast.show();
	    
	    // Check which radio button was clicked
	    switch(view.getId()) {
	        case R.id.whiteRadioButton:
	            // Use the White Noise Masker
	        	masker = new WhiteNoiseMasker();
	            break;
	        case R.id.brownRadioButton:
	        	masker = new BrownNoiseMasker();
	        	break;
	        case R.id.rainRadioButton:
	            // Use the Rain Masker
	        	masker = new RainMasker();
	            break;
	        case R.id.sineRadioButton:
	        	masker = new SineMasker();
	        	break;

	    }
	}
}
