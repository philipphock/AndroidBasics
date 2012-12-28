package de.philipphock.android.androidBasics;

import de.philipphock.android.androidBasics.R;
import de.philipphock.android.androidBasics.R.layout;
import de.philipphock.android.androidBasics.R.menu;
import de.philipphock.android.androidBasics.ext.BlobView;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class TouchBlob extends Activity {

	private BlobView blobView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		blobView = new BlobView(this, null);
		
		
		setContentView(blobView);
		//setContentView(R.layout.activity_touch_blob);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.activity_touch_blob, menu);
		return true;
	}

}
