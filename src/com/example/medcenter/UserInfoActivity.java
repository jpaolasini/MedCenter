package com.example.medcenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserInfoActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userinfo);
		Intent intent = getIntent();
		//String message = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);
		//EditText editText = (EditText) findViewById(R.id.textView1);
	}
	
	//Go to the pictures activity
	public void goToPictures(View view)
	{
		Intent intent = new Intent(getApplicationContext(), PhotoIntentActivity.class);
		startActivity(intent);
	}
	
	//Go to the medical chart activity
	public void goToMedChart(View view)
	{
		Intent intent = new Intent(getApplicationContext(), MedChartActivity.class);
		startActivity(intent);
	}
	
	public void goToGraphing(View view)
	{
		Intent intent = new Intent(getApplicationContext(), GraphingActivity.class);
		startActivity(intent);
	}
	
	//Go to the schedule activity
	public void goToSchedule(View view)
	{
		Intent intent = new Intent(getApplicationContext(), ScheduleActivity.class);
		startActivity(intent);
	}

}
