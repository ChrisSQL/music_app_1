package com.area52.techno.djs;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.area52.techno.R;

public class AddItemActivity extends AppCompatActivity {

    EditText todo;
    EditText time;
    Button button;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dj_additem);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"sesh")
                .build();

        todo = (EditText) findViewById(R.id.name);
        time = (EditText) findViewById(R.id.time);
        button = (Button)findViewById(R.id.button);


        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"sesh")
                .build();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!todo.getText().toString().equals("") && !time.getText().toString().equals("")){

                    final DJListItem DJListItem = new DJListItem(todo.getText().toString(),time.getText().toString());
                    //save the item before leaving the activity


                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            db.databaseInterface().insertAll(DJListItem);
                        }
                    });


                    Intent i = new Intent(AddItemActivity.this,DJs.class);
                    startActivity(i);

                    finish();
                }
            }
        });
    }
}
