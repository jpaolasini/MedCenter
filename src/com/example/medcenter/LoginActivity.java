package com.example.medcenter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

  private static final int DIALOG_ALERT = 10;
  public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

  @Override
  /**
   * runs when this page is accessed in order to display the according Activity.
   */
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_login);
    Intent intent = getIntent();
    String message = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);
    TextView t = new TextView(this);
    t = (TextView) findViewById(R.id.errorMessage);
    //t.setText(message);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.login, menu);
    return true;
  }

  @Override
  public void onBackPressed() {
    Intent intent = new Intent(Intent.ACTION_MAIN);
    intent.addCategory(Intent.CATEGORY_HOME);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
    
  }

  /**
   * Logs in a user. It does this by grabbing the user name and password off the
   * activity page and checks them against predefined values.
 * @throws Exception 
 * @throws IOException 
 * @throws FileNotFoundException 
   */
  public void logInUser(View view) throws FileNotFoundException, IOException, Exception {
    EditText editText = (EditText) findViewById(R.id.userName);
    String userName = editText.getText().toString();
    EditText userPass = (EditText) findViewById(R.id.password);
    String password = userPass.getText().toString();

    if((userName.equals("A")) && password.equals("A"))
    {//This is an admin login in order to demonstrate adding doctors/nurses.
    	Intent intent = new Intent(getApplicationContext(),
			      AdminHomeActivity.class);
			      startActivity(intent);
    }
    else if(!userName.equalsIgnoreCase("") && !password.equalsIgnoreCase(""))
    {//This is a test situation to be removed before demo. 
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
        	String fileData = FileHandler.ReadFile(filePath,fileName);
        	String[] userInfo = UserInformation.parseInfo(fileData);        	
        	
        	if(password.equals(userInfo[1]))
        	{
        		UserInformation.userType = userInfo[5];
        		if(UserInformation.userType.equals("patient"))
        		{
        			UserInformation.userName = userName;
        			Intent intent = new Intent(getApplicationContext(), 
        					UserInfoActivity.class);
            	    intent.putExtra(EXTRA_MESSAGE, userName);
            	    startActivity(intent);
        		}
        		else if(UserInformation.userType.equals("Doctor"))
        		{
        			Intent intent = new Intent(getApplicationContext(), 
        					DocNurseHomeActivity.class);
            	    startActivity(intent);
        		}
        		else if(UserInformation.userType.equals("Nurse"))
        		{
        			Intent intent = new Intent(getApplicationContext(), 
        					DocNurseHomeActivity.class);
            	    startActivity(intent);
        		}
        		else if(UserInformation.userType.equals("admin"))
        		{
        			Intent intent = new Intent(getApplicationContext(),
        			      AddDoctorActivity.class);
        			      startActivity(intent);
        		}
        	}
        	else
        	{
        		
        		//Design Change to Toasts-JP
        		
        		
        		// Displays an error message if login failed.
//                TextView t = new TextView(this);
//                t = (TextView) findViewById(R.id.errorMessage);
//                t.setText("Failed, incorrect password!");
        		Toast.makeText(getApplicationContext(), "Incorrect password!",
        		          Toast.LENGTH_LONG).show();
        	}
        }
    }
    else {
      // Displays an error message if login failed.
    	Toast.makeText(getApplicationContext(), "Failed",
		          Toast.LENGTH_LONG).show();
    }
  }

  /**
   * Transfers a user to the registration page.
   */
  public void goToRegister(View view) {
    Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
    startActivity(intent);
  }
}
