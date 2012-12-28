package de.philipphock.android.androidBasics;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class BrightnessFragment extends Fragment implements SensorEventListener{
	private SensorManager mSensorManager;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.brightnessfragmentlayout, null);	
	}

	@Override
	public void onResume() {
	  super.onResume();
	  if(!mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
			  SensorManager.SENSOR_DELAY_NORMAL)){
		  	  TextView v = (TextView) getActivity().findViewById(R.id.bright_val);
			  v.setText("no brightness sensor available");
		
	  }else{
		  TextView v = (TextView) getActivity().findViewById(R.id.bright_val);
		  v.setText("");
	  }
	}
	
	@Override
	public void onPause() {
	  super.onPause();
	  mSensorManager.unregisterListener(this);
	}

	

	@Override
	public void onSensorChanged(SensorEvent event) {
		float[] values = event.values;
		
		float a = values[0];
	
		TextView v_a = (TextView) getActivity().findViewById(R.id.bright_val);
		
		
		if ( v_a == null ){
			return;
		}
		
		v_a.setText(""+(int)a);
				
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
		
		
	}
}
