/*Kushank Raghav*/
package com.example.user.connectmentor;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;


public class OverViewActivity extends Activity {

    /*Source:https://github.com/aboudalia/Teamivore/blob/master/Teamivore/app/src/main/java/edu/umich/teamivore/OverviewActivity.java*/
    /*Source: http://www.androidhive.info/2012/02/android-custom-listview-with-image-and-text/*/
    public final static String EXTRA_MESSAGE = "edu.umich.teamivore.MESSAGE";
    ArrayList <HashMap<String, String>> recordsList = new ArrayList <HashMap<String, String>>();
    String messagestring = "user_name";
    static final String KEY_NAME = "name";
    static final String KEY_MAJOR = "major";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_view);
        /*Source:https://github.com/aboudalia/Teamivore/blob/master/app/src/main/java/edu/umich/teamivore/TeamDetailActivity.java*/
        // Receiving Message from the User Login Activity - User Name
        Intent intent = getIntent();
        messagestring = intent.getStringExtra(UserLoginActivity.EXTRA_MESSAGE);
        //Setting title of Action bar with user name entered in login page
        getActionBar().setTitle("User List for "+messagestring);
        /*Source:https://github.com/aboudalia/Teamivore/blob/master/Teamivore/app/src/main/java/edu/umich/teamivore/OverviewActivity.java*/
        /*Source: http://www.androidhive.info/2012/02/android-custom-listview-with-image-and-text/*/
        ListView memberListView = (ListView) findViewById(R.id.listview1);
        initializelist();
        OverviewListAdapter adapter=new OverviewListAdapter(this, recordsList);
        memberListView.setAdapter(adapter);
        memberListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
                                    long id) {
                openMemberDetail(id);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.over_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent goSettingIntent = new Intent(this, Settings.class);
            startActivity(goSettingIntent);
        }
        if (id == R.id.action_overview) {
            Intent OverviewIntent = new Intent(this, OverViewActivity.class);
            startActivity(OverviewIntent);
        }
        if (id == R.id.action_profile) {
            Intent ProfileIntent = new Intent(this, Profile.class);
            startActivity(ProfileIntent);
        }
        if (id == R.id.action_message) {
            Intent MessageIntent = new Intent(this, MessageInbox.class);
            startActivity(MessageIntent);

        }
        if (id == R.id.action_logout) {
            Intent logoutIntent = new Intent(this,UserLoginActivity.class);
            startActivity(logoutIntent);
        }
        return super.onOptionsItemSelected(item);
    }
    /*Source:https://github.com/aboudalia/Teamivore/blob/master/Teamivore/app/src/main/java/edu/umich/teamivore/OverviewActivity.java*/
    private void initializelist()
    {
        //Demo data
        recordsList.add(createMember("Andy", "IOE"));
        recordsList.add(createMember("Anthony", "EE"));
        recordsList.add(createMember("Kurt", "CS"));
        recordsList.add(createMember("Kush", "SI"));
        recordsList.add(createMember("Alison", "SI"));
        recordsList.add(createMember("Erica", "SI"));

        //This function will retrieve records from database
        // Make connection to DB
        // Retrieve values of a column from table using Select statement
        // loop through values
         //memberlist.add(createMember("member", value));

    }
    /*Source:https://github.com/aboudalia/Teamivore/blob/master/Teamivore/app/src/main/java/edu/umich/teamivore/OverviewActivity.java*/
     /*Source: http://www.androidhive.info/2012/02/android-custom-listview-with-image-and-text/*/
    private HashMap<String, String> createMember(String key, String name) {
        HashMap<String, String> team = new HashMap<String, String>();
        team.put(KEY_NAME, key);
        team.put(KEY_MAJOR, name);
        return team;
    }
    /*Source:https://github.com/aboudalia/Teamivore/blob/master/Teamivore/app/src/main/java/edu/umich/teamivore/OverviewActivity.java*/
    public void openMemberDetail(long id) {
        Intent intent = new Intent(this, Profile.class);
        String message = String.valueOf(id);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void goToFilter(View view){
        Intent filterIntent = new Intent(this, Filter.class);
        startActivity(filterIntent);
    }

}
