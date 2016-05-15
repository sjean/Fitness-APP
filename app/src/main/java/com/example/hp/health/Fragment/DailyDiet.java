package com.example.hp.health.Fragment;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.hp.health.Db.Netconnect;
import com.example.hp.health.Db.URL;
import com.example.hp.health.Model.BingAPI;
import com.example.hp.health.Model.FatSecretAPI;
import com.example.hp.health.Model.Utility;
import com.example.hp.health.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class DailyDiet extends Fragment {

    private View _view;
    private String _category;
    private Spinner category;
    private Spinner foodChosen;
    private ImageView image;
    private ListView fatSecret;
    private Button  addButton;
    private EditText count;
    private String foodname;
    private String username;
    private String _count;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        _view = inflater.inflate(R.layout.fragment_daily_diet, container, false);
        category = (Spinner)_view.findViewById(R.id.category);
        foodChosen = (Spinner)_view.findViewById(R.id.foodspinner);
        fatSecret = (ListView)_view.findViewById(R.id.fatSecret);
        addButton = (Button)_view.findViewById(R.id.Addcounts);
        count = (EditText)_view.findViewById(R.id.foodCount);
        username = getActivity().getIntent().getStringExtra("username");


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utility.NotNull(count.getText().toString())){

                    _count = count.getText().toString();
                    System.out.println(_count+"countcountcountcountcountcountcountcount");
                    String url = URL.URL_AddFoodCount + username +"/" + foodname+"/" +_count;

                    new setCount().execute(url);

                }else {
                    Utility.show(getActivity(),"Please Input The Count!");
                }
            }
        });

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                _category = category.getSelectedItem().toString();

                String url = URL.URL_Category + _category;
                new GetFood().execute(url);
                System.out.println(url);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

             foodChosen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                System.out.println(foodChosen.getSelectedItem().toString());
                foodname = foodChosen.getSelectedItem().toString();
                if (foodname != "Pick food"){
                    new GetPictureUrl().execute(foodname);
                    new getFatSecret().execute(foodname);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return _view;
    }

    private  class setCount extends  AsyncTask<String,Void,String >{

        @Override
        protected String doInBackground(String... params) {
            return Netconnect.getContent(params[0]);
        }
        protected void onPostExecute(String s){
            Utility.show(getActivity(),"Add count successfully!");
        }
    }

    private class GetFood extends AsyncTask<String ,Void,String >{

        @Override
        protected String doInBackground(String... urls) {
            //System.out.println(urls);
            return Netconnect.getContent(urls[0]);
        }

        protected void onPostExecute(String s){
           // System.out.println(s);
            super.onPostExecute(s);

            try {
                JSONArray foodcontainer = new JSONArray(s);
                ArrayList<String > arrayitems = new ArrayList<String>();
                arrayitems.add("Pick food");
                for (int i=0; i<foodcontainer.length(); i++){
                    JSONObject result = foodcontainer.getJSONObject(i);
                    arrayitems.add(result.getString("foodname"));
                }
//                System.out.println(arrayitems.toString());
                ArrayAdapter adapter = new ArrayAdapter(getActivity(),R.layout.support_simple_spinner_dropdown_item,arrayitems);
                foodChosen.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private class GetPictureUrl extends AsyncTask<String, Void,String>{
        @Override
        protected String doInBackground(String... food) {
            return new BingAPI().getPicture(food[0]);
        }

        protected void onPostExecute (String s){
            System.out.println(s);
            image = (ImageView) _view.findViewById(R.id.foodimage);
            try {
                JSONObject jsonObject = new JSONObject(s);
                String imageUrl = jsonObject.getJSONArray("value").getJSONObject(0).getString("thumbnailUrl");
                new getPicture().execute(imageUrl);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private class getPicture extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... url) {
            return Utility.getUrlImage(url[0]);
        }
        protected void onPostExecute(Bitmap s){
            image.setImageBitmap(s);
        }
    }


    private class getFatSecret extends AsyncTask<String, Void, JSONObject> {


        @Override
        protected JSONObject doInBackground(String... url) {

            try {
//                return  Utility.getFatSecret(url[0]);
                FatSecretAPI api = new FatSecretAPI("374069ffebc84e98a200582626cb9790","f6f277a94ee5437c99fc73bfa80492c8");
                JSONObject object = api.getFoodItems(url[0]);
                return object;

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(JSONObject s){
            System.out.println("1234124");
  //          System.out.println(s);
//            JSONObject jsonObject = new JSONObject(s);
            try {

                ArrayList<String>  list = new ArrayList<String >();
                String foodDescription = s.getJSONObject("result").getJSONObject("foods").getJSONArray("food").getJSONObject(0).getString("food_description");
                String foodType = s.getJSONObject("result").getJSONObject("foods").getJSONArray("food").getJSONObject(0).getString("food_type");
                //  System.out.println(foodDescription);
                String[] description = foodDescription.split("\\|");
                for (int i =0;i<4; ++i){
                    list.add(description[i]);
                }
                list.add("FoodType:"+foodType);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,list);
                fatSecret.setAdapter(adapter);
                System.out.println(foodDescription+foodType);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


//    private  class addCounts extends  AsyncTask<String,Void,String>{
//
//        @Override
//        protected String doInBackground(String... params) {
//            return null;
//        }
//    }

}
