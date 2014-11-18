/*Kushank Raghav*/
package com.example.user.connectmentor;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
    public static final String LOGIN_PREFS = "Login_Pref" ;
    public static final String name = "nameKey";
    public static final String pass = "passwordKey";

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
        //Source: https://developer.android.com/training/basics/firstapp/starting-activity.html
        //Source: Week 11 Lecture
        //Get Shared Preferences object
        SharedPreferences loginsharedpref = getSharedPreferences(LOGIN_PREFS,Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginsharedpref.edit();
        Intent intent = new Intent(this,OverViewActivity.class);
        // To send the username to next view and login/signup, get its value from the user name text box
        // Retrieve the password from the password text box
        EditText username = (EditText)findViewById(R.id.editText);
        EditText password = (EditText)findViewById(R.id.editText2);
        String u = username.getText().toString();
        String p = password.getText().toString();
        //Check if the username already exists in shared preferences file
        //Retrieve corresponding password
        //if retrieved password == password
        // Login the user,use Intent to switch to next Activity -> OverView Activity
        //if retrieved password != password
        // Show Wrong password
        if (loginsharedpref.contains(u)) {
            if(loginsharedpref.getString(u,null).equals(p))
            {
                // Send the string using Intent
                intent.putExtra(EXTRA_MESSAGE, u);
                startActivity(intent);

            }
            else {
                TextView wrongwdlabel = (TextView) findViewById(R.id.textView2);
                wrongwdlabel.setText("Wrong Password");
            }

        }
        //if the username does not exist
        // insert username in shared preferences
        //insert password in shared preferences
        else
        {
            editor.putString(u,p);
            editor.apply();
            // Send the username string using Intent
            intent.putExtra(EXTRA_MESSAGE,u);
            startActivity(intent);

        }






    }

    }

