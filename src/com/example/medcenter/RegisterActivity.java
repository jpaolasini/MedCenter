package com.example.medcenter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterActivity extends Activity {
  public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
  private static final int DIALOG_ALERT = 10;

  public void onBackPressed(){
	  showDialog(DIALOG_ALERT);
  }

  @Override
  protected Dialog onCreateDialog(int id) {
    switch (id) {
    case DIALOG_ALERT:
      // create out AlterDialog
      Builder builder = new AlertDialog.Builder(this);
      builder.setMessage("Cancel registration?");
      builder.setCancelable(true);
      builder.setPositiveButton("Yes", new OkOnClickListener());
      builder.setNegativeButton("No", new CancelOnClickListener());
      AlertDialog dialog = builder.create();
      dialog.show();
    }
    return super.onCreateDialog(id);
  }

  private final class CancelOnClickListener implements
      DialogInterface.OnClickListener {
    public void onClick(DialogInterface dialog, int which) {

    }
  }

  private final class OkOnClickListener implements
      DialogInterface.OnClickListener {
    public void onClick(DialogInterface dialog, int which) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
  }
  
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);
    Intent intent = getIntent();
    setTitle("Register a New User");
  }

  /*
   * Verify users specified inputs and register them to the system.
   */
  public void completeReg(View view) throws Exception {
    // Retrieve the user input from the text fields.
    EditText userName = (EditText) findViewById(R.id.editText1);
    EditText password = (EditText) findViewById(R.id.editText2);
    EditText firstName = (EditText) findViewById(R.id.editText3);
    EditText lastName = (EditText) findViewById(R.id.editText4);
    EditText height = (EditText) findViewById(R.id.editText5);
    EditText weight = (EditText) findViewById(R.id.editText6);
    List<Integer> weightArray = new ArrayList<Integer>();
    weightArray.add(Integer.parseInt(weight.getText().toString()));
    
    EditText age = (EditText) findViewById(R.id.editText7);
    List<Integer> ageArray = new ArrayList<Integer>();
    ageArray.add(Integer.parseInt(age.getText().toString()));
    
    Spinner gender = (Spinner) findViewById(R.id.spinner2);
    Spinner bloodType = (Spinner) findViewById(R.id.spinner1);
    String patientType = "patient";
    String fileName = userName.getText().toString() + ".txt";
    List<String> prescriptions = new ArrayList<String>();
    
    if (userName.getText().toString().equals("")
            || userName.getText().toString().equals("Username Taken!")
            || password.getText().toString().equals("")
            || firstName.getText().toString().equals("")
            || lastName.getText().toString().equals("")
            || height.getText().toString().equals("")
            || weight.getText().toString().equals("")
            || age.getText().toString().equals("")
            || patientType.equals("")) {
          if (userName.getText().toString().equals("")) {
            userName.setHint("Field Cannot be Empty");
          }
          if (password.getText().toString().equals("")) {
            password.setHint("Field Cannot be Empty");
          }
          if (firstName.getText().toString().equals("")) {
            firstName.setHint("Field Cannot be Empty");
          }
          if (lastName.getText().toString().equals("")) {
            lastName.setHint("Field Cannot be Empty");
          }
          if (height.getText().toString().equals("")) {
            height.setHint("Field Cannot be Empty");
          }
          if (weight.getText().toString().equals("")) {
            weight.setHint("Field Cannot be Empty");
          }
          if (age.getText().toString().equals("")) {
            age.setHint("Field Cannot be Empty");
          }
          if (patientType.equals("")) {
                weight.setHint("Field Cannot be Empty");
          }
    }

    else if (firstName.getText().toString().matches(".*\\d.*")
        || lastName.getText().toString().matches(".*\\d.*")
        || bloodType.getSelectedItem().toString().matches(".*\\d.*")) {
      //userName.setText("Incorrect Parameters");
    	
      //show a toast message when there are incorrect values and hide keyboard 
      Toast.makeText(getApplicationContext(), "You have incorrect values!",Toast.LENGTH_LONG).show();
      InputMethodManager inputManager = (InputMethodManager)            
			  this.getSystemService(Context.INPUT_METHOD_SERVICE); 
			    inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),      
			    InputMethodManager.HIDE_NOT_ALWAYS);
    } else {

      try {

        double heightDouble = Double.parseDouble(height.getText().toString());
        double weightDouble = Double.parseDouble(weight.getText().toString());
        int ageInteger = Integer.parseInt(age.getText().toString());

        // Get the directory path to the download folder and create an app
        // folder there.
        String directoryPath = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_DOWNLOADS).toString()
            + "/";
        FileHandler.createDirectory("medCenter", directoryPath);

        // Check if username is taken
        File file = new File(directoryPath + "/medCenter/" + fileName);
        if (file.exists() == false) {
          // merges user information into a string to be stored.
          String mergedData = UserInformation.mergePatientInfo(userName
              .getText().toString(), password.getText().toString(), firstName
              .getText().toString(), lastName.getText().toString(),gender.getSelectedItem().toString(),
              patientType, height.getText().toString(), weightArray, ageArray, bloodType
                  .getSelectedItem().toString(),"","","","","",prescriptions);

          // Encrypt data and write it to text file in the app directory.
          FileHandler.WriteFile(directoryPath + "/medCenter/", fileName,
              mergedData);

     Intent intent = new Intent(getApplicationContext(),
             LoginActivity.class);

     Toast.makeText(getApplicationContext(), "Registration Successful",
	          Toast.LENGTH_LONG).show();
     
         startActivity(intent);
          
          
        } else if (file.exists() == true) {
          // EditText failed = (EditText) findViewById(R.id.textView10);
          //userName.setText("Username Taken!");
          //show a toast message when there are incorrect values and hide keyboard 
          Toast.makeText(getApplicationContext(), "This username is already taken",Toast.LENGTH_LONG).show();
          InputMethodManager inputManager = (InputMethodManager)            
    			  this.getSystemService(Context.INPUT_METHOD_SERVICE); 
    			    inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),      
    			    InputMethodManager.HIDE_NOT_ALWAYS);
        }
      } catch (Exception e) {
       // userName.setHint("Incorrect Parameters");
      //show a toast message when there are incorrect values and hide keyboard 
        Toast.makeText(getApplicationContext(), "You have incorrect values!",Toast.LENGTH_LONG).show();
        InputMethodManager inputManager = (InputMethodManager)            
  			  this.getSystemService(Context.INPUT_METHOD_SERVICE); 
  			    inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),      
  			    InputMethodManager.HIDE_NOT_ALWAYS);
      }
    }
  }
  }
