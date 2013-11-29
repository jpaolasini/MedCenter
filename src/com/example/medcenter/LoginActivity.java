package com.example.medcenter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

	@Override
	/*runs when this page is accessed in order to display the according Activity.
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Intent intent = getIntent();
		String message = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);
		 TextView t=new TextView(this); 
		 t=(TextView)findViewById(R.id.errorMessage); 
		 t.setText(message);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	@Override
	public void onBackPressed(){
		
	}
	
	/*
	 * Logs in a user.
	 * It does this by grabbing the user name and password
	 * off the activity page and checks them against predifined values.
	 */
	public void logInUser(View view)
	{
		 EditText editText = (EditText) findViewById(R.id.userName);
		 String userName = editText.getText().toString();
		 EditText userPass = (EditText) findViewById(R.id.password);
		 String password = userPass.getText().toString();
		 
		 if((userName.equalsIgnoreCase("")) && password.equalsIgnoreCase(""))
		 {
			 Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
			 intent.putExtra(EXTRA_MESSAGE, userName);
			 startActivity(intent);
		 }
		 else
		 {
			 //Displays an error message if login failed.
			 TextView t=new TextView(this); 
			 t=(TextView)findViewById(R.id.errorMessage); 
			 t.setText("Failed");
		 }
	}

	/*
	 * Transfers a user to the registration page.
	 */
	public void goToRegister(View view)
	{
		Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
		startActivity(intent);
	}
	
	/*
	 * logs in an administrator.
	 * Can be removed and have this feature added to regular
	 * login once we have access control for all users.
	 */
	public void logInAdmin(View view)
	{
		 EditText editText = (EditText) findViewById(R.id.userName);
		 String userName = editText.getText().toString();
		 EditText userPass = (EditText) findViewById(R.id.password);
		 String password = userPass.getText().toString();
		 
		 if((userName.equalsIgnoreCase("")) && password.equalsIgnoreCase(""))
		 {
			 Intent intent = new Intent(getApplicationContext(), AddDoctorActivity.class);
			 startActivity(intent);
		 }
		 else
		 {
			 //Displays an error message if login failed.
			 TextView t=new TextView(this); 
			 t=(TextView)findViewById(R.id.errorMessage); 
			 t.setText("Failed");
		 }
	}
	
	
	public void pedometer(View view)
	{
	 Intent intent = new Intent(getApplicationContext(), PedometerActivity.class);
	 startActivity(intent);
	  
	}
}
