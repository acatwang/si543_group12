/*Kushank Raghav*/
package com.example.user.connectmentor;

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
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;


public class OverViewActivity extends Activity {

    List <Map<String, String>> memberlist = new ArrayList<Map<String,String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_view);
        /*Source:https://github.com/aboudalia/Teamivore/blob/master/Teamivore/app/src/main/java/edu/umich/teamivore/OverviewActivity.java*/
        ListView memberListView = (ListView) findViewById(R.id.listview1);
        initializelist();
        SimpleAdapter simpleAdpt = new SimpleAdapter(this, memberlist, android.R.layout.simple_list_item_1, new String[] {"member"}, new int[] {android.R.id.text1});
        memberListView.setAdapter(simpleAdpt);
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /*Source:https://github.com/aboudalia/Teamivore/blob/master/Teamivore/app/src/main/java/edu/umich/teamivore/OverviewActivity.java*/
    private void initializelist()
    {
        //Demo data
        //This function will retrieve records from database
        memberlist.add(createMember("member", "Member 1"));
        memberlist.add(createMember("member", "Member 2"));
        memberlist.add(createMember("member", "Member 3"));

    }
    /*Source:https://github.com/aboudalia/Teamivore/blob/master/Teamivore/app/src/main/java/edu/umich/teamivore/OverviewActivity.java*/
    private HashMap<String, String> createMember(String key, String name) {
        HashMap<String, String> team = new HashMap<String, String>();
        team.put(key, name);
        return team;
    }

    public void goToFilter(View view){
        Intent filterIntent = new Intent(this, Filter.class);
        startActivity(filterIntent);
    }

}
