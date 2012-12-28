package de.philipphock.android.androidBasics;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.MapFragment;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;

import android.util.Log;

import android.widget.Toast;

public class Location extends Activity implements LocationListener{
	
	private LocationManager locationManager; 
	private android.location.Location location;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
	}

	

	

	@Override
	protected void onResume() {
		if (ConnectionResult.SUCCESS != GooglePlayServicesUtil.isGooglePlayServicesAvailable(this)){
			exitBecause("Google Play Services not installed.. oh dear!");
		}
		if (!isOnline()){
			Toast.makeText(this,"enable internet!",Toast.LENGTH_LONG).show();
		}
		getLocation();
		
		super.onResume();
	}
	
	
	
	private void exitBecause(String text){
		
		Toast.makeText(this,text,Toast.LENGTH_LONG).show();
		System.exit(0);
			
		
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		if(locationManager != null){
            locationManager.removeUpdates(this);
        }
		 
	}

	@Override
	public void onLocationChanged(android.location.Location arg0) {
		location = arg0;
		Log.d("location",arg0+"");
		
		setLocation();
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		getLocation();
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void getLocation() {
		
        
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
 
            // getting network status
        if (!locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
        	Toast.makeText(this,"enable network location service!",Toast.LENGTH_LONG).show();
        }else{
        	Log.d("location", "network location");
        	locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,10000,10, this);        	
        }
 
        
            
        if (locationManager != null) {
        	location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
     // getting GPS status
        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
        	Toast.makeText(this,"enable gps!",Toast.LENGTH_LONG).show();
        }else{
        	locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,10000, 10, this);
            Log.d("location", "GPS Enabled");        	
        }
                     
        
        if (locationManager != null) {
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        if (location!=null)
        onLocationChanged(location);
        
    }
	
	private void setLocation(){
		FragmentManager fragmentManager = getFragmentManager();  
	     MapFragment mapFragment = (MapFragment)fragmentManager.findFragmentById(R.id.map);  
	     GoogleMap googleMap = mapFragment.getMap();  
	     LatLng sfLatLng = new LatLng(location.getLatitude(),location.getLongitude());
	     
	     googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);  
	     googleMap.addMarker(new MarkerOptions().position(sfLatLng).title("you")
	            .icon(BitmapDescriptorFactory.defaultMarker(
	     BitmapDescriptorFactory.HUE_AZURE)));
	     
	     googleMap.getUiSettings().setCompassEnabled(true);  
	     googleMap.getUiSettings().setZoomControlsEnabled(true);  
	     googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sfLatLng, 10));
	     
	   
	}
	
	public boolean isOnline() {
	    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    return (netInfo != null && netInfo.isConnectedOrConnecting());
	}
}
