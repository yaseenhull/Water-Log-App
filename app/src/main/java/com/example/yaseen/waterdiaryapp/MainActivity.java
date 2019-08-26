package com.example.yaseen.waterdiaryapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnView;
    TextView totalText, avgText;
    int total, count;
    double avg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalText = (TextView)findViewById(R.id.totalText);
        avgText = (TextView)findViewById(R.id.avgText);

        btnAdd = (Button) findViewById(R.id.btnAdd); // Button to launcher calculator activity
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
                startActivity(intent);
            }
        });
        btnView = (Button) findViewById(R.id.btnView); // Button to launch list of entries activity
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewListActivity.class);
                startActivity(intent);
            }
        });


        SharedPreferences sharedPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE); // retrieve total and number of entries
        total = sharedPref.getInt("fi", 0); //total
        count = sharedPref.getInt("ci",0); // number of entries
        avg = (double)total/count;
        String main = String.valueOf(total);
        String second = String.valueOf(avg);

        /** if count is 0 then
         * set total and average
         * to 0
         */
        if(count == 0)
        {
            totalText.setText("0"+" ltr.");
            avgText.setText("0"+" ltr.");
        }
        else
        {
            totalText.setText(main+" ltr.");
            avgText.setText(second+" ltr.");
        }



    }


}
