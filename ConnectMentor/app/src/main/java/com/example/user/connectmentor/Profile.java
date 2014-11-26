// Edit by Yi-Yin Wang
package com.example.user.connectmentor;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Profile extends Activity {

    private LinearLayout talkLayout;
    Button button;
    ImageView image;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    ArrayList <HashMap<String, String>> usersList = new ArrayList <HashMap<String, String>>();
    String usermsg;
    int userid;

    /* SharedPreference*/
    public final static String EXTRA_MESSAGE = "edu.umich.teamivore.MESSAGE";
    ArrayList <HashMap<String, String>> recordsList = new ArrayList <HashMap<String, String>>();
    static final String KEY_NAME = "name";
    static final String KEY_MAJOR = "major";
    public static final String LOGIN_PREFS = "Login_Prefs" ;
    public static final String SESSION_PREFS = "Session_Prefs" ;
    public static final String PROFILE_PREFS = "Profile_Prefs" ;

    /* User Profile Data */
    String aboutmeMst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //enable the action bar
        ActionBar actionBar = getActionBar();

        addListnerOnButton();

        // Get the message from the intent

        initUserList();

        Intent intent = getIntent();
        try {
            usermsg = intent.getStringExtra(OverViewActivity.EXTRA_MESSAGE);
            userid = (int) Long.parseLong(usermsg);
        }catch (RuntimeException e){
            userid = 9999;
        }


        /* Create the text view*/

        //show ID for debug use
        TextView textViewID = (TextView) findViewById(R.id.textView_userid);
        textViewID.setText("User ID: "+ userid);

        // TODO: rogramatically Set User Name
        TextView textView = (TextView) findViewById(R.id.textView_username);
        Button talkButton =  (Button) findViewById(R.id.btnTalk);
        Button changeButton = (Button) findViewById(R.id.btnChangeImage);
        if (userid ==9999){ // Current User
            //Get username from SharedPrefernce
            SharedPreferences loginsharedpref = getSharedPreferences(LOGIN_PREFS,Activity.MODE_PRIVATE);
            SharedPreferences sessionpref = getSharedPreferences(SESSION_PREFS,Activity.MODE_PRIVATE);
            String user = sessionpref.getString("Login","");

            //Show content
            textView.setText(user);
            talkButton.setVisibility(View.GONE);


        } else{ // The user is viewing other member's profile
            //TODO get username from overviewintent
            textView.setText("Otheruser");
            //textView.setText(recordsList.get(userid).getName());

            // Show let's talk button and hide change image button
            changeButton.setVisibility(View.GONE);

        }


        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        // Get Data of User
        prepareListData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);


        //TODO: Connect with database to get user info
        getUserInfo();
    }

    private void initUserList(){


        usersList.add(createMember("Andy", "IOE"));
        usersList.add(createMember("Katharina", "SI"));
        usersList.add(createMember("Kurt", "CS"));
        usersList.add(createMember("Kush", "SI"));
        usersList.add(createMember("Alison", "SI"));
        usersList.add(createMember("Erica", "SI"));
    };

    private HashMap<String, String> createMember(String key, String name) {
        HashMap<String, String> team = new HashMap<String, String>();
        team.put(KEY_NAME, key);
        team.put(KEY_MAJOR, name);
        return team;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    /* Set up menu bar at the bottom */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id){
            case R.id.action_overview:
                Intent goOvreviewIntent = new Intent(this, OverViewActivity.class);
                startActivity(goOvreviewIntent);
                break;
            case  R.id.action_profile:
                // TODO Get current user name

                Intent goProfileIntent = new Intent(this, Profile.class);
                String message = String.valueOf("9999");
                goProfileIntent.putExtra(EXTRA_MESSAGE, message);
                startActivity(goProfileIntent);
                break;
            case R.id.action_message:
                Intent goMessageIntent = new Intent(this, MessageInbox.class);

                startActivity(goMessageIntent);
                break;
            case R.id.action_settings:
                Intent goSettingIntent = new Intent(this, Settings.class);
                startActivity(goSettingIntent);
                break;
            case R.id.action_logout:
                //Source:http://www.coderzheaven.com/2013/02/14/dialogfragments-android-simple-example/
                FragmentManager fm = getFragmentManager();
                MyDialogFragment testDialog = new MyDialogFragment();
                testDialog.setRetainInstance(true);
                testDialog.show(fm, "My_Dialog_Fragment");
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addListnerOnButton(){
        image = (ImageView) findViewById(R.id.imageView1);
        button = (Button) findViewById(R.id.btnChangeImage);
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                image.setImageResource(R.drawable.profile_icon2);
            }

        });
    }

    // Programatically set data
    private void prepareListData(){
        SharedPreferences profilePref = getSharedPreferences(PROFILE_PREFS,Activity.MODE_PRIVATE);
        SharedPreferences sessionpref = getSharedPreferences(SESSION_PREFS,Activity.MODE_PRIVATE);
        String user = sessionpref.getString("Login","");

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        /* Set List Data Header */
        listDataHeader.add("About me");
        listDataHeader.add("Coursework");
        listDataHeader.add("Skills");


        // Adding child(Content) data
        List<String> aboutme = new ArrayList<String>();
        // for now we add mock-up information. The content can be get from DB using function getUserInfo(userid)
        //aboutme.add("Hi I am Katharina");

        //Get About Me from sharedprefernce
        String aboutmemsg = ((profilePref.getString(user,"")=="")? "Hi, I am "+user:profilePref.getString(user,"")); //set default message
        //aboutme.add(profilePref.getString(user,""));
        aboutme.add(aboutmemsg);

        /* Add Coursework*/
        List<String> coursework = new ArrayList<String>();
        // for now we add mock-up information. The content can be get from DB using function getUserInfo(userid)
        coursework.add("Java");
        coursework.add("Data Manipulation");
        coursework.add("InfoViz");

        // Adding child(Content) data
        List<String> skills = new ArrayList<String>();
        // for now we add mock-up information. The content can be get from DB using function getUserInfo(userid)
        skills.add("Android");
        skills.add("programming");


        listDataChild.put(listDataHeader.get(0), aboutme); // Header, Child data
        listDataChild.put(listDataHeader.get(1), coursework);
        listDataChild.put(listDataHeader.get(2), skills);


    }

    public void getUserInfo(){
        // Retrieve specific user info based on Intent
    }


    /* Intents*/
    public void startConversation(View view){
        Intent talkIntent = new Intent(this, MessageInbox.class);
        startActivity(talkIntent);
    }

    public void editProfile(View view){
        Intent editIntent = new Intent(this, EditProfile.class);
        SharedPreferences loginsharedpref = getSharedPreferences(LOGIN_PREFS,Activity.MODE_PRIVATE);
        SharedPreferences sessionpref = getSharedPreferences(SESSION_PREFS,Activity.MODE_PRIVATE);
        String user = sessionpref.getString("Login","");

        editIntent.putExtra("name",user);

        startActivity(editIntent);
    }
}

