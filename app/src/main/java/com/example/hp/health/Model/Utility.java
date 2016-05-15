package com.example.hp.health.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.example.hp.health.Db.ServerUser;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by hp on 21/04/2016.
 */
public class Utility {
    public static boolean NotNull(String txt){
        return txt != null && txt.trim().length()>0?true:false;
    }

    public static void show (Context context,String txt){
        Toast.makeText(context,txt, Toast.LENGTH_SHORT).show();
    }

    public boolean getUser(ServerUser user, Context context, String username, String password, String gender,
                           String age, String height,String weight, String stepsPerMile, String level)
            throws ExecutionException, InterruptedException {
        if(NotNull(username)&&NotNull(password)&&NotNull(gender)&&NotNull(age)&&NotNull(height)
                &&NotNull(weight)&&NotNull(stepsPerMile)&&NotNull(level)){
            user.setUsername(username);
            user.setPassword(password);
            user.setGender(gender);
            user.setAge(age);
            user.setHeight(height);
            user.setWeight(weight);
            user.setStepsPerMile(stepsPerMile);
            user.setLevel(level);
            return true;
        }
        else {
            if (!NotNull(username)){
                show(context,"Please input your name");
                return false;
            }
            if (!NotNull(password)){
                show(context,"Please input your password");
                return false;
            }
            if (!NotNull(gender)){
                show(context,"Please choose your gender");
                return false;
            }
            if (!NotNull(age)){
                show(context,"Please input your age");
                return false;
            }
            if (!NotNull(height)){
                show(context,"Please input your height");
                return false;
            }
            if (!NotNull(weight)){
                show(context,"Please input your weight");
                return false;
            }
            if (!NotNull(stepsPerMile)){
                show(context,"Please input your stepsPerMile");
                return false;
            }
            if (!NotNull(level)){
                show(context,"Please input your level of Activity");
                return false;
            }
            return false;
        }
    }

    public static  String Today(){

        long time = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(time);
        String currentTime = format.format(date);
        return currentTime;
    }

    public static Bitmap getUrlImage(String url){
        Bitmap img = null;
        try {
            java.net.URL picture = new java.net.URL(url);
            HttpURLConnection conn = (HttpURLConnection) picture.openConnection();
            conn.setConnectTimeout(6000);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.connect();
            InputStream in = conn.getInputStream();
            img = BitmapFactory.decodeStream(in);
            in.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

}

