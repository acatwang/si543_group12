// Edit by Yi-Yin Wang
package com.example.user.connectmentor;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.ClipData;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Filter extends Activity {

    // Choice
    GridView gridviewYear;
    List<Map<String, String>> yearList = new ArrayList<Map<String,String>>();
    //CustomGridViewAdapter customGridAdapter;

    // Declare Selection
    private String major;
    private String year;
    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        //enable the action bar
        //ActionBar actionBar = getActionBar();

        //TODO: add item to the grid
        initYearGrid();
        GridLayout gridviewYear = (GridLayout) findViewById(R.id.gridview_year);
        //gridview.setAdapter(new ImageAdapter(this));

        // TODO: add listener to each text, to make it clickable
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.filter, menu);
        //return super.onCreateOptionsMenu(menu);
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
                Intent goProfileIntent = new Intent(this, Profile.class);
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


    /* Add data to filter*/
    private void initYearGrid(){
        //yearList.add
    }

    //TODO: set the selected value after click
    /** Save the selected conditions **/
    public void setMajorView(){
        // create Textview for each marjor and set a onClickListener to store the selected value
    }

    public void setYearView(){
        // create Textview for each gender and set a onClickListener to store the selected value
    }

    public void setGenderView(){
        // create Textview for each gender and set a onClickListener to store the selected value
    }

    public String getMajor(){
        return major;
    }

    public String getYear(){
        return year;
    }

    public String getGender(){
        return gender;
    }


    /** Called when the user clicks the Research button */
    public void doSearchByFilter(View view){

        Intent searchByFilterIntent = new Intent(this, OverViewActivity.class);
        // Pass selected value to overview to find the corresponding users
        searchByFilterIntent.putExtra("FilterMajor", getMajor());
        searchByFilterIntent.putExtra("FilterYear", getYear());
        searchByFilterIntent.putExtra("FilterGender", getGender());
        startActivity(searchByFilterIntent);

    }
}
