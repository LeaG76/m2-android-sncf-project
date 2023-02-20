package com.example.apiscnfgarelehavre;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FragmentDepartures extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Current view
        View view = inflater.inflate(R.layout.fragment_departures, container,false);
        super.onViewCreated(view, savedInstanceState);

        Date currentDate = Calendar.getInstance().getTime();

        // Current date
        SimpleDateFormat dfDate = new SimpleDateFormat("dd MMM yyyy", Locale.FRANCE);
        String formattedDate = dfDate.format(currentDate);
        TextView textViewDate = view.findViewById(R.id.text_date_departures);
        textViewDate.setText("On est le " + formattedDate);

        // Current hour
        SimpleDateFormat dfHour = new SimpleDateFormat("HH:mm", Locale.FRANCE);
        String formattedHour= dfHour.format(currentDate);
        TextView textViewHour= view.findViewById(R.id.text_hour_departures);
        textViewHour.setText("Il est " + formattedHour);

        ArrayList<String> train_date_list = MainActivity.train_departure_Date;
        ArrayList<String> train_direction_list = MainActivity.train_departure_Direction;
        ArrayList<String> train_type_list = MainActivity.train_departure_Type;
        ArrayList<String> train_number_list = MainActivity.train_departure_Number;

        int[] hour_ids = new int[] {R.id.text_hour_1, R.id.text_hour_2, R.id.text_hour_3, R.id.text_hour_4, R.id.text_hour_5, R.id.text_hour_6, R.id.text_hour_7, R.id.text_hour_8, R.id.text_hour_9, R.id.text_hour_10};

        int[] direction_ids = new int[] {R.id.text_route_1, R.id.text_route_2, R.id.text_route_3, R.id.text_route_4, R.id.text_route_5, R.id.text_route_6, R.id.text_route_7, R.id.text_route_8, R.id.text_route_9, R.id.text_route_10};

        int[] detail_ids = new int[] {R.id.text_detail_1, R.id.text_detail_2, R.id.text_detail_3, R.id.text_detail_4, R.id.text_detail_5, R.id.text_detail_6, R.id.text_detail_7, R.id.text_detail_8, R.id.text_detail_9, R.id.text_detail_10};

        for(int i=0; i<10; i++) {
            TextView train_hour = view.findViewById(hour_ids[i]);
            train_hour.setText(String.valueOf(train_date_list.get(i)));

            TextView train_direction = view.findViewById(direction_ids[i]);
            train_direction.setText(String.valueOf(train_direction_list.get(i)));

            TextView train_detail = view.findViewById(detail_ids[i]);
            train_detail.setText(train_type_list.get(i) + " [" + train_number_list.get(i) + "]");
        }

        return view;
    }
}