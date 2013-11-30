package com.example.medcenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserInfoActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_userinfo);
    Intent intent = getIntent();
    String patientUserName = "";
    if(UserInformation.userType.equals("Doctor") || UserInformation.userType.equals("Nurse"))
    {
    	patientUserName = intent.getStringExtra(DocNurseHomeActivity.EXTRA_MESSAGE);
    	//Turn on the edit info button if a doctor/nurse is viewing this page.	
      	Button editInfo = (Button) findViewById(R.id.editInfoButton);      		
      	editInfo.setVisibility(View.VISIBLE);
    }
    else
    {
        patientUserName = intent.getStringExtra(HomeActivity.EXTRA_MESSAGE);
        
        //Turn off the edit info button if a patient is viewing this page.	
      	Button editInfo = (Button) findViewById(R.id.editInfoButton);      		
      	editInfo.setVisibility(View.GONE);
    }
    
    UserInformation.userName = patientUserName;
    // Get the directory path to the download folder and create an app
    // folder there.
    String directoryPath = Environment.getExternalStoragePublicDirectory(
        Environment.DIRECTORY_DOWNLOADS).toString()
        + "/";
    String fileName = patientUserName + ".txt";
    try {
    	//Get the patients information.
		String data = FileHandler.ReadFile(directoryPath + "medCenter/", fileName);
		String[] patientInfo = UserInformation.parseInfo(data);
		UserInformation.userPassword = patientInfo[1];
		//Display the patients information
	    TextView patientName = new TextView(this);
		patientName = (TextView) findViewById(R.id.errorMessage);
		patientName.setText(patientInfo[2] + " " + patientInfo[3]);	
		
		TextView age = new TextView(this);
		age = (TextView) findViewById(R.id.textView2);
		age.setText("Age: " + patientInfo[5]);	
		
		TextView weight = new TextView(this);
		weight = (TextView) findViewById(R.id.textView3);
		weight.setText("Weight: " + patientInfo[6]);	
		
		TextView height = new TextView(this);
		height = (TextView) findViewById(R.id.textView4);
		height.setText("Height: " + patientInfo[7]);	
		
		TextView bloodType = new TextView(this);
		bloodType = (TextView) findViewById(R.id.textView5);
		bloodType.setText("BloodType: " + patientInfo[8]);			
    } catch (Exception e) {  		
	}    
    
  }

  // Go to the pictures activity
  public void goToPictures(View view) {
    Intent intent = new Intent(getApplicationContext(),
        PhotoIntentActivity.class);
    startActivity(intent);
  }

  // Go to the medical chart activity
  public void goToMedChart(View view) {
    Intent intent = new Intent(getApplicationContext(), MedChartActivity.class);
    startActivity(intent);
  }

  public void goToGraphing(View view) {
    Intent intent = new Intent(getApplicationContext(), GraphingActivity.class);
    startActivity(intent);
  }

  // Go to the schedule activity
  public void goToSchedule(View view) {
    Intent intent = new Intent(getApplicationContext(), ScheduleActivity.class);
    startActivity(intent);
  }
  
  public void goToEditPatientInfo (View view)
  {
	  Intent intent = new Intent(getApplicationContext(), EditPatientInformationActivity.class);
	  intent.putExtra(EXTRA_MESSAGE,UserInformation.userName);
	  startActivity(intent);
  }

}
