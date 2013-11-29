package com.example.medcenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends Activity {

  @Override
  /**
   * runs when this page is accessed in order to display the according Activity.
   */
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    Intent intent = getIntent();
    // String message = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);
    // EditText editText = (EditText) findViewById(R.id.textView1);
  }

  /**
   * Logs out a user.
   * 
   * @param View view - a view.
   */
  public void logOutUser(View view) {
    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
    startActivity(intent);
  }

  /**
   * Transfers to the user info page.
   */
  public void viewUserInfo(View view) {
    Intent intent = new Intent(getApplicationContext(), UserInfoActivity.class);
    startActivity(intent);
  }
}
