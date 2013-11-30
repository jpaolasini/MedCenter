package com.example.medcenter;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;

public class EditPatientInformationActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_editpatientinfo);
	    Intent intent = getIntent();
	}
	
	public void confirmChange(View view)
	{
		// Retrieve the user input from the text fields.
	    String firstName = ((EditText) findViewById(R.id.firstName)).getText().toString();
	    String lastName = ((EditText) findViewById(R.id.lastName)).getText().toString();
	    String height = ((EditText) findViewById(R.id.height)).getText().toString();
	    String weight = ((EditText) findViewById(R.id.weight)).getText().toString();
	    String age = ((EditText) findViewById(R.id.age)).getText().toString();
	    String bloodType = ((EditText) findViewById(R.id.bloodType)).getText().toString();
	    
	    if (firstName.matches(".*\\d.*")
	            || lastName.matches(".*\\d.*")
	            || bloodType.matches(".*\\d.*")) {
	        } else {

	          try {

	            double heightDouble = Double.parseDouble(height);
	            double weightDouble = Double.parseDouble(weight);
	            int ageInteger = Integer.parseInt(age); 
	            
	            // Get the directory path to the download folder and create an app
	            // folder there.
	            String directoryPath = Environment.getExternalStoragePublicDirectory(
	                Environment.DIRECTORY_DOWNLOADS).toString()
	                + "/";

	            //delete data to be changed
	            //File file = new File(directoryPath + "medCenter/" + UserInformation.userName + ".txt");
	            //file.delete();
	            
	              // merges user information into a string to be stored.
	              String mergedData = UserInformation.mergePatientInfo(UserInformation.userName, 
	            		  UserInformation.userPassword, firstName, lastName,
	            		  "patient", height, weight,
	            		  age, bloodType);

	              // Encrypt data and write it to text file in the app directory.
	              FileHandler.WriteFile(directoryPath + "medCenter/", UserInformation.userName +".txt",
	                  mergedData);
	              
		            Intent intent = new Intent(getApplicationContext(), UserInfoActivity.class);
		            intent.putExtra(EXTRA_MESSAGE,UserInformation.userName);
			        startActivity(intent);
			    	             

	          } catch (Exception e) {
	        	  e.printStackTrace();
	          }
	        }
	}
	}
