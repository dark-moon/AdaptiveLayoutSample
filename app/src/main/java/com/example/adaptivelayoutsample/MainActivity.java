package com.example.adaptivelayoutsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main Activity";
    private ListView listServices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] services = {"Web Applications",
                "Mobile Applications",
                "Machine Learning",
                "Translation"
        };

        listServices = findViewById(R.id.list_services);

        ListAdapter listAdapter = new ArrayAdapter(this,  android.R.layout.simple_list_item_1, services);
        listServices.setAdapter(listAdapter);

        listServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.i(TAG, "onItemClick: " + view.toString() + ", Position: "  + position
//                + ", id: " + id);
                Log.i(TAG, "onItemClick: " + ((TextView) view).getText());
            }
        });
    }
}