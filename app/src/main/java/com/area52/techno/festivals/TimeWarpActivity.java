package com.area52.techno.festivals;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.area52.techno.R;
import com.area52.techno.activities.PlatformsActivity;
import com.area52.techno.youtube.YouTubeActivityPlaylist;

public class TimeWarpActivity extends AppCompatActivity {

    Intent intentPlatform;
    String platform, festival;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festivals_timewarp_years);

        intentPlatform = getIntent();
        platform = intentPlatform.getStringExtra("platform");
        festival = intentPlatform.getStringExtra("festival");

        final Button button2018 = (Button) findViewById(R.id.n2018Button);
        button2018.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent i=new Intent(TimeWarpActivity.this,YouTubeActivityPlaylist.class);
                i.putExtra("playlist", "PLW_7xvV4xv5jdFXZmYmlbcLuUo_gFKl0C");
                TimeWarpActivity.this.startActivity(i);
            }
        });
        final Button button2017 = (Button) findViewById(R.id.n2017Button);
        button2017.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent i=new Intent(TimeWarpActivity.this,YouTubeActivityPlaylist.class);
                i.putExtra("playlist", "PLW_7xvV4xv5j9cr7ZYgwN0c4ee8uosvYH");
                TimeWarpActivity.this.startActivity(i);
            }
        });
        final Button button2016 = (Button) findViewById(R.id.n2016Button);
        button2016.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent i=new Intent(TimeWarpActivity.this,YouTubeActivityPlaylist.class);
                i.putExtra("playlist", "PLmh4GKNPRUM81GJbBJCDxEcjo_iRDUITm");
                TimeWarpActivity.this.startActivity(i);
            }
        });
        final Button button2015 = (Button) findViewById(R.id.n2015Button);
        button2015.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent i=new Intent(TimeWarpActivity.this,YouTubeActivityPlaylist.class);
                i.putExtra("playlist", "PLmh4GKNPRUM_bfmS6sLcclfZEoHc1xPBW");
                TimeWarpActivity.this.startActivity(i);
            }
        });
        final Button button2014 = (Button) findViewById(R.id.n2014Button);
        button2014.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent i=new Intent(TimeWarpActivity.this,YouTubeActivityPlaylist.class);
                i.putExtra("playlist", "PLmh4GKNPRUM_5owpicvtHBtVvnZElaVX5");
                TimeWarpActivity.this.startActivity(i);
            }
        });
        final Button button2013 = (Button) findViewById(R.id.n2013Button);
        button2013.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent i=new Intent(TimeWarpActivity.this,YouTubeActivityPlaylist.class);
                i.putExtra("playlist", "PLmh4GKNPRUM8eZM30brviQNR7Rn1PV-Cz");
                TimeWarpActivity.this.startActivity(i);
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
        Intent i = new Intent(TimeWarpActivity.this, PlatformsActivity.class);
        i.putExtra("festival", festival);
        TimeWarpActivity.this.startActivity(i);
    }

}
