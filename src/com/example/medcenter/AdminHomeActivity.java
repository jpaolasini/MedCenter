package com.example.medcenter;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class AdminHomeActivity extends Activity {

        
        private static final int DIALOG_ALERT = 10;
        
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_admin_home);
                setTitle("Welcome, Admin");
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.admin_home, menu);
                return true;
        }
        
        public void AddDocNurse(View view){
                Intent intent = new Intent(getApplicationContext(), AddDoctorActivity.class);
            startActivity(intent);
        }
        
        public void DeleteUser(View view){
                Intent intent = new Intent(getApplicationContext(), DeleteUserActivity.class);
                startActivity(intent);
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
          

          public void LogOut(View view) {
            showDialog(DIALOG_ALERT);
          }

          public void onBackPressed() {
            showDialog(DIALOG_ALERT);
          }

}