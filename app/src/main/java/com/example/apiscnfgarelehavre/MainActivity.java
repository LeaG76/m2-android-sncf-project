package com.example.apiscnfgarelehavre;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;

import com.google.android.material.tabs.TabLayout;
import org.json.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.net.*;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static ArrayList<String>train_arrival_Direction,train_arrival_Type,train_arrival_Number, train_arrival_Date;
    public static ArrayList<String>train_departure_Direction, train_departure_Type, train_departure_Number, train_departure_Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        train_arrival_Direction=new ArrayList<>();
        train_arrival_Type=new ArrayList<>();
        train_arrival_Number=new ArrayList<>();
        train_arrival_Date=new ArrayList<>();
        train_departure_Number=new ArrayList<>();
        train_departure_Type=new ArrayList<>();
        train_departure_Direction=new ArrayList<>();
        train_departure_Date=new ArrayList<>();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            JSONArray arrivals=stringToJsonObject(retrieveArrivals()).getJSONArray("arrivals");
            JSONArray departures= stringToJsonObject(retrieveDepartures()).getJSONArray("departures");
            for (int i=0;i<arrivals.length();i++){
                JSONObject train=arrivals.getJSONObject(i).getJSONObject("display_informations");
                train_arrival_Direction.add(train.getString("direction"));
                train_arrival_Type.add(train.getString("physical_mode"));
                train_arrival_Number.add(train.getString("headsign"));
                System.out.println("Direction :"+train.getString("direction"));
                System.out.println("Type de train :"+train.getString("physical_mode"));
                System.out.println("Numéro du train :"+train.getString("headsign"));

                JSONObject arrival_time=arrivals.getJSONObject(i).getJSONObject("stop_date_time");
                String arrival_time_input = arrival_time.getString("arrival_date_time");
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
                SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM\nHH:mm");
                try {
                    Date date = inputFormat.parse(arrival_time_input);
                    train_arrival_Date.add(outputFormat.format(date));
                    System.out.println(outputFormat.format(date));

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i <departures.length() ; i++) {

                JSONObject train=departures.getJSONObject(i).getJSONObject("display_informations");
                train_departure_Type.add(train.getString("physical_mode"));
                train_departure_Number.add(train.getString("headsign"));
                train_departure_Direction.add(train.getString("direction"));
                System.out.println("Direction :"+train.getString("direction"));
                System.out.println("Type de train :"+train.getString("physical_mode"));
                System.out.println("Numéro du train :"+train.getString("headsign"));

                JSONObject departure_time=departures.getJSONObject(i).getJSONObject("stop_date_time");
                String departure_time_input = departure_time.getString("departure_date_time");
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
                SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM\nHH:mm");
                try {
                    Date date = inputFormat.parse(departure_time_input);
                    train_departure_Date.add(outputFormat.format(date));
                    System.out.println(outputFormat.format(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        setTitle("API SNCF | Gare Le Havre");

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.setupWithViewPager(viewPager);

        TabLayoutAdapter tabLayoutAdapter = new TabLayoutAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        tabLayoutAdapter.addFragment(new FragmentDepartures(), "Départs");
        tabLayoutAdapter.addFragment(new FragmentArrivals(), "Arrivées");
        viewPager.setAdapter(tabLayoutAdapter);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_logout_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_login_24);
    }
    public static JSONObject stringToJsonObject(String str) {
        JSONObject json = new JSONObject();
        try {
            json = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
    public String generateTime() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return LocalDateTime.now().toString().replace(":", "").replace("-", "").split("\\.")[0];
        }
        return "";
    }
    public String retrieveDepartures(){
        ArrayList<String> lines = new ArrayList<String>();
        String url_str = "https://api.sncf.com/v1/coverage/sncf/stop_areas/stop_area:SNCF:87413013/departures?datetime="+generateTime();
        // construct the url with the current date and time in the format
        // YYYYMMDDTHHMMSS
        System.out.println(url_str);
        String final_str="";
        // open the connection
        try {
            URL url = new URL(url_str);
            URLConnection urlConnection = url.openConnection();
            // set the authorization header
            urlConnection.setRequestProperty("Authorization", "c8d4cbb2-f09e-4c03-ab25-2d58894ff28c");

            // get the response
            InputStream res = urlConnection.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(res));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                // break the response into lines
                // System.out.println(inputLine);
                lines.add(inputLine);
            }
            for (String string : lines) {
                final_str+=string;
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Whoopsie");
        }
        return final_str;
    }
    public String retrieveArrivals(){
        ArrayList<String> lines = new ArrayList<String>();
        String url_str = "https://api.sncf.com/v1/coverage/sncf/stop_areas/stop_area:SNCF:87413013/arrivals?datetime="+generateTime();
        // construct the url with the current date and time in the format
        // YYYYMMDDTHHMMSS
        System.out.println(url_str);
        String final_str="";
        // open the connection
        try {
            URL url = new URL(url_str);
            URLConnection urlConnection = url.openConnection();
            // set the authorization header
            urlConnection.setRequestProperty("Authorization", "c8d4cbb2-f09e-4c03-ab25-2d58894ff28c");

            // get the response
            InputStream res = urlConnection.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(res));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                // break the response into lines
                // System.out.println(inputLine);
                lines.add(inputLine);
            }
            for (String string : lines) {
                final_str+=string;
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Whoopsie");
        }
            return final_str;
    }
}