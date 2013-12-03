package com.example.medcenter;

import java.util.ArrayList;
import java.util.List;

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
	    String newWeight = ((EditText) findViewById(R.id.weight)).getText().toString();
	    String newAge = ((EditText) findViewById(R.id.age)).getText().toString();
	    String height = ((EditText) findViewById(R.id.height)).getText().toString();
	    String gender = ((Spinner) findViewById(R.id.spinner2)).getSelectedItem().toString();
	    String bloodType = ((Spinner) findViewById(R.id.spinner1)).getSelectedItem().toString();
	    
	    if (firstName.matches(".*\\d.*")
	            || lastName.matches(".*\\d.*")
	            || bloodType.matches(".*\\d.*")) {
	        } else {

	          try {	            
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
	    		String lastTetnus = patientInfo[10];
	 		    String lastFlu =  patientInfo[11];
	 		    String hasHernia =  patientInfo[12];
	 		    String hasBloodInUrine = patientInfo[13];
	 		    String hasDiabetes =  patientInfo[14];
	 		    
	 		    //Retrieve individual prescriptions.
	 		    String prescriptionString =  patientInfo[15];	 		    
	 		    String[] prescriptions = new String[3];
	 		    prescriptionString = prescriptionString.replace("[", "");
	 		    prescriptionString = prescriptionString.replace("]", "");
	 		    prescriptions = prescriptionString.split(":");
	 		    
	 		    String age = patientInfo[6];
	 		    String weight = patientInfo[7];
	 		    
	 		    //Get array of weight
	 		    List<Integer> weightArray = new ArrayList<Integer>();
	 		    weight = weight.replace("[", "");
	 		    weight = weight.replace("]", "");
	 		    String[] weightStringArray = weight.split(":");
	 		    for(int i = 0; i < weightStringArray.length; i++)
	 		    {
	 		    	weightArray.add(Integer.parseInt(weightStringArray[i]));
	 		    }
	 		    weightArray.add(Integer.parseInt(newWeight));
	 		    
	 		    //get array of age.
	 		    List<Integer> ageArray = new ArrayList<Integer>();
	 		    age = age.replace("[", "");
	 		    age = age.replace("]", "");
	 		    String[] ageStringArray = age.split(":");
	 		    for(int i = 0; i < ageStringArray.length; i++)
	 		    {
	 		    	ageArray.add(Integer.parseInt(ageStringArray[i]));
	 		    }	    
	 		    ageArray.add(Integer.parseInt(newAge));
	    		
	              // merges user information into a string to be stored.
	              String mergedData = UserInformation.mergePatientInfo(UserInformation.userName, 
	            		  UserInformation.userPassword, firstName, lastName, gender,
	            		  "patient", height, weightArray,ageArray, bloodType, 
	            		  lastTetnus, lastFlu, hasHernia, hasBloodInUrine, hasDiabetes, prescriptions);

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
