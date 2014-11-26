/*Kushank Raghav*/
package com.example.user.connectmentor;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
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
import android.app.FragmentManager;


public class OverViewActivity extends Activity {

    /*Source:https://github.com/aboudalia/Teamivore/blob/master/Teamivore/app/src/main/java/edu/umich/teamivore/OverviewActivity.java*/
    /*Source: http://www.androidhive.info/2012/02/android-custom-listview-with-image-and-text/*/
    public final static String EXTRA_MESSAGE = "edu.umich.teamivore.MESSAGE";
    ArrayList <HashMap<String, String>> recordsList = new ArrayList <HashMap<String, String>>();
    static final String KEY_NAME = "name";
    static final String KEY_MAJOR = "major";
    public static final String LOGIN_PREFS = "Login_Prefs" ;
    public static final String SESSION_PREFS = "Session_Prefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_view);
        //Getting current logged in username from session shared preferences
        //Setting title of Action bar with user name entered in login page
        SharedPreferences loginsharedpref = getSharedPreferences(LOGIN_PREFS,Activity.MODE_PRIVATE);
        SharedPreferences sessionpref = getSharedPreferences(SESSION_PREFS,Activity.MODE_PRIVATE);
        String user = sessionpref.getString("Login","");
        if (user != null) {
            if(!user.isEmpty()) {
                getActionBar().setTitle("User List - " + user);
            }
        }

        /*Source:https://github.com/aboudalia/Teamivore/blob/master/Teamivore/app/src/main/java/edu/umich/teamivore/OverviewActivity.java*/
        /*Source: http://www.androidhive.info/2012/02/android-custom-listview-with-image-and-text/*/
        ListView memberListView = (ListView) findViewById(R.id.listview1);
        initializelist();
        OverviewListAdapter adapter=new OverviewListAdapter(this, recordsList);
        memberListView.setAdapter(adapter);
        memberListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
                                    long id) {
                String listname = recordsList.get(position).get("name").toString();
                openMemberDetail(listname);
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
            //Sent id to indicate this is current user
            String message = String.valueOf("9999");
            ProfileIntent.putExtra(EXTRA_MESSAGE, message);
            startActivity(ProfileIntent);
        }
        if (id == R.id.action_message) {
            Intent MessageIntent = new Intent(this, MessageInbox.class);
            startActivity(MessageIntent);

        }
        if (id == R.id.action_logout) {
            //Source:http://www.coderzheaven.com/2013/02/14/dialogfragments-android-simple-example/
            FragmentManager fm = getFragmentManager();
            MyDialogFragment testDialog = new MyDialogFragment();
            testDialog.setRetainInstance(true);
            testDialog.show(fm,"My_Dialog_Fragment");
        }
        return super.onOptionsItemSelected(item);
    }
    /*Source:https://github.com/aboudalia/Teamivore/blob/master/Teamivore/app/src/main/java/edu/umich/teamivore/OverviewActivity.java*/
    private void initializelist()
    {
        //Demo data
        recordsList.add(createMember("Colin","IOE"));
        recordsList.add(createMember("Mike","SI"));
        recordsList.add(createMember("Kathryn","SI"));
        recordsList.add(createMember("Amber","SI"));
        recordsList.add(createMember("Uday","EE"));
        //Get names and major stored in shared preferences and display them (Except current user)
        SharedPreferences loginsharedpref = getSharedPreferences(LOGIN_PREFS,Activity.MODE_PRIVATE);
        SharedPreferences sessionpref = getSharedPreferences(SESSION_PREFS,Activity.MODE_PRIVATE);
        String user = sessionpref.getString("Login","");
        Map<String, ?> allEntries = loginsharedpref.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (!entry.getKey().toString().equals(user)) {
                recordsList.add(createMember(entry.getKey().toString(), entry.getValue().toString().split(":")[1])); //major
            }
        }



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
    public void openMemberDetail(String list_name) {
        Intent intent = new Intent(this, Profile.class);
        //String message = String.valueOf(id);
        String message = list_name;
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void goToFilter(View view){
        Intent filterIntent = new Intent(this, Filter.class);
        startActivity(filterIntent);
    }

}
