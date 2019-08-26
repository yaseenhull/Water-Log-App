package com.example.yaseen.waterdiaryapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DiaryEntryActivity extends AppCompatActivity {

    TextView dateView, totalView;
    Button btnNext, btnPrev, btnCal;
    int position, TempPos, Templen;
    String temp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_layout);

        dateView = (TextView) findViewById(R.id.dateView);
        totalView = (TextView) findViewById(R.id.totalView);
        String TempHolder = getIntent().getStringExtra("listviewClickValue");
        TempPos = getIntent().getIntExtra("pos",0);
        Templen = getIntent().getIntExtra("len",0);

        dateView.setText(TempHolder);
        totalView.setText(String.valueOf(TempPos));

        btnNext = (Button)findViewById(R.id.btnNext);
        btnPrev = (Button)findViewById(R.id.btnPrev);
        btnCal = (Button)findViewById(R.id.btnCal);
        /**
         * access to previous entry
         */
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TempPos == 0)
                {
                    Intent intent = new Intent(DiaryEntryActivity.this, ViewListActivity.class);
                    intent.putExtra("prevpos",TempPos );
                    intent.putExtra("statusp",true);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(DiaryEntryActivity.this, ViewListActivity.class);
                    intent.putExtra("prevpos", TempPos-1);
                    intent.putExtra("statusp",true);
                    startActivity(intent);
                }
            }
        });
        /**
         * access to next entry
         */

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TempPos == Templen-1)
                {
                    Intent intent = new Intent(DiaryEntryActivity.this, ViewListActivity.class);
                    intent.putExtra("nexpos",TempPos );
                    intent.putExtra("statusn",true);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(DiaryEntryActivity.this, ViewListActivity.class);
                    intent.putExtra("nexpos",TempPos +1);
                    intent.putExtra("statusn",true);
                    startActivity(intent);
                }

            }
        });
        /**
         * access to calculator activity
         */

        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiaryEntryActivity.this, CalculatorActivity.class);
                startActivity(intent);
            }
        });



    }
}
