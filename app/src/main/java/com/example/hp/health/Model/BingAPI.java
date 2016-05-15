package com.example.hp.health.Model;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by hp on 27/04/2016.
 */
public class BingAPI {
    public  String getPicture(String food){
        try {
            String Path = "&count=5&offset=0&mkt=en-us&safeSearch=Moderate";
            URL url = new URL("https://bingapis.azure-api.net/api/v5/images/search"+"?q="+food.replaceAll(" ", "%20")+Path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Ocp-Apim-Subscription-Key","4520ca9949fc41afa23a12e8031c8803");

            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader buffer = new BufferedReader(new InputStreamReader(in));

            StringBuffer sb = new StringBuffer();
            String line = null;
            while ((line = buffer.readLine()) != null){
                sb.append(line);
            }
            conn.disconnect();
//            System.out.println(sb.toString());
            return sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
//    public String JsonResolve(String s){
//
//    }
//
}
