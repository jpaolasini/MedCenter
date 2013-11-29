package com.example.medcenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RegisterActivity extends Activity{
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		Intent intent = getIntent();
	}
	
	/*
	 * Verify users specified inputs and register them to the system.
	 */
	public void verifyDone(View view)
	{
		Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
		intent.putExtra(EXTRA_MESSAGE, "Registration Successful.");
		startActivity(intent);
	}
}
