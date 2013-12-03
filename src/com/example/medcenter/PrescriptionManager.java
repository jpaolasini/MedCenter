package com.example.medcenter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class PrescriptionManager extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prescription_manager);
		Intent intent = getIntent();
		
		String directoryPath = Environment.getExternalStoragePublicDirectory(
		        Environment.DIRECTORY_DOWNLOADS).toString()
		        + "/";
		    String fileName = UserInformation.userName + ".txt";
		 
		    //Get the patients information.
			String data;
			try {
				data = FileHandler.ReadFile(directoryPath + "medCenter/", fileName);
				
				String[] patientInfo = UserInformation.parseInfo(data);
				
				String prescriptionString = patientInfo[15];
				prescriptionString = prescriptionString.replace("[", "");
				prescriptionString = prescriptionString.replace("]", "");
				
				String[] prescriptions = prescriptionString.split(":");
				ArrayList<String> spinnerArray = new ArrayList<String>();
				
				for(int i = 0; i < prescriptions.length; i++)
				{
					spinnerArray.add(prescriptions[i]);
				}				

			    Spinner spinner = (Spinner) findViewById(R.id.presSpinner);
			    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
			    spinner.setAdapter(spinnerArrayAdapter);
			} catch (Exception e) {

			} 			
			
	}
	
	public void addPres(View view)
	{
		String directoryPath = Environment.getExternalStoragePublicDirectory(
		        Environment.DIRECTORY_DOWNLOADS).toString()
		        + "/";
		    String fileName = UserInformation.userName + ".txt";
		    String currentPrescription = ((EditText) findViewById(R.id.prescriptionsText)).getText().toString();
		 
		    //Get the patients information.
			String data;
			try {
				data = FileHandler.ReadFile(directoryPath + "medCenter/", fileName);
				
				String[] patientInfo = UserInformation.parseInfo(data);
				
				//Add the new prescription
				String prescriptionString = patientInfo[15];
				prescriptionString = prescriptionString.replace("[", "");
				prescriptionString = prescriptionString.replace("]", "");
				String[] prescriptions = prescriptionString.split(":");
				List<String> prescriptionsNew =  new ArrayList<String>();
				for(int i = 0; i < prescriptions.length + 1; i++)
				{
					if(i < prescriptions.length)
					{
						prescriptionsNew.add(prescriptions[i]);
					}
					else if(i == prescriptions.length)
					{
						Calendar c = Calendar.getInstance(); 
						int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
						int month = c.get(Calendar.MONTH) + 1;
						int year = c.get(Calendar.YEAR);
						String date = dayOfMonth + "/" + month + "/" + year;
						prescriptionsNew.add(currentPrescription + "-> " + date);
					}		
				}		      	
				
				Intent intent = new Intent(getApplicationContext(),
	      			      DocNurseHomeActivity.class);
	      			      startActivity(intent);
			
				
				//get age array
				String ageString = patientInfo[6];
				Log.d("AGEEEE", patientInfo[6]);
				ageString = ageString.replace("[", "");
				ageString = ageString.replace("]", "");
				List<Integer> age =  new ArrayList<Integer>();
				String[] ageArray = ageString.split(":");
				for(int i = 0; i < ageArray.length ; i++)
				{
						age.add(Integer.parseInt(ageArray[i]));
				}
				
				//get weight array
				String weightString = patientInfo[7];
				weightString = weightString.replace("[", "");
				weightString = weightString.replace("]", "");
				List<Integer> weight =  new ArrayList<Integer>();
				String[] weightArray = weightString.split(":");
				for(int i = 0; i < weightArray.length; i++)
				{
					weight.add(Integer.parseInt(weightArray[i]));
				}
				
				
				
				String mergedData = UserInformation.mergePatientInfo(patientInfo[0], patientInfo[1], patientInfo[2],
						patientInfo[3], patientInfo[4], patientInfo[5], patientInfo[8], weight, age, patientInfo[9], patientInfo[10], 
						patientInfo[11], patientInfo[12], patientInfo[13], patientInfo[14], prescriptionsNew);
				
				FileHandler.WriteFile(directoryPath + "medCenter/", fileName, mergedData);
					
			}
			catch(Exception e)
			{
				
			}
		
	}

}
