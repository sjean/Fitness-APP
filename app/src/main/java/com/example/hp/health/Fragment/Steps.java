package com.example.hp.health.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.hp.health.Db.DbHelper;
import com.example.hp.health.Db.LocalSteps;
import com.example.hp.health.Db.Netconnect;
import com.example.hp.health.Db.URL;
import com.example.hp.health.Model.Utility;
import com.example.hp.health.R;

public class Steps extends Fragment {

    View _view;
    EditText stepsEnter;
    Button addSteps;
    Button endSteps;
    protected String username;
    private int i =1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _view = inflater.inflate(R.layout.fragment_steps, container, false);
        stepsEnter = (EditText)_view.findViewById(R.id.stepsEnter);
        addSteps = (Button)_view.findViewById(R.id.addsteps);
        endSteps = (Button)_view.findViewById(R.id.endsteps);
        username = getActivity().getIntent().getStringExtra("username");

        addSteps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String InputStep = stepsEnter.getText().toString();
                if (Utility.NotNull(InputStep)){

                    new UpdateSteps().execute(username,InputStep);

                    Utility.show(getActivity(),"Add Steps Successfully!");


                }else {
                    Utility.show(getActivity(),"Please input steps you taken.");
                }
            }
        });


        if (i!=0){
        endSteps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper dbHelper = new DbHelper(getActivity());
                long steps = dbHelper.getTotalLocalSteps(username);
                String url = URL.URL_TotalSteps+username+"/"+steps+"/"+Utility.Today();
                new stepsToDB().execute(url);
                Utility.show(getActivity(),"Succeed to Add Steps to Database!");
                --i;
            }
        });}else {
            Utility.show(getActivity(),"Sorry, you have add your total steps once.");
        }


        return _view;
    }

    private class UpdateSteps extends AsyncTask<String ,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            DbHelper dbHelper = new DbHelper(getActivity());
            int userid = dbHelper.getuserID(params[0]);
            dbHelper.addSteps(new LocalSteps(Utility.Today(),Long.parseLong(params[1]),userid));
            return null;
        }

    }

    private class stepsToDB extends AsyncTask<String,Void,Void>{

        @Override
        protected Void doInBackground(String... params) {
            Netconnect.getContent(params[0]);
            return null;
        }
    }

}
