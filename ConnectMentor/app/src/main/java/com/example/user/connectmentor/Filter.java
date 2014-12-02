// Built by Yi-Yin Wang
package com.example.user.connectmentor;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.ClipData;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Filter extends Activity {

    List<Map<String, String>> yearList = new ArrayList<Map<String,String>>();
    GridLayout gridGender;
    GridLayout majorGrid;
    // Declare Selection
    private String [] majors;
    private String[] genders = new String[]{"Male", "Female"};
    String intentMsg="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        gridGender = (GridLayout)findViewById(R.id.gridview_gender);
        majorGrid = (GridLayout)findViewById(R.id.gridview_major);

        //enable the action bar
        //ActionBar actionBar = getActionBar();
        majors = getMajors();
        //add item to the grid
        initGenderGrid();
        initMajorGrid();


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


    /* Add data*/
    private void initGenderGrid(){
        for (int i =0; i<genders.length; i++){
            final TextView tv = new TextView(this);
            tv.setText(genders[i]);
            tv.setBackgroundColor(Color.WHITE);
            tv.setTextSize(getResources().getDimension(R.dimen.fillter_textsize));
            //tv.setTextSize(15);
            tv.setPadding(2, 2, 2, 2);

            //add listener to each text, to make it clickable
            tv.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // do something here.

                    intentMsg+= tv.getText().toString()+";";
                    tv.setBackgroundColor(Color.YELLOW);

                    //doSearchByFilter(v,intentMsg);

                }
            });

            //set up foramt
            //ref: http://stackoverflow.com/questions/13532084/set-rowspan-or-colspan-of-a-child-of-a-gridlayout-programmatically
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.setMargins(10,10,10,10);
            params.setGravity(Gravity.CENTER);

            params.columnSpec = GridLayout.spec(i);
            params.rowSpec = GridLayout.spec(0);
            tv.setLayoutParams(params);

            gridGender.addView(tv);

        }
    }

    private void initMajorGrid(){

        for (int i =0; i<majors.length;i++){

            final TextView tv = new TextView(this);
            tv.setText(majors[i]);
            tv.setBackgroundColor(Color.WHITE);
            tv.setTextSize(getResources().getDimension(R.dimen.fillter_textsize));
            tv.setPadding(2, 2, 2, 2);

            //add listener to each text, to make it clickable
            tv.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // do something here.
                    intentMsg+= tv.getText().toString()+";";
                    tv.setBackgroundColor(Color.YELLOW);

                    //doSearchByFilter(v,intentMsg);

                }
            });


            //TODO:set column number
            int rowNum= ((majors.length<=5)?1:((majors.length/5) +1));
            int colNum= ((majors.length<=5)?majors.length:5);
            /*
            if (majors.length <=5){
                rowNum = 1;
                colNum = majors.length;
            }else if (majors.length>5){
                rowNum = (majors.length/5) +1;
                colNum =5;
            }
            */

            majorGrid.setRowCount(rowNum);
            majorGrid.setColumnCount(colNum);


            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.setMargins(10,10,10,10);
            params.setGravity(Gravity.CENTER);
            //TODO: if multiple rows
            params.columnSpec = GridLayout.spec(i);
            params.rowSpec = GridLayout.spec(0);
            tv.setLayoutParams(params);

            majorGrid.addView(tv);

        }

    }

    /** Save the selected conditions **/
    public void setMajorView(){
        // create Textview for each marjor and set a onClickListener to store the selected value
    }


    public void setGenderView(){
        // create Textview for each gender and set a onClickListener to store the selected value
    }

    public String [] getMajors(){
        //TODO: get major list from shared preference
        return new String [] {"CS","SI","IOE","EE"};
    }




    /** Called when the user clicks the Research button */
    public void doSearchByFilter(View view){

        Intent searchByFilterIntent = new Intent(this, OverViewActivity.class);
        // Pass selected value to overview to find the corresponding users
        //searchByFilterIntent.putExtra("FilterMsg", msg);
        searchByFilterIntent.putExtra("FilterMsg", intentMsg);
        startActivity(searchByFilterIntent);

    }
}
