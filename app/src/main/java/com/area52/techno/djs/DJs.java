package com.area52.techno.djs;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.area52.techno.R;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class DJs extends AppCompatActivity {

    FloatingActionButton fab;
    public static RecyclerView recyclerView;
    public static RecyclerView.Adapter adapter;
    List<DJListItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_djs);

        //whenever the activity is started, it reads data from database and stores it into
        // local array list 'items'
        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"sesh")
                .build();

        //it is very bad practice to pull data from Room on main UI thread,
        // that's why we create another thread which we use for getting the data and displaying it
        Runnable r = new Runnable(){
            @Override
            public void run() {
                items = db.databaseInterface().getAllItems();
                recyclerView= (RecyclerView)findViewById(R.id.recyclerview);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));
                adapter= new UserAdapter(items);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);

            }
        };

        Thread newThread= new Thread(r);
        newThread.start();

        fab=(FloatingActionButton)findViewById(R.id.fab);

//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(DJs.this,AddItemActivity.class);
//                startActivity(i);
//                finish();
//            }
//        });

    }

}
