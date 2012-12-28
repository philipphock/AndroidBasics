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


public class MagnetometerFragment extends Fragment implements SensorEventListener{
	
	private SensorManager mSensorManager;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.magnetometerfragmentlayout, null);	
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float[] values = event.values;
		
		float x = values[0];
		float y = values[1];
		float z = values[2];
		
		TextView v_x = (TextView) getActivity().findViewById(R.id.mag_a);
		TextView v_y = (TextView) getActivity().findViewById(R.id.mag_b);
		TextView v_z = (TextView) getActivity().findViewById(R.id.mag_c);
		
		if (	(v_x == null) ||
				(v_y == null) ||
				(v_z == null) ){
			return;
		}
		
		v_x.setText(""+(int)x);
		v_y.setText(""+(int)y);
		v_z.setText(""+(int)z);
		
	}

	@Override
	public void onResume() {
	  super.onResume();
	  
	
	  if(!mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
			  SensorManager.SENSOR_DELAY_NORMAL)){
	   		  TextView v = (TextView) getActivity().findViewById(R.id.mag_status);
			  v.setText("no magnetometer available");
		
	  }else{
		  TextView v = (TextView) getActivity().findViewById(R.id.mag_status);
		  v.setText("");
	  }
	  
	  
	}
	
	@Override
	public void onPause() {
	  super.onPause();
	  mSensorManager.unregisterListener(this);
	}
}
