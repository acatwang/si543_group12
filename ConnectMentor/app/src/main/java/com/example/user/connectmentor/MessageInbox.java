//editied by Erica Chan
package com.example.user.connectmentor;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class MessageInbox extends Activity {

    ListView list;
    String[] userTitles;
    String[] userDescriptions;
    int[] images = {R.drawable.user1,R.drawable.user2,R.drawable.user3,R.drawable.user4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_inbox);

        Resources res=getResources();
        userTitles=res.getStringArray(R.array.titles);
        userDescriptions=res.getStringArray(R.array.descriptions);

        list= (ListView) findViewById(R.id.listView);
        ConnectMentorAdapter adapter=new ConnectMentorAdapter(this,userTitles,images,userDescriptions);
        list.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.message_inbox, menu);
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
    }}

class ConnectMentorAdapter extends ArrayAdapter<String>
{
    Context context;
    int [] images;
    String[] titleArray;
    String [] descriptionArray;
    ConnectMentorAdapter(Context c,String[] titles, int imgs[], String [] desc)
    {
        super(c,R.layout.single_row,R.id.textView,titles);
        this.context=c;
        this.images=imgs;
        this.titleArray=titles;
        this.descriptionArray=desc;

    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(R.layout.single_row,parent, false);

        ImageView myImage= (ImageView) row.findViewById(R.id.imageView);
        TextView myTitle= (TextView) row.findViewById(R.id.textView);
        TextView myDescription= (TextView) row.findViewById(R.id.textView2);

        myImage.setImageResource(images[position]);
        myTitle.setText(titleArray[position]);
        myDescription.setText(descriptionArray[position]);


        return row;
    }
}



//Messages show in a list view when received



//Name of the person sent from
//The first line of the message displayed
