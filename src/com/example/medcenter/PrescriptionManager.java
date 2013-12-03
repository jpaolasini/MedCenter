package com.example.medcenter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class PrescriptionManager extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prescription_manager);
		Intent intent = getIntent();
		
		/*
		ArrayList<String> spinnerArray = new ArrayList<String>();
	    spinnerArray.add("one");
	    spinnerArray.add("two");
	    spinnerArray.add("three");
	    spinnerArray.add("four");
	    spinnerArray.add("five");

	    Spinner spinner = (Spinner) findViewById(R.id.PresSpinner);
	    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
	    spinner.setAdapter(spinnerArrayAdapter);*/
	}

}
