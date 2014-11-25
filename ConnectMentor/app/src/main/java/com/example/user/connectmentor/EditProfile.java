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

public class EditProfile extends Activity {
    public final static String EXTRA_MESSAGE = "edu.umich.teamivore.MESSAGE";
    public static final String PROFILE_PREFS = "Profile_Prefs" ;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        SharedPreferences profilePref = getSharedPreferences(PROFILE_PREFS,Activity.MODE_PRIVATE);
        SharedPreferences.Editor profileEditor = profilePref.edit();

        //TODO: Navigate back to home activitiy
        getActionBar().setDisplayHomeAsUpEnabled(true);

        //Get user name
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        TextView textViewID = (TextView) findViewById(R.id.edit_username);
        textViewID.setText("Hi,  "+ name);
    }


    public void editAboutMe(View view){
        Intent backIntent = new Intent(this,Profile.class);

        SharedPreferences profilePref = getSharedPreferences(PROFILE_PREFS,Activity.MODE_PRIVATE);
        SharedPreferences.Editor profileEditor = profilePref.edit();

        EditText aboutMeField = (EditText)findViewById(R.id.editAboutMe);

        String aboutmeText = aboutMeField.getText().toString();
        if (!aboutmeText.isEmpty()){ //make sure the user type in sth
            profileEditor.putString(name,aboutmeText);
            profileEditor.apply();

            // Go back to profile screen
            String message = String.valueOf("9999");
            backIntent.putExtra(EXTRA_MESSAGE, message);
            startActivity(backIntent);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_profile, menu);
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
}
