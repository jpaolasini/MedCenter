package com.example.medcenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.ImageView;
import android.widget.TextView;

public class PedometerActivity extends Activity implements SensorEventListener {
  private float mLastX, mLastY, mLastZ;
  private boolean mInitialized;
  private SensorManager mSensorManager;
  private Sensor mAccelerometer;
  private final float NOISE = (float) 2.0;

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pedometer);
    Intent intent = getIntent();
    mInitialized = false;
    mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    mSensorManager.registerListener(this, mAccelerometer,
        SensorManager.SENSOR_DELAY_NORMAL);
  }

  protected void onResume() {

    super.onResume();

    mSensorManager.registerListener(this, mAccelerometer,
        SensorManager.SENSOR_DELAY_NORMAL);

  }

  protected void onPause() {

    super.onPause();

    mSensorManager.unregisterListener(this);

  }

  @Override
  public void onAccuracyChanged(Sensor arg0, int arg1) {
    // TODO Auto-generated method stub
  }

  @Override
  public void onSensorChanged(SensorEvent arg0) {
    EditText editText = (EditText) findViewById(R.id.textView1);
    editText.setText("wat");
  }

}
