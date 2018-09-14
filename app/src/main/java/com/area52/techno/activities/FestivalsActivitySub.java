package com.area52.techno.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.area52.techno.R;

public class FestivalsActivitySub extends AppCompatActivity {

    Intent intent;
    String festival;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intent = getIntent();
        festival = intent.getStringExtra("festival");

        if(festival == "Awakenings"){
            setContentView(R.layout.activity_festivals_awakenings_years);
        }else if(festival == "TimeWarp"){
            //setContentView(R.layout.activity_festivals_timewarp_years);
        }else if(festival == "Futur"){
            //setContentView(R.layout.activity_futur_years);
        }else if(festival == "PrintWorks"){
            //setContentView(R.layout.activity_printworks_years);
        }

        final Button button2018 = (Button) findViewById(R.id.AwakeningsButton);
        button2018.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                goToFestival("Awakenings");
            }
        });
        final Button button2017 = (Button) findViewById(R.id.TimeWarpButton);
        button2017.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                goToFestival("TimeWarp");
            }
        });
        final Button button2016 = (Button) findViewById(R.id.FuturButton);
        button2016.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                goToFestival("Futur");
            }
        });
        final Button button2015 = (Button) findViewById(R.id.PrintWorksButton);
        button2015.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                goToFestival("Printworks");
            }
        });
        final Button button2014 = (Button) findViewById(R.id.n2014Button);
        button2014.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                //goToFestival(2014);
            }
        });
        final Button button2013 = (Button) findViewById(R.id.n2013Button);
        button2013.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                //goToFestival(2013);
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(FestivalsActivitySub.this, MainActivity.class));
    }

    public void goToFestival(String festivalIn){



        String festival = festivalIn;

        if(festival == "Awakenings"){
            Intent i=new Intent(FestivalsActivitySub.this,Awakenings2018.class);
            FestivalsActivitySub.this.startActivity(i);

        }else if(festival == "TimeWarp"){


        }else if(festival == "Futur"){


        }else if(festival == "PrintWorks"){

        }



    }

}
