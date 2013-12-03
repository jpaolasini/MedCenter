package com.example.medcenter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

public class EditPatientMedicalHistory extends Activity{
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";


	 protected void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.activity_editpatientmed);
		    Intent intent = getIntent();
		    String patientUserName = intent.getStringExtra(DocNurseHomeActivity.EXTRA_MESSAGE);
		    UserInformation.userName = patientUserName;
	 }
	 
	 public void confirmChange(View view)
		{
			// Retrieve the user input from the text fields.
		    String lastTetnusDay = String.valueOf(((DatePicker) findViewById(R.id.datePicker1)).getDayOfMonth());
		    String lastTetnusMonth = String.valueOf(((DatePicker) findViewById(R.id.datePicker1)).getMonth());
		    String lastTetnusYear = String.valueOf(((DatePicker) findViewById(R.id.datePicker1)).getYear());
		    String lastTetnus = lastTetnusDay + "/" + lastTetnusMonth + "/" + lastTetnusYear;
		    
		    String lastFluDay = String.valueOf(((DatePicker) findViewById(R.id.datePicker2)).getDayOfMonth());
		    String lastFluMonth = String.valueOf(((DatePicker) findViewById(R.id.datePicker2)).getMonth());
		    String lastFluYear = String.valueOf(((DatePicker) findViewById(R.id.datePicker2)).getYear()); 
		    String lastFlu = lastFluDay + "/" + lastFluMonth + "/" + lastFluYear;
		   
		    String hasHernia = ((Spinner) findViewById(R.id.spinner2)).getSelectedItem().toString();
		    String hasBloodInUrine = ((Spinner) findViewById(R.id.spinner3)).getSelectedItem().toString();
		    String hasDiabetes = ((Spinner) findViewById(R.id.spinner4)).getSelectedItem().toString();
		    List<String> prescriptions = new ArrayList<String>();
		    
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
		    		
		    		//Get specific data fields
		    		String firstName = patientInfo[2];
		    		String lastName = patientInfo[3];
		    		String gender = patientInfo[4];
		    		String height = patientInfo[8];
		    		String weight = patientInfo[7];
		    		String age = patientInfo[6];
		    		String bloodType = patientInfo[9];
		    		
			 		    
		    		
			 		    //Get array of weight
		    			List<Integer> weightArray = new ArrayList<Integer>();
			 		    weight = weight.replace("[", "");
			 		    weight = weight.replace("]", "");
			 		    String[] weightStringArray = weight.split(":");
			 		    for(int i = 0; i < weightStringArray.length; i++)
			 		    {
			 		    	weightArray.add(Integer.parseInt(weightStringArray[i]));
			 		    }
			 		    
			 		    //get array of age.
			 		    List<Integer> ageArray = new ArrayList<Integer>();
			 		    age = age.replace("[", "");
			 		    age = age.replace("]", "");
			 		    String[] ageStringArray = age.split(":");
			 		    for(int i = 0; i < ageStringArray.length; i++)
			 		    {
			 		    	ageArray.add(Integer.parseInt(ageStringArray[i]));
			 		    }
		    		
		            
		              // merges user information into a string to be stored.
		              String mergedData = UserInformation.mergePatientInfo(UserInformation.userName, 
		            		  UserInformation.userPassword, firstName, lastName, gender,
		            		  "patient", height, weightArray, ageArray, bloodType,lastTetnus, 
		            		  lastFlu, hasHernia, hasBloodInUrine, hasDiabetes, prescriptions);

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
