package de.philipphock.android.androidBasics;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MagnetometerFragment extends Fragment{
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.magnetometerfragmentlayout, null);	
	}

}
