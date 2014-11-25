/*Kushank Raghav*/
package com.example.user.connectmentor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
import com.facebook.*;
import com.facebook.model.*;
import com.google.android.gms.common.SignInButton;


public class UserLoginActivity extends Activity {
    public final static String EXTRA_MESSAGE = "edu.umich.teamivore.MESSAGE";
    public static final String LOGIN_PREFS = "Login_Prefs" ;
    public static final String SESSION_PREFS = "Session_Prefs" ;
    public static final String name = "nameKey";
    public static final String pass = "passwordKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        //Remove Login key at beginning of Login Screen
        SharedPreferences sessionpref = getSharedPreferences(SESSION_PREFS,Activity.MODE_PRIVATE);
        SharedPreferences.Editor seditor = sessionpref.edit();
        if (sessionpref.contains("Login"))
        {
            seditor.remove("Login");
            seditor.commit();
        }




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




    }
    public void logingoogle(View view) {


    }
    public void signin(View view) {
        //Source: https://developer.android.com/training/basics/firstapp/starting-activity.html
        //Source: Week 11 Lecture
        //Get Shared Preferences object
        SharedPreferences loginsharedpref = getSharedPreferences(LOGIN_PREFS,Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginsharedpref.edit();
        //Shared Preferences for Login Session
        SharedPreferences sessionpref = getSharedPreferences(SESSION_PREFS,Activity.MODE_PRIVATE);
        SharedPreferences.Editor seditor = sessionpref.edit();
        Intent intent = new Intent(this,OverViewActivity.class);
        // To send the username to next view and login/signup, get its value from the user name text box
        // Retrieve the password from the password text box
        // Retrieve major from major text box
        EditText username = (EditText)findViewById(R.id.editText);
        EditText password = (EditText)findViewById(R.id.editText2);
        EditText major = (EditText)findViewById(R.id.editText3);
        String u = username.getText().toString();
        String p = password.getText().toString();
        String m = major.getText().toString();
        String j = p + ":" + m;

        if (u.equalsIgnoreCase("")) {
            username.setHint("Please enter username");
        }
        if (p.equalsIgnoreCase("")) {
            password.setHint("Please enter password");
        }
        if (m.equalsIgnoreCase("")) {
            major.setHint("Please enter major");
        }

        if (!u.isEmpty() && !p.isEmpty() && !m.isEmpty()) {


            //Check if the username already exists in shared preferences file
            //Retrieve corresponding password and major
            //if retrieved password and major == password and major
            // Login the user,use Intent to switch to next Activity -> OverView Activity
            //if retrieved password and/or major != password and/or major
            // Show Wrong password or Major
            if (loginsharedpref.contains(u)) {
                if (loginsharedpref.getString(u, null).equals(j)) {

                    seditor.putString("Login",u);
                    seditor.apply();
                    // Send the string using Intent
                    intent.putExtra(EXTRA_MESSAGE, u);
                    startActivity(intent);

                } else {
                    TextView wrongwdlabel = (TextView) findViewById(R.id.textView2);
                    wrongwdlabel.setText("Wrong Password or Major");
                }

            }
            //if the username does not exist
            // insert username in shared preferences
            //insert password and major in shared preferences
            // insert session information in shared preferences
            else {
                editor.putString(u, j);
                editor.apply();
                seditor.putString("Login",u);
                seditor.apply();
                // Send the username string using Intent
                intent.putExtra(EXTRA_MESSAGE, u);
                startActivity(intent);

            }
        }






    }

    }

