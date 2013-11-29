package com.example.medcenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.widget.Toast;

public class AddDoctorActivity extends Activity {

	  private static final int DIALOG_ALERT = 10;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_adddoctor);
    Intent intent = getIntent();
  }

  public void LogOut(View view) {
	  showDialog(DIALOG_ALERT);
  }
  
  public void onBackPressed(){
	  showDialog(DIALOG_ALERT);
  }

  @Override
  protected Dialog onCreateDialog(int id) {
    switch (id) {
    case DIALOG_ALERT:
      // create out AlterDialog
      Builder builder = new AlertDialog.Builder(this);
      builder.setMessage("Do you want to log out?");
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
      Toast.makeText(getApplicationContext(), "Activity will continue",
          Toast.LENGTH_LONG).show();
    }
  }

  private final class OkOnClickListener implements
      DialogInterface.OnClickListener {
    public void onClick(DialogInterface dialog, int which) {
    	Toast.makeText(getApplicationContext(), "You have been logged out",
  	          Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
  }
  
  public void encryptData(View view) throws Exception

  { // Retrieve the user input from the text fields.
    EditText editText = (EditText) findViewById(R.id.editText1);
    EditText encrypted = (EditText) findViewById(R.id.editText4);
    EditText decrypted = (EditText) findViewById(R.id.editText3);

    // Get the directory path to the download folder and create an app folder
    // there.
    String directoryPath = Environment.getExternalStoragePublicDirectory(
        Environment.DIRECTORY_DOWNLOADS).toString()
        + "/";
    FileHandler.createDirectory("medCenter", directoryPath);

    // Encrypt data and write it to text file in the app directory.
    encrypted.setText(FileHandler.WriteFile(directoryPath + "/medCenter/",
        editText.getText().toString() + ".txt", editText.getText().toString()));

    // read data from an encypted file and decrypt the data.
    decrypted.setText(FileHandler.ReadFile(directoryPath + "/medCenter/",
        editText.getText().toString() + ".txt"));

  }
}
