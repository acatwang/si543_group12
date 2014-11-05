/*Kushank Raghav*/
package com.example.user.connectmentor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import com.facebook.*;
import com.facebook.model.*;


public class UserLoginActivity extends Activity {
    public final static String EXTRA_MESSAGE = "edu.umich.teamivore.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void loginfb(View view) {
         //start Facebook Authentication
         //check for authentication state changes
         //result of authentication
         //if result == success
           // Login user, use Intent to switch to next Activity -> OverView Activity


    }
    public void logingoogle(View view) {
        //start Google Authentication
        //check for authentication state changes
        //retrieve result of authentication
        //if result == success
          // Login user, use Intent to switch to next Activity -> OverView Activity


    }
    public void signin(View view) {
        //Retrieve values from text fields
        //store them in variables-username,password
        //Connect to Database
        //Check if the username already exists in database by Select statement with where clause
         //if the username exists
           //retrieve corresponding password from db by Select statement with where clause
           //if retrieved password == password
              // Login the user,use Intent to switch to next Activity -> OverView Activity
           //if retrieved password != password
              // Do Nothing (stay on same activity)
         //if the username does not exist
           // insert username in db
           //insert password in db
           //Login the user, use Intent to switch to next Activity -> OverView Activity

        Intent intent = new Intent(this,OverViewActivity.class);
        //Source: https://developer.android.com/training/basics/firstapp/starting-activity.html
        // To send the username to next view, get its value from the user name text box
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        // Send the string using Intent
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);

    }

    }

