package com.example.hp.health.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.health.Db.Netconnect;
import com.example.hp.health.Db.URL;
import com.example.hp.health.Model.Utility;
import com.example.hp.health.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;


public class TrackCalorie extends Fragment {

    View _view = null;
    private String username;
    private TextView goal;
    private TextView consumed;
    private TextView burned;
    private TextView net;
    private TextView remaining;
    private String burned1;
    private String burned2;
    private Double _consumed;
    private Double _burned;
    private Double _goal;
    private Double _net;
    DecimalFormat df = new DecimalFormat( "0.0 ");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _view = inflater.inflate(R.layout.fragment_track_calorie, container, false);
        username = getActivity().getIntent().getStringExtra("username");
        goal = (TextView)_view.findViewById(R.id.goal);
        consumed = (TextView)_view.findViewById(R.id.consumed);
        burned = (TextView)_view.findViewById(R.id.burned);
        net = (TextView)_view.findViewById(R.id.net);
        remaining = (TextView)_view.findViewById(R.id.remaining);

        String url = URL.URL_GetGoal+ Utility.Today()+"/"+username;
        new getGoal().execute(url);

        String urlStepsBurned = URL.URL_StepsBurned+username+"/"+Utility.Today();
        new getStepsBurned().execute(urlStepsBurned);


        String url2 = URL.URL_Consumed+username+"/"+Utility.Today();

        new getCalorieConsumed().execute(url2);


        return _view;

    }

    protected class getStepsBurned extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... params) {
            return Netconnect.getContent(params[0]);
        }
        protected  void onPostExecute(String s){
            _burned = Double.parseDouble(s);
            burned.setText(df.format(_burned));
        }
    }

    protected class getBMRBurned extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            return null;
        }
    }


    protected class getCalorieConsumed extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            return Netconnect.getContent(params[0]);
        }
        protected  void onPostExecute(String s){
            _consumed = Double.parseDouble(s);
            consumed.setText(s);
            _net= _consumed - _burned;
            net.setText(df.format(_net));
            Double i = _goal - _net;
            remaining.setText("Remaining "+df.format(i));
        }
    }

    protected class getGoal extends AsyncTask<String,Void,String >{

        @Override
        protected String doInBackground(String... params) {
            return Netconnect.getContent(params[0]);
        }

        protected  void onPostExecute(String s){
            try {
//                System.out.println("1234"+s);
                JSONObject result =  new JSONArray(s).getJSONObject(0);
                _goal = Double.parseDouble(result.getString("calorieSetGoal"));
                goal.setText(result.getString("calorieSetGoal"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}