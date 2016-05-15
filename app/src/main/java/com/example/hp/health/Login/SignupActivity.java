package com.example.hp.health.Login;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.hp.health.Db.DbHelper;
import com.example.hp.health.Db.LocalUser;
import com.example.hp.health.Db.Netconnect;
import com.example.hp.health.Db.ServerUser;
import com.example.hp.health.Db.URL;
import com.example.hp.health.Model.MD5;
import com.example.hp.health.Model.Utility;
import com.example.hp.health.R;

import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

public class SignupActivity extends AppCompatActivity {

    public ServerUser user;
    protected EditText _username;
    protected EditText _password;
    protected RadioButton _gender1;
    protected RadioButton _gender2;
    protected EditText _age;
    protected EditText _height;
    protected EditText _weight;
    protected EditText _stepsPerMile;
    protected Spinner _level;
    protected Button _confirm;
    protected Button _cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        user = new ServerUser();
        _username = (EditText)findViewById(R.id.username);
        _password = (EditText)findViewById(R.id.password);
        _gender1 = (RadioButton)findViewById(R.id.male);
        _gender2 = (RadioButton)findViewById(R.id.female);
        _age = (EditText)findViewById(R.id.age);
        _height = (EditText)findViewById(R.id.height);
        _weight = (EditText)findViewById(R.id.weight);
        _stepsPerMile = (EditText)findViewById(R.id.stepsPerMile);
        _level = (Spinner)findViewById(R.id.spinner);

        _cancel = (Button)findViewById(R.id.cancel);
        _cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        _confirm = (Button)findViewById(R.id.confirm);
        _confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gender = null;
                String name = _username.getText().toString();

                String age = _age.getText().toString();
                if(_gender1.isChecked()){
                    gender = "male";
                 }
                 else  if (_gender2.isChecked()){
                    gender ="female";
                        }
                 else{
                    gender = null;
                 }
                    String password = _password.getText().toString();
                    password = MD5.crypt(password);
                    String height = _height.getText().toString();
                    String weight = _weight.getText().toString();
                    String stepsPerMile = _stepsPerMile.getText().toString();
                    String level = _level.getSelectedItem().toString();

                    try {
                        if (new Utility().getUser(user, SignupActivity.this, name, password, gender, age, height, weight, stepsPerMile, level)) {
                            String url = URL.URL_Signup + user.getUsername() + "/" + user.getPassword() + "/" + user.getGender()
                                    + "/" + user.getAge() + "/" + user.getHeight() + "/" + user.getWeight() + "/" + user.getStepsPerMile() + "/" + user.getLevel();
                            System.out.println(url);
                            new UserToDB().execute(url);
                            new UserToSQLite().execute(new LocalUser(name, password, Utility.Today()));
                            Utility.show(SignupActivity.this, "Signup succeed, you can login now!");
                            Intent intent = new Intent();
                            intent.setClass(SignupActivity.this, LoginActivity.class);
                            startActivity(intent);

                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
        });
    }



    private class UserToSQLite extends AsyncTask<LocalUser,Void,Void>{

        @Override
        protected Void doInBackground(LocalUser... user) {
            DbHelper dbHelper = new DbHelper(getApplicationContext());
            try {
                dbHelper.addUser(user[0]);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class UserToDB extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... urls) {
            return Netconnect.getContent(urls[0]);
        }

        protected void onPostExecute(String s){
            System.out.println(s);

            if (s.equals("0")){
                Utility.show(SignupActivity.this,"username still exists in Database!");
            }else {
                Utility.show(SignupActivity.this, "ServerDB succeed!");
            }
        }
    }



}
