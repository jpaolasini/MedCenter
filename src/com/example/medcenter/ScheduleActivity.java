package com.example.medcenter;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;


public class ScheduleActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_schedule);
    setTitle("Schedule");
    
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
  
  public void goToMedicalInformation(View view) {
	    Intent intent = new Intent(getApplicationContext(), MedChartActivity.class);
	    intent.putExtra(EXTRA_MESSAGE, UserInformation.userName);
	    startActivity(intent);
	    
	    TimePicker getTimepickerTime = (TimePicker) findViewById(R.id.timePicker1);
	    int Hour= getTimepickerTime.getCurrentHour();
	    int Minute=getTimepickerTime.getCurrentMinute();
	   
	    String lastTetnusDay = String.valueOf(((DatePicker) findViewById(R.id.datePicker3)).getDayOfMonth());
	    String lastTetnusMonth = String.valueOf(((DatePicker) findViewById(R.id.datePicker3)).getMonth());
	    String lastTetnusYear = String.valueOf(((DatePicker) findViewById(R.id.datePicker3)).getYear());
	    String lastTetnus = lastTetnusDay + "/" + lastTetnusMonth + "/" + lastTetnusYear;
	    UserInformation.appointmentDate = lastTetnus +" at "+ Hour+":"+Minute;
	    
	    Toast.makeText(getApplicationContext(), "Your Appointment is on "+ UserInformation.appointmentDate,
	            Toast.LENGTH_LONG).show();
	  }
  public void onBackPressed(){
	  
		  Intent intent = new Intent(getApplicationContext(), UserInfoActivity.class);
		  intent.putExtra(EXTRA_MESSAGE, UserInformation.userName);
		    startActivity(intent);
		  startActivity(intent);
	
  }

  

  
}
