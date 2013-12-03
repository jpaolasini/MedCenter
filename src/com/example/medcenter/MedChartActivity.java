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
		
    TextView lastTetnus = new TextView(this);
    lastTetnus = (TextView) findViewById(R.id.lastTetnus);
    lastTetnus.setText("Last Tetnus: "+patientInfo[10]);
    
    TextView lastFlu = new TextView(this);
    lastFlu = (TextView) findViewById(R.id.lastFlu);
    lastFlu.setText("Last Flu Shot: "+patientInfo[11]);
    
    TextView hasHernia = new TextView(this);
    hasHernia = (TextView) findViewById(R.id.hasHernia);
    hasHernia.setText("Hernia: "+patientInfo[12]);
    
    TextView hasBloodInUrine = new TextView(this);
    hasBloodInUrine = (TextView) findViewById(R.id.hasBloodInUrine);
    hasBloodInUrine.setText("Blood in Urine: "+patientInfo[13]);
    
    TextView hasDiabetes = new TextView(this);
    hasDiabetes = (TextView) findViewById(R.id.hasDiabetes);
    hasDiabetes.setText("Diabetes: "+patientInfo[14]);   

		
    } catch (Exception e) {  		
	}    
    
  }
}
