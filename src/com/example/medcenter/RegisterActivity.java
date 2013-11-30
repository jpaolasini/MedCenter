package com.example.medcenter;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
  public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);
    Intent intent = getIntent();
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
    EditText age = (EditText) findViewById(R.id.editText7);
    EditText bloodType = (EditText) findViewById(R.id.editText8);
    String patientType = "patient";
    String fileName = userName.getText().toString() + ".txt";

    if (firstName.getText().toString().matches(".*\\d.*")
        || lastName.getText().toString().matches(".*\\d.*")
        || bloodType.getText().toString().matches(".*\\d.*")) {
      userName.setText("Incorrect Parameters");
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

        // Check is username is taken
        File file = new File(directoryPath + "/medCenter/" + fileName);
        if (file.exists() == false) {
          // merges user information into a string to be stored.
          String mergedData = UserInformation.mergePatientInfo(userName
              .getText().toString(), password.getText().toString(), firstName
              .getText().toString(), lastName.getText().toString(),
              patientType, height.getText().toString(), weight.getText()
                  .toString(), age.getText().toString(), bloodType.getText()
                  .toString());

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
          userName.setText("Username Taken!");
        }
      } catch (Exception e) {
        userName.setHint("Incorrect Parameters");
      }
    }
  }
}
