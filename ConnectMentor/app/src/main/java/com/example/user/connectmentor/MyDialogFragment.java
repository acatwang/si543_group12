package com.example.user.connectmentor;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Kushank on 05-11-2014.
 */
/*Source:http://www.coderzheaven.com/2013/02/14/dialogfragments-android-simple-example/*/
//Source:http://stackoverflow.com/questions/12112061/handle-button-clicks-in-a-dialogfragment

public class MyDialogFragment extends DialogFragment {

    public MyDialogFragment() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment, container);
        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        ((Button) view.findViewById(R.id.yesbtn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserLoginActivity.class);
                getActivity().startActivityForResult(intent, 0);

            }
        });
        ((Button) view.findViewById(R.id.nobtn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getDialog().dismiss();

            }
        });

        return view;

    }
}



