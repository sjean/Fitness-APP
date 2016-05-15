package com.example.hp.health.Fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hp.health.Db.Netconnect;
import com.example.hp.health.Db.URL;
import com.example.hp.health.Model.Utility;
import com.example.hp.health.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CalorieGoal extends Fragment {
    private View _view;
    private String username;
    private String goal = null;
    private TextView goalText;
    private EditText newGoal;
    private Button confirm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _view = inflater.inflate(R.layout.fragment_calorie_goal, container, false);

        username = getActivity().getIntent().getStringExtra("username");
        goalText = (TextView)_view.findViewById(R.id.goalText);
        newGoal = (EditText)_view.findViewById(R.id.newGoal);
        confirm = (Button)_view.findViewById(R.id.confirm);

        confirm = (Button)_view.findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utility.NotNull(newGoal.getText().toString())){
                String url = URL.URL_UpdateCalorie+username+"/"+newGoal.getText().toString()+"/"+Utility.Today();
                new BackToDB().execute(url);
                Utility.show(getActivity(),"Succeed to change your Calorie Goal!");}
                else {
                    Utility.show(getActivity(),"Please Input the Goal.");
                }
            }
        });

        String url = URL.URL_GetGoal+ Utility.Today()+"/"+username;
        System.out.println(url);
        new getGoal().execute(url);
        return _view;
    }

    public class getGoal extends AsyncTask<String ,Void,String >{

        @Override
        protected String doInBackground(String... url) {
            return Netconnect.getContent(url[0]);
        }
        protected void onPostExecute(String s){
         //   System.out.print(s);

            if (s.equals("[]")) {
                String url =URL.URL_AddReport+username+"/"+"0"+"/"+Utility.Today();
                new BackToDB().execute(url);

            }
            else {
                try {
                    JSONObject result = new JSONArray(s).getJSONObject(0);

                    goal = result.getString("calorieSetGoal");
//                    TrackCalorie trackCalorie = new TrackCalorie();
//                    Bundle bundle =new Bundle();
//                    trackCalorie.setArguments(bundle);

                    if (goal.equals("0.0")||goal.equals(null)) {
                        goalText.setText("Sorry, you have not set your Goal!");
                    } else {
                        goalText.setText("Your Goal is:" + goal);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class BackToDB extends AsyncTask<String,Void,String >{

        @Override
        protected String doInBackground(String... url) {
            return Netconnect.getContent(url[0]);
        }
        protected void onPostExecute(String s){

            super.onPostExecute(s);

        }
    }

}