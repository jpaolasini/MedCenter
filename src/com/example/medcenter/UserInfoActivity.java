package com.example.medcenter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserInfoActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	 private static final int DIALOG_ALERT = 10;
	 
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_userinf);
    Intent intent = getIntent();
    String patientUserName = "";
    boolean tetanus = false;
    boolean fluShot = false;
    
    if(UserInformation.userType.equals("Doctor"))
    {
    	patientUserName = intent.getStringExtra(DocNurseHomeActivity.EXTRA_MESSAGE);
    	//Turn on the edit info button if a doctor/nurse is viewing this page.	
      	Button editInfo = (Button) findViewById(R.id.editInfoButton);      		
      	editInfo.setVisibility(View.GONE);
      	
      	//Turn on the edit info button if a doctor/nurse is viewing this page.	
      	Button editMed = (Button) findViewById(R.id.editMedicalHistoryButton);      		
      	editMed.setVisibility(View.VISIBLE);
      	
      	Button prescriptionManagerBtn = (Button) findViewById(R.id.PrescriptionMan);
      	prescriptionManagerBtn.setVisibility(View.VISIBLE);
    }
    else if(UserInformation.userType.equals("Nurse"))
    {
    	patientUserName = intent.getStringExtra(DocNurseHomeActivity.EXTRA_MESSAGE);
    	//Turn on the edit info button if a doctor/nurse is viewing this page.	
      	Button editInfo = (Button) findViewById(R.id.editInfoButton);      		
      	editInfo.setVisibility(View.VISIBLE);
      	
      	//Turn on the edit info button if a doctor/nurse is viewing this page.	
      	Button editMed = (Button) findViewById(R.id.editMedicalHistoryButton);      		
      	editMed.setVisibility(View.GONE);
      	
      	Button prescriptionManagerBtn = (Button) findViewById(R.id.PrescriptionMan);
      	prescriptionManagerBtn.setVisibility(View.GONE);
    	
    }
    else if(UserInformation.userType.equals("patient"))
    {
        patientUserName = intent.getStringExtra(HomeActivity.EXTRA_MESSAGE);
        
        //Turn off the edit info button if a patient is viewing this page.	
      	Button editInfo = (Button) findViewById(R.id.editInfoButton);      		
      	editInfo.setVisibility(View.GONE);
      	
      	Button editMed = (Button) findViewById(R.id.editMedicalHistoryButton);      		
      	editMed.setVisibility(View.GONE);
      	
      	Button prescriptionManagerBtn = (Button) findViewById(R.id.PrescriptionMan);
      	prescriptionManagerBtn.setVisibility(View.GONE);
    }
    else
    {
    	patientUserName = intent.getStringExtra(EditPatientInformationActivity.EXTRA_MESSAGE);
    	//Turn on the edit info button if a doctor/nurse is viewing this page.	
      	Button editInfo = (Button) findViewById(R.id.editInfoButton);      		
      	editInfo.setVisibility(View.VISIBLE);
      	
      	Button editMed = (Button) findViewById(R.id.editMedicalHistoryButton);      		
      	editMed.setVisibility(View.GONE);
      	
      	Button prescriptionManagerBtn = (Button) findViewById(R.id.PrescriptionMan);
      	prescriptionManagerBtn.setVisibility(View.GONE);
    }
    
    UserInformation.userName = patientUserName;
    // Get the directory path to the download folder and create an app
    // folder there.
    String directoryPath = Environment.getExternalStoragePublicDirectory(
        Environment.DIRECTORY_DOWNLOADS).toString()
        + "/";
    String fileName = patientUserName + ".txt";
    try {
    	//Get the patients information.
		String data = FileHandler.ReadFile(directoryPath + "medCenter/", fileName);
		String[] patientInfo = UserInformation.parseInfo(data);
		UserInformation.userPassword = patientInfo[1];
		String weight = patientInfo[7];
		String age = patientInfo[6];
		
		 //Get array of weight
		   // List<Integer> weightArray = new ArrayList<Integer>();
		    weight = weight.replace("[", "");
		    weight = weight.replace("]", "");
		    String[] weightArray = weight.split(":");
		    int currentWeight = weightArray.length;
		    
		    //get array of age.
		   // List<Integer> ageArray = new ArrayList<Integer>();
		    age = age.replace("[", "");
		    age = age.replace("]", "");
		    String[] ageArray = age.split(":");
		    int currentAge = ageArray.length;
		
		//Display the patients information
	    TextView patientName = new TextView(this);
		patientName = (TextView) findViewById(R.id.errorMessage);
		patientName.setText(patientInfo[2] + " " + patientInfo[3] );	

		
		TextView ageText = new TextView(this);
		ageText = (TextView) findViewById(R.id.textView2);
		ageText.setText("Age: " + ageArray[currentAge-1]);	
		
		TextView gender = new TextView(this);
		gender = (TextView) findViewById(R.id.textView1);
		gender.setText("Gender: " + patientInfo[4]);	
		
		TextView weightText = new TextView(this);
		weightText = (TextView) findViewById(R.id.textView3);
		weightText.setText("Weight: " + weightArray[currentWeight-1]);	
		
		TextView height = new TextView(this);
		height = (TextView) findViewById(R.id.textView4);
		height.setText("Height: " + patientInfo[8]);	
		
		TextView bloodType = new TextView(this);
		bloodType = (TextView) findViewById(R.id.textView5);
		bloodType.setText("BloodType: " + patientInfo[9]);
		
		
		Calendar c = Calendar.getInstance();
		System.out.println("Current time => " + c.getTime());

		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		String formattedDate = df.format(c.getTime());
		  
		String[] date1 = formattedDate.split("-");
		String[] date2 = patientInfo[10].split("/");
		String[] date3 = patientInfo[11].split("/");
	
		
		if((Integer.parseInt(date1[2])-Integer.parseInt(date2[2]))>10){
			tetanus = true;
			   
		}
		
		if((Integer.parseInt(date1[2])-Integer.parseInt(date3[2]))>1){
			fluShot = true;
		}
		
    } catch (Exception e) {  		
	}    
    

	
	
    if(tetanus){
    	NotificationCompat.Builder mBuilder =
    	        new NotificationCompat.Builder(this)
    	        .setSmallIcon(R.drawable.ic_launcher)
    	        .setContentTitle("MedCenter")
    	        .setContentText("Come in for your Tetanus Vaccine!");
    	// Creates an explicit intent for an Activity in your app
    	Intent resultIntent = new Intent(this, ScheduleActivity.class);

    	// The stack builder object will contain an artificial back stack for the
    	// started Activity.
    	// This ensures that navigating backward from the Activity leads out of
    	// your application to the Home screen.
    	TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
    	// Adds the back stack for the Intent (but not the Intent itself)
    	stackBuilder.addParentStack(ScheduleActivity.class);
    	// Adds the Intent that starts the Activity to the top of the stack
    	stackBuilder.addNextIntent(resultIntent);
    	PendingIntent resultPendingIntent =
    	        stackBuilder.getPendingIntent(
    	            0,
    	            PendingIntent.FLAG_UPDATE_CURRENT
    	        );
    	mBuilder.setContentIntent(resultPendingIntent);
    	NotificationManager mNotificationManager =
    	    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    	int mId = 0;
		// mId allows you to update the notification later on.
    	mNotificationManager.notify(mId , mBuilder.build());
    }
    

    if(fluShot){
    	NotificationCompat.Builder mBuilder =
    	        new NotificationCompat.Builder(this)
    	        .setSmallIcon(R.drawable.ic_launcher)
    	        .setContentTitle("MedCenter")
    	        .setContentText("Come in for your Flu Vaccine!");
    	// Creates an explicit intent for an Activity in your app
    	Intent resultIntent = new Intent(this, ScheduleActivity.class);

    	// The stack builder object will contain an artificial back stack for the
    	// started Activity.
    	// This ensures that navigating backward from the Activity leads out of
    	// your application to the Home screen.
    	TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
    	// Adds the back stack for the Intent (but not the Intent itself)
    	stackBuilder.addParentStack(ScheduleActivity.class);
    	// Adds the Intent that starts the Activity to the top of the stack
    	stackBuilder.addNextIntent(resultIntent);
    	PendingIntent resultPendingIntent =
    	        stackBuilder.getPendingIntent(
    	            0,
    	            PendingIntent.FLAG_UPDATE_CURRENT
    	        );
    	mBuilder.setContentIntent(resultPendingIntent);
    	NotificationManager mNotificationManager =
    	    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    	int mId = 0;
		// mId allows you to update the notification later on.
    	mNotificationManager.notify(mId , mBuilder.build());
    }
    
  }
  

  // Go to the pictures activity
  public void goToPictures(View view) {
    Intent intent = new Intent(getApplicationContext(),
        PhotoIntentActivity.class);
    startActivity(intent);
  }
  
  //go to the prescription manager
  public void goToPrescriptionMan(View view) {
	  Intent intent = new Intent(getApplicationContext(), PrescriptionManager.class);
	  startActivity(intent);
  }

  // Go to the medical chart activity
  public void goToMedChart(View view) {
    Intent intent = new Intent(getApplicationContext(), MedChartActivity.class);
    intent.putExtra(EXTRA_MESSAGE, UserInformation.userName);
    startActivity(intent);
  }

  public void goToGraphing(View view) {
    Intent intent = new Intent(getApplicationContext(), GraphingActivity.class);
    startActivity(intent);
  }

  // Go to the schedule activity
  public void goToSchedule(View view) {
    Intent intent = new Intent(getApplicationContext(), ScheduleActivity.class);
    startActivity(intent);
  }
  
  public void goToEditPatientInfo (View view)
  {
	  Intent intent = new Intent(getApplicationContext(), EditPatientInformationActivity.class);
	  intent.putExtra(EXTRA_MESSAGE,UserInformation.userName);
	  startActivity(intent);
  }
  
  public void goToEditPatientMedicalHistory (View view)
  {
	  Intent intent = new Intent(getApplicationContext(), EditPatientMedicalHistory.class);
	  intent.putExtra(EXTRA_MESSAGE,UserInformation.userName);
	  startActivity(intent);
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
	  if (UserInformation.userType.equals("Doctor") || UserInformation.userType.equals("Nurse")){
		  Intent intent = new Intent(getApplicationContext(), DocNurseHomeActivity.class);
		  startActivity(intent);
	  }
	  else
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
