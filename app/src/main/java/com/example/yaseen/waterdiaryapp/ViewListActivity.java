package com.example.yaseen.waterdiaryapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewListActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    ArrayList<String> theList, nextList;
    ListView listView;
    public static int post;
    boolean statusprev, statusnex = false;
    int newInt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        listView = (ListView) findViewById(R.id.list);
        myDB = new DatabaseHelper(this);

        //populate an ArrayList<String> from the database and then view it
        theList = new ArrayList<>();
        nextList = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2,android.R.id.text1,theList);
        listView.setAdapter(adapter);

        Cursor data = myDB.getListContents();
        /**Add data to arraylist and setup list adapter
         * data is retrieved from arraylist and sent to DiaryEntryActivity to be displayed
         * position of array also sent to Diary activity via intent
         */
        if(data.getCount() == 0){
            Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                theList.add("Date: "+data.getString(1)+"\n"+("Total: ")+data.getString(10));
                nextList.add("Date: " +data.getString(1)+"\n"+"\n"+"Shower: "+data.getString(11)+"\n"+"Toilet: "+ data.getString(2)+ "\n"+"Hygiene: "+data.getString(3)+"\n"+"Laundry: "+data.getString(4)+"\n"+"Dishes: "+data.getString(5)+"\n"+"Drinking: "+data.getString(6)+"\n"+"Cooking: "+data.getString(7)+"\n"+"Cleaning: "+data.getString(8)+"\n"+"Other: "+data.getString(9)+"\n"+"\n"+"Total: "+data.getString(10) + "ltr.");
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(listAdapter);
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String TempList = nextList.get(position).toString();
                Intent intent = new Intent(ViewListActivity.this,DiaryEntryActivity.class);
                intent.putExtra("listviewClickValue",TempList);
                intent.putExtra("pos", position);
                intent.putExtra("len", nextList.size());

                startActivity(intent);

            }
        });

        /**statusprev and statusnex are boolean conditions to update arraylist position and start new intent
         * each controls navigation forward ans back in entries list
         */
        statusprev = getIntent().getBooleanExtra("statusp",false);

        if(statusprev)
        {
            newInt = getIntent().getIntExtra("prevpos",0);
            String TempList = nextList.get(newInt).toString();
            Intent intent = new Intent(ViewListActivity.this,DiaryEntryActivity.class);
            intent.putExtra("listviewClickValue",TempList);
            intent.putExtra("pos", newInt);
            intent.putExtra("len", nextList.size());
            startActivity(intent);


        }

        statusnex = getIntent().getBooleanExtra("statusn",false);

        if(statusnex)
        {
            newInt = getIntent().getIntExtra("nexpos",0);
            String TempList = nextList.get(newInt).toString();
            Intent intent = new Intent(ViewListActivity.this,DiaryEntryActivity.class);
            intent.putExtra("listviewClickValue",TempList);
            intent.putExtra("pos", newInt);
            intent.putExtra("len", nextList.size());
            startActivity(intent);


        }


    }
}
