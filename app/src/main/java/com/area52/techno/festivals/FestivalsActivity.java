package com.area52.techno.festivals;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.area52.techno.R;
import com.area52.techno.activities.MainActivity;
import com.area52.techno.activities.PlatformsActivity;

public class FestivalsActivity extends AppCompatActivity {

    Intent intentPlatform;
    String festival;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festivals);

        intentPlatform = getIntent();
        festival = intentPlatform.getStringExtra("festival");

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
        final Button button2014 = (Button) findViewById(R.id.adeButton);
        button2014.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                goToFestival("ADE");
            }
        });
        final Button button2013 = (Button) findViewById(R.id.lumoButton);
        button2013.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                goToFestival("Lumo");
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
        startActivity(new Intent(FestivalsActivity.this, MainActivity.class));
    }

    public void goToFestival(String festivalIn){

        Intent intentPlatform=new Intent(FestivalsActivity.this,FestivalsActivity2.class);
        intentPlatform.putExtra("festival", festivalIn);
        FestivalsActivity.this.startActivity(intentPlatform);





    }

}