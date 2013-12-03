package com.example.medcenter;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class ScheduleActivity extends Activity {

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_schedule);
    
    // String message = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);
    // EditText editText = (EditText) findViewById(R.id.textView1);
  
  }
  public void calendarEvent(Calendar begintime, Calendar endtime){

	    Intent intent = new Intent(Intent.ACTION_EDIT);
	    intent.setType("vnd.android.cursor.item/event");
	    intent.putExtra("beginTime", begintime.getTimeInMillis());
	    intent.putExtra("allDay", true);
	    intent.putExtra("rrule", "FREQ=YEARLY");
	    intent.putExtra("endTime", endtime.getTimeInMillis()+60*60*1000);
	    intent.putExtra("title", "A Test Event from android app");
	    startActivity(intent);
	}
}
