// Created by Yi-Yin Wang
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
import java.util.Map;

public class Profile extends Activity {

    private LinearLayout talkLayout;
    Button button;
    ImageView image;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    HashMap<String, String> usersList = new HashMap<String, String>();

    String userName;
    String major;
    int userid;
    boolean isSelfUser;

    /* SharedPreference*/
    public final static String EXTRA_MESSAGE = "edu.umich.teamivore.MESSAGE";
    //ArrayList <HashMap<String, String>> recordsList = new ArrayList <HashMap<String, String>>();

    public static final String LOGIN_PREFS = "Login_Prefs" ;
    public static final String SESSION_PREFS = "Session_Prefs" ;
    public static final String PROFILE_PREFS = "Profile_Prefs" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //enable the action bar
        ActionBar actionBar = getActionBar();

        addListnerOnButton();

        initUserList();

        /* Get the message from the intent */
        Intent intent = getIntent();
        userName = intent.getStringExtra(OverViewActivity.EXTRA_MESSAGE);
        try {
            //userName = intent.getStringExtra(OverViewActivity.EXTRA_MESSAGE);
            userid = (int) Long.parseLong(userName);
            isSelfUser = true;
        }catch (RuntimeException e){
            //  Intent from Overview or from EditProfile
            isSelfUser = (userName==null)?true:false;
        }



        /* Generate the text view*/

        TextView textView = (TextView) findViewById(R.id.textView_username);
        TextView textView_major = (TextView) findViewById(R.id.textView_usermajor);
        Button talkButton =  (Button) findViewById(R.id.btnTalk);
        Button changeButton = (Button) findViewById(R.id.btnChangeImage);
        Button editButton = (Button) findViewById(R.id.btnEdit);


        //Get data from SharedPrefernce
        SharedPreferences loginsharedpref = getSharedPreferences(LOGIN_PREFS,Activity.MODE_PRIVATE);
        SharedPreferences sessionpref = getSharedPreferences(SESSION_PREFS,Activity.MODE_PRIVATE);
        String user = (isSelfUser? sessionpref.getString("Login",""):userName);

        try {
            major = loginsharedpref.getString(user, "").split(":")[1]; //parse the shared preference
        }
        catch (ArrayIndexOutOfBoundsException e){
            // Catch the exception for the demo data
            // The demo data are initiated when the activity is called
            // The value stored in a hashmap called usersList

            major = usersList.get(user).toString();

        }

        /* Programatically Set User Name and display button */

        textView.setText(user);
        textView_major.setText(major);

        if (isSelfUser){
            talkButton.setVisibility(View.GONE);
        } else{ // The user is viewing other member's profile
            // Show let's talk button and hide change image button
            changeButton.setVisibility(View.GONE);
            editButton.setVisibility(View.GONE);
        }


        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        // Get Data of User
        prepareListData(user);
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener(){
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //Nothing here ever fires
                System.err.println(groupPosition);
                System.err.println(childPosition);

                //Reference:
                // http://stackoverflow.com/questions/11534435/how-to-get-item-from-child-list-at-expandable-listview
                //http://stackoverflow.com/questions/16888574/expandablelistview-child-click-listener-not-firing

                if(groupPosition==1){ // Course taken
                    String c =parent.getExpandableListAdapter().getChild(groupPosition, childPosition).toString();

                    filterByCourse(v,c);
                }else{
                    Toast.makeText(getApplicationContext(), "Oops, can't filter by this.", Toast.LENGTH_SHORT).show();

                }
                return true;
            }
        });

        //TODO: Connect with database to get user info
        getUserInfo();
    }

    /* Demo Data */
    private void initUserList(){
        usersList.put("Colin","IOE");
        usersList.put("Mike","SI");
        usersList.put("Kathryn","SI");
        usersList.put("Amber","SI");
        usersList.put("Uday","EE");

    };

    /* Set up menu bar at the bottom */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

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

    //Change picture
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

    // Programatically set Expandable list
    private void prepareListData(String user){

        SharedPreferences profilePref = getSharedPreferences(PROFILE_PREFS,Activity.MODE_PRIVATE);
        /*
        SharedPreferences sessionpref = getSharedPreferences(SESSION_PREFS,Activity.MODE_PRIVATE);
        String user = sessionpref.getString("Login","");
        */

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
        //TODO: add from sharepreference
        List<String> coursework = new ArrayList<String>();
        // for now we add mock-up information. The content can be get from DB using function getUserInfo(userid)
        coursework.add("Java");
        coursework.add("Data Manipulation");
        coursework.add("InfoViz");

        // Adding child(Content) data
        List<String> skills = new ArrayList<String>();
        //TODO: add from sharepreference
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

    public void filterByCourse(View view, String course){
        Intent filterIntent = new Intent(this, OverViewActivity.class);
        filterIntent.putExtra("Course",course);
        startActivity(filterIntent);
    }

}



