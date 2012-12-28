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


public class GyroscopeFragment extends Fragment implements SensorEventListener{
	
	private SensorManager mSensorManager;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.gyroscopefragmentlayout, null);	
	}

	@Override
	public void onResume() {
	  super.onResume();
	  if(!mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
			  SensorManager.SENSOR_DELAY_NORMAL)){
		  	  TextView v = (TextView) getActivity().findViewById(R.id.gyro_status);
			  v.setText("no gyroscope available");
		
	  }else{
		  TextView v = (TextView) getActivity().findViewById(R.id.gyro_status);
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
		float b = values[1];
		float c = values[2];
		float d = values[3];
	
		TextView v_a = (TextView) getActivity().findViewById(R.id.gyro_val_a);
		TextView v_b = (TextView) getActivity().findViewById(R.id.gyro_val_b);
		TextView v_c = (TextView) getActivity().findViewById(R.id.gyro_val_c);
		TextView v_d = (TextView) getActivity().findViewById(R.id.gyro_val_d);
		
		
		if (( v_a == null ) ||
			( v_b == null ) ||
			( v_c == null ) ||
			( v_d == null ) ){
			return;
		}
		
		v_a.setText(""+(int)a);
		v_b.setText(""+(int)b);
		v_c.setText(""+(int)c);
		v_d.setText(""+(int)d);
				
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
		
		
	}

}
