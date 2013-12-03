package com.example.medcenter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity {

	  private static final int DIALOG_ALERT = 10;
	  public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

  @Override
  /**
   * runs when this page is accessed in order to display the according Activity.
   */
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    Intent intent = getIntent();
    String userName = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);
    UserInformation.userName = userName;
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
      builder.setMessage("Cancel prescription manager?");
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
    	Toast.makeText(getApplicationContext(), "Prescription manager cancelled",
    	          Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), UserInfoActivity.class);
        startActivity(intent);
    }
  }

  /**
   * Transfers to the user info page.
   */
  public void viewUserInfo(View view) {
    Intent intent = new Intent(getApplicationContext(), UserInfoActivity.class);
    intent.putExtra(EXTRA_MESSAGE, UserInformation.userName);
    startActivity(intent);
  }
}
