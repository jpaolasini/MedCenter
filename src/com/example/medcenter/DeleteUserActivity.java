package com.example.medcenter;

import java.io.File;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteUserActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete_user);
		setTitle("Delete a User");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.delete_user, menu);
		return true;
	}
	
	
	public void DeleteUser(View view){
		EditText editText = (EditText) findViewById(R.id.deleteThisPatient);
	    String userName = editText.getText().toString();
		//This is a test situation to be removed before demo. 
    	// Get the directory path to the download folder and create an app
        // folder there.
    	String fileName = userName + ".txt";
        String filePath = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_DOWNLOADS).toString()
            + "/medCenter/";
        File file = new File(filePath + fileName);
        
        if(file.exists() == false)
        {
        	// Displays an error message if login failed.
        	Toast.makeText(getApplicationContext(), "User does not exist!",
  		          Toast.LENGTH_LONG).show();
        }
        else if(file.exists() == true)
        {
        	boolean deleted = file.delete();
        	Toast.makeText(getApplicationContext(), "User "+userName+" has been deleted.",
    		          Toast.LENGTH_LONG).show();
        	
        	InputMethodManager inputManager = (InputMethodManager)            
      			  this.getSystemService(Context.INPUT_METHOD_SERVICE); 
      			    inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),      
      			    InputMethodManager.HIDE_NOT_ALWAYS);
        }

	}
}
