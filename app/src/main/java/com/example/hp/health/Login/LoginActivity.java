package com.example.hp.health.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.os.AsyncTask;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.hp.health.Db.Netconnect;
import com.example.hp.health.Db.URL;
import com.example.hp.health.Model.MD5;
import com.example.hp.health.Model.Utility;
import com.example.hp.health.R;


/**
 * Created by hp on 19/04/2016.
 */
public class LoginActivity extends AppCompatActivity {
    protected  Button loginButton;
    protected  Button signupButton;
    protected  String username;
    protected  String password;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_main);

        TransformationMethod m = new PasswordTransformationMethod();

        ((EditText)findViewById(R.id.password)).setTransformationMethod(m);

        signupButton = (Button)findViewById(R.id.signup);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });

        loginButton = (Button)findViewById(R.id.signin);
        loginButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
            username = ((EditText) findViewById(R.id.username)).getText().toString();
            password = ((EditText) findViewById(R.id.password)).getText().toString();
                password = MD5.crypt(password);
            if (Utility.NotNull(username)&&Utility.NotNull(password)){
                String url = URL.URL_retriveFromDB+username+"/"+password;
                new UserLogin().execute(url);
            }
                else {
                if (!Utility.NotNull(username))
                    Utility.show(LoginActivity.this,"Please fill the username");
                else
                    Utility.show(LoginActivity.this,"Please fill the password");
            }
        }
    });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class  UserLogin extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... urls) {
            String s = Netconnect.getContent(urls[0]);
            return s;
        }

        protected void onPostExecute(String s){
            if (s.equals("1")){
                Utility.show(LoginActivity.this, "Login succeed, Welcome!");
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, MainActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
            else if(s.equals("2")){
                Utility.show(LoginActivity.this, "Username does not exist, please signup first!");
            }
            else {
                Utility.show(LoginActivity.this,"Wrong password!");
            }
        }

    }

}



