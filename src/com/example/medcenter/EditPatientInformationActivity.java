package com.example.medcenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

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
	    String gender = ((Spinner) findViewById(R.id.spinner2)).getSelectedItem().toString();
	    String height = ((EditText) findViewById(R.id.height)).getText().toString();
	    String weight = ((EditText) findViewById(R.id.weight)).getText().toString();
	    String age = ((EditText) findViewById(R.id.age)).getText().toString();
	    String bloodType = ((Spinner) findViewById(R.id.spinner1)).getSelectedItem().toString();
	    
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
	            
	            String fileName = UserInformation.userName + ".txt";
	            
	            //Read existing user data from file.
	            String data = FileHandler.ReadFile(directoryPath + "medCenter/", fileName);
	    		String[] patientInfo = UserInformation.parseInfo(data);
	    		
	    		//Get specific patient data.
	    		String lastTetnus = patientInfo[10] ;
	 		    String lastFlu =  patientInfo[11] ;
	 		    String hasHernia =  patientInfo[12] ;
	 		    String hasBloodInUrine = patientInfo[13] ;
	 		    String hasDiabetes =  patientInfo[14] ;
	 		    
	 		    //Retrieve individual prescriptions.
	 		    String prescriptionString =  patientInfo[15];	 		    
	 		    String[] prescriptions;
	 		    prescriptionString = prescriptionString.replaceAll("[", "");
	 		    prescriptions = prescriptionString.split(":");
	    		
	              // merges user information into a string to be stored.
	              String mergedData = UserInformation.mergePatientInfo(UserInformation.userName, 
	            		  UserInformation.userPassword, firstName, lastName, gender,
	            		  "patient", height, weight,
	            		  age, bloodType, lastTetnus, lastFlu, hasHernia, hasBloodInUrine, hasDiabetes, prescriptions);

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
