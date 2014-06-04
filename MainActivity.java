package com.example.proyecto_examen_mediaplayer;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.R.anim;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	private MediaPlayer mediaPlayer;
	private int length = 0;
	private int[] songs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		
		songs = new int[] {
				R.raw.song1, R.raw.song2, R.raw.song3
		};
		
		mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.song1);
		
		Spinner spinner = (Spinner) findViewById(R.id.spinner);
		
		ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(
				getApplicationContext(), R.array.canciones, android.R.layout.simple_spinner_item);
		
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adaptador);
		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int posicion, long arg3) {
					mediaPlayer = MediaPlayer.create(getApplicationContext(), songs[posicion]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
		
		
		(findViewById(R.id.btnPlay)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				
				if(mediaPlayer.isPlaying()) {
					
					mediaPlayer.pause();
					length = mediaPlayer.getCurrentPosition();
				
				} 								
				else {
					mediaPlayer.seekTo(length);
					mediaPlayer.start();
				}
				
			}
		});
		
		(findViewById(R.id.btnStop)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				mediaPlayer.pause();
				length = 0;
			}
		});
		
	}
	
}//Fin class MainActivity