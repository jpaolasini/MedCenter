package com.example.medcenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;

public class AddDoctorActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adddoctor);
		Intent intent = getIntent();
	}
	
	public void LogOut(View view)
	{
		Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
		startActivity(intent);
	}
	
	public void encryptData(View view) throws Exception
	
	{	//Retrieve the user input from the text fields.
	    EditText editText = (EditText) findViewById(R.id.editText1);
		EditText encrypted = (EditText) findViewById(R.id.editText4);
		EditText decrypted = (EditText) findViewById(R.id.editText3);
		
		//Get the directory path to the download folder and create an app folder there.
		String directoryPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()+ "/";
		FileHandler.createDirectory("medCenter", directoryPath);
		
		//Encrypt data and write it to text file in the app directory.
		encrypted.setText(FileHandler.WriteFile(directoryPath + "/medCenter/",editText.getText().toString() + ".txt", editText.getText().toString()));
		
		//read data from an encypted file and decrypt the data.
		decrypted.setText(FileHandler.ReadFile(directoryPath + "/medCenter/", editText.getText().toString() + ".txt"));
		
		
//		byte[] array = new byte[myStr.length()];
//		array = myStr.getBytes();
//	    byte[] arrayEnc = AESencrp.encrypt(array);
//	    byte[] arrayDec = AESencrp.decrypt(arrayEnc);
//	    String encStr = new String(arrayEnc);
//	    String strS = new String(arrayDec);
//	    encrypted.setText(encStr);
//	    decrypted.setText(strS);
	}
}
