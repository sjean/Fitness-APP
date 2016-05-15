package com.example.hp.health.Db;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by hp on 18/04/2016.
 */
public class Netconnect {
    public static String getContent(String urls){
        try {
            URL url = new URL(urls);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept","application/json");
            InputStream in = new BufferedInputStream(conn.getInputStream());

            BufferedReader buffer = new BufferedReader(new InputStreamReader(in));

            StringBuffer bb = new StringBuffer();
            String line = null;

            while ((line = buffer.readLine()) != null){
                bb.append(line);
            }
            conn.disconnect();
            return bb.toString();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
