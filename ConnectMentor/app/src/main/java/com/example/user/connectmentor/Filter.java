// Creadte by Yi-Yin Wang
package com.example.user.connectmentor;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;



public class Filter extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.filter, menu);
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


    //TODO
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



    /** Called when the user clicks the Research button */
    public void doSearchByFilter(){
        //TODO: How to Gather or selected value and send it to Overview?
        // Send the selected criteria to find the corresponding users
        //Intent searchByFilterintent = new Intent(this, OverviewActivity.class);

    }
}
