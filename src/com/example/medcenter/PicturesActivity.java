package com.example.medcenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PicturesActivity extends Activity {

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pictures);
    Intent intent = getIntent();
    setTitle("Take a Photo");
    // String message = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);
    // EditText editText = (EditText) findViewById(R.id.textView1);
  }

  public void goToUserInfo(View view) {
    Intent intent = new Intent(getApplicationContext(), UserInfoActivity.class);
    startActivity(intent);
  }
}
