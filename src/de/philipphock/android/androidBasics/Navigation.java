package de.philipphock.android.androidBasics;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class Navigation extends Activity{

	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_navigation);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_navigation, menu);
		return true;
	}
	
	
	
	public void onClick(View v){
		switch (v.getId()){
			case R.id.nav_sensors:
				startActivity(new Intent(this,Sensors.class));		
			break;
			
			case R.id.touch:
				startActivity(new Intent(this,TouchBlob.class));		
			break;
			
			case R.id.location:
				startActivity(new Intent(this,Location.class));		
			break;

		}
		
		
	}


  
}
