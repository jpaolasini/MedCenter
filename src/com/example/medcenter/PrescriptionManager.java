package com.example.medcenter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class PrescriptionManager extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prescription_manager);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.prescription_manager, menu);
		return true;
	}

}
