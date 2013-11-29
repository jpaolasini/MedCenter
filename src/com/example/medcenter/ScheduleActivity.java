package com.example.medcenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ScheduleActivity extends Activity{

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule);
		Intent intent = getIntent();
		//String message = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);
		//EditText editText = (EditText) findViewById(R.id.textView1);
	}
}
