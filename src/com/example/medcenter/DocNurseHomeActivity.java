package com.example.medcenter;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DocNurseHomeActivity extends Activity{
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	
	private static final int DIALOG_ALERT = 10;
	
	@Override
	  /**
	   * runs when this page is accessed in order to display the according Activity.
	   */
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_docnursehome);
	    Intent intent = getIntent();
	    setTitle("Find a Patient");
	  }
	
	/**
	   * Finds a patient.
	   * 
	   * @param View view - a view.
	   */
	public void findPatient(View view)
	{
		//Get patients username
		EditText userName = (EditText) findViewById(R.id.editText1);
		// Get the directory path to the download folder and create an app
        // folder there.
		
    	String fileName = userName.getText().toString() + ".txt";
        String filePath = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_DOWNLOADS).toString()
            + "/medCenter/";
        File file = new File(filePath + fileName);
        
        if(file.exists() == false)
        {
        	// Displays an error message if login failed and hides the keyboard
        	InputMethodManager inputManager = (InputMethodManager)            
        			  this.getSystemService(Context.INPUT_METHOD_SERVICE); 
        			    inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),      
        			    InputMethodManager.HIDE_NOT_ALWAYS);
        			    
        	Toast.makeText(getApplicationContext(), "Incorrect patient username!", Toast.LENGTH_LONG).show();
            
        	
        	/*TextView t = new TextView(this);
            t = (TextView) findViewById(R.id.textView2);
            t.setText("Incorrect patient username!");*/
        }
        else if(file.exists() == true)
        {
        	Intent intent = new Intent(getApplicationContext(), 
					UserInfoActivity.class);
    	    intent.putExtra(EXTRA_MESSAGE, userName.getText().toString());
    	    startActivity(intent);
        }
	}
	
	/**
	   * Logs out a user.
	   * 
	   * @param View view - a view.
	   */
	  public void logOutUser(View view) {
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
}
