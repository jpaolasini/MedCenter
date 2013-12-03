package com.example.medcenter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
//************************NOT NEEDED****************************************//
//This file is not needed and since has been moved to PhotoIntentActivity.
//The reason it has not been removed is to stop errors from occuring if it has been specified elsewhere.

public class TakePictureActivity extends Activity {

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pictures);
    Intent intent = getIntent();
    setTitle("Take a Photo");
    // String message = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);
    // EditText editText = (EditText) findViewById(R.id.textView1);
  }

  private void dispatchTakePictureIntent(int actionCode) {
    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    startActivityForResult(takePictureIntent, actionCode);
  }

  public static boolean isIntentAvailable(Context context, String action) {
    final PackageManager packageManager = context.getPackageManager();
    final Intent intent = new Intent(action);
    List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
        PackageManager.MATCH_DEFAULT_ONLY);
    return list.size() > 0;
  }
}
