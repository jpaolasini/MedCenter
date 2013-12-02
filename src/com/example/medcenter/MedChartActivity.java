package com.example.medcenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MedChartActivity extends Activity {

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_medchart);
    Intent intent = getIntent();
    String patientUserName = intent.getStringExtra(DocNurseHomeActivity.EXTRA_MESSAGE);
    if(UserInformation.userType.equals("Doctor") || UserInformation.userType.equals("Nurse"))
    {
      	//Button editInfo = (Button) findViewById(R.id.editInfoButton);      		
      	//editInfo.setVisibility(View.VISIBLE);
    }
    
    else if(UserInformation.userType.equals("patient"))
    {
        //Turn off the edit info button if a patient is viewing this page.	
      	//Button editInfo = (Button) findViewById(R.id.editInfoButton);      		
      	//editInfo.setVisibility(View.GONE);
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
		age.setText("Age: " + patientInfo[6]);	
		
		TextView gender = new TextView(this);
		age = (TextView) findViewById(R.id.textView1);
		age.setText("Gender: " + patientInfo[4]);	
		
		TextView weight = new TextView(this);
		weight = (TextView) findViewById(R.id.textView3);
		weight.setText("Weight: " + patientInfo[7]);	
		
		TextView height = new TextView(this);
		height = (TextView) findViewById(R.id.textView4);
		height.setText("Height: " + patientInfo[8]);	
		
		TextView bloodType = new TextView(this);
		bloodType = (TextView) findViewById(R.id.textView5);
		bloodType.setText("BloodType: " + patientInfo[9]);			
    } catch (Exception e) {  		
	}    
    
  }
}
