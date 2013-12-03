package com.example.medcenter;

import java.io.File;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.widget.Toast;

public class AddDoctorActivity extends Activity {
  public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

  private static final int DIALOG_ALERT = 10;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_adddoctor);
    Intent intent = getIntent();
    setTitle("Add a New Staff Member");
  }

  public void LogOut(View view) {
    showDialog(DIALOG_ALERT);
  }

  public void onBackPressed() {
    showDialog(DIALOG_ALERT);
  }

  public void encryptData(View view) throws Exception { // Retrieve the user
                                                        // input from the text
                                                        // fields.
    Spinner type = (Spinner) findViewById(R.id.spinner1);
    EditText userName = (EditText) findViewById(R.id.editText1);
    EditText password = (EditText) findViewById(R.id.editText2);
    EditText firstName = (EditText) findViewById(R.id.editText3);
    EditText lastName = (EditText) findViewById(R.id.editText4);
    EditText department = (EditText) findViewById(R.id.editText5);
    EditText number = (EditText) findViewById(R.id.editText6);
    EditText email = (EditText) findViewById(R.id.editText7);
    String fileName = userName.getText().toString() + ".txt";

    if (userName.getText().toString().equals("")
        || userName.getText().toString().equals("Username Taken!")
        || password.getText().toString().equals("")
        || firstName.getText().toString().equals("")
        || lastName.getText().toString().equals("")
        || department.getText().toString().equals("")
        || number.getText().toString().equals("")
        || email.getText().toString().equals("")) {
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
      if (department.getText().toString().equals("")) {
        department.setHint("Field Cannot be Empty");
      }
      if (number.getText().toString().equals("")) {
        number.setHint("Field Cannot be Empty");
      }
      if (email.getText().toString().equals("")) {
        email.setHint("Field Cannot be Empty");
      }
    } else {
      try {
        String directoryPath = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_DOWNLOADS).toString()
            + "/";
        FileHandler.createDirectory("medCenter", directoryPath);
        // Check is username is taken
        File file = new File(directoryPath + "/medCenter/" + fileName);
        if (file.exists() == false) {
          // merges doctor nurse information into a string to be stored.
          String mergedData = DoctorNurseInformation.mergeDoctorNurseInfo(type
              .getSelectedItem().toString(), userName.getText().toString(),
              password.getText().toString(), firstName.getText().toString(),
              lastName.getText().toString(), department.getText().toString(),
              number.getText().toString(), email.getText().toString());
          // Encrypt data and write it to text file in the app directory.
          FileHandler.WriteFile(directoryPath + "/medCenter/", fileName,
              mergedData);
          
          
         String decrypted =  FileHandler.ReadFile(directoryPath + "/medCenter/", fileName);
         userName.setText(decrypted);

          Intent intent = new Intent(getApplicationContext(),
              AddDoctorActivity.class);
          Toast.makeText(getApplicationContext(), 
        		  type.getSelectedItem().toString()  + " Added.",
                  Toast.LENGTH_LONG).show();
          //intent.putExtra(EXTRA_MESSAGE, type.getSelectedItem().toString()
          //  + " Added.");
          startActivity(intent);
          
          
        } else if (file.exists() == true) {
          // EditText failed = (EditText) findViewById(R.id.textView10);
          userName.setText("Username Taken!");
          InputMethodManager inputManager = (InputMethodManager)            
    			  this.getSystemService(Context.INPUT_METHOD_SERVICE); 
    			    inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),      
    			    InputMethodManager.HIDE_NOT_ALWAYS);
        }
      } catch (Exception e) {
        // probably this will never be reached
      }
    }
  }

  @Override
  protected Dialog onCreateDialog(int id) {
    switch (id) {
    case DIALOG_ALERT:
      // create out AlterDialog
      Builder builder = new AlertDialog.Builder(this);
      builder.setMessage("Stop adding a doctor?");
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
      
      Intent intent = new Intent(getApplicationContext(), AdminHomeActivity.class);
      startActivity(intent);
    }
  }

}
