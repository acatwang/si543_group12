// Creadte by Yi-Yin Wang
package com.example.user.connectmentor;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
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
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class Profile extends Activity {
    Button button;
    ImageView image;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        addListnerOnButton();

        //TODO
        addListnerOnBtnTalk();

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        // Get Data of User
        prepareListData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);


        //TODO
        getUserInfo();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
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

    public void addListnerOnBtnTalk(){
        // Send intent to messenger
    }

    private void prepareListData(){
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child(hearder) data
        listDataHeader.add("About me");
        listDataHeader.add("Coursework");
        listDataHeader.add("Skills");

        // Adding child(Content) data
        List<String> aboutme = new ArrayList<String>();
        aboutme.add("Hi I am andy");

        // Adding child(Content) data
        List<String> coursework = new ArrayList<String>();
        coursework.add("Java");
        coursework.add("Data Manipulation");
        coursework.add("InfoViz");

        // Adding child(Content) data
        List<String> skills = new ArrayList<String>();
        skills.add("Android");
        skills.add("programming");
        listDataChild.put(listDataHeader.get(0), aboutme); // Header, Child data
        listDataChild.put(listDataHeader.get(1), coursework);
        listDataChild.put(listDataHeader.get(2), skills);


    }

    public void getUserInfo(){
        // Retrieve specific user info based on Intent
    }
}

