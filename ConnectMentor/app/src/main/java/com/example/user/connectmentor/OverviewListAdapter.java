/**
 * Created by Kushankr on 04-11-2014.
 */
//Source: http://www.androidhive.info/2012/02/android-custom-listview-with-image-and-text/


package com.example.user.connectmentor;
import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class OverviewListAdapter extends BaseAdapter{
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;

    public OverviewListAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_row, null);

        TextView name = (TextView)vi.findViewById(R.id.username);
        TextView major = (TextView)vi.findViewById(R.id.major); // duration

        HashMap<String, String> record = new HashMap<String, String>();
        record = data.get(position);

        // Setting all values in listview
        name.setText(record.get(OverViewActivity.KEY_NAME));
        major.setText(record.get(OverViewActivity.KEY_MAJOR));
        return vi;
    }
}
