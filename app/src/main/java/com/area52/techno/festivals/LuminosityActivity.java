package com.area52.techno.festivals;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.area52.techno.R;
import com.area52.techno.activities.FestivalsActivity;
import com.area52.techno.activities.PlatformsActivity;
import com.area52.techno.youtube.YouTubeActivityPlaylist;

public class LuminosityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festivals_lumo_years);

        final Button button2018 = (Button) findViewById(R.id.n2018Button);
        button2018.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent i=new Intent(LuminosityActivity.this,YouTubeActivityPlaylist.class);
                i.putExtra("playlist", "PLqdiEcHh28muXNP7BGxqTYGW01RYMKzH7");
                LuminosityActivity.this.startActivity(i);
            }
        });
        final Button button2017 = (Button) findViewById(R.id.n2017Button);
        button2017.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent i=new Intent(LuminosityActivity.this,YouTubeActivityPlaylist.class);
                i.putExtra("playlist", "PLmh4GKNPRUM-XYmZRNtsHszE5c_hcdJYl");
                LuminosityActivity.this.startActivity(i);
            }
        });
        final Button button2016 = (Button) findViewById(R.id.n2016Button);
        button2016.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent i=new Intent(LuminosityActivity.this,YouTubeActivityPlaylist.class);
                i.putExtra("playlist", "PLmh4GKNPRUM81GJbBJCDxEcjo_iRDUITm");
                LuminosityActivity.this.startActivity(i);
            }
        });
        final Button button2015 = (Button) findViewById(R.id.n2015Button);
        button2015.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent i=new Intent(LuminosityActivity.this,YouTubeActivityPlaylist.class);
                i.putExtra("playlist", "PLmh4GKNPRUM9ASj3IakXfrSvXsF79oL4T");
                LuminosityActivity.this.startActivity(i);
            }
        });
        final Button button2014 = (Button) findViewById(R.id.n2014Button);
        button2014.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent i=new Intent(LuminosityActivity.this,YouTubeActivityPlaylist.class);
                i.putExtra("playlist", "PLmh4GKNPRUM-9iav7B4uzFMNT09BpHk-f");
                LuminosityActivity.this.startActivity(i);
            }
        });
        final Button button2013 = (Button) findViewById(R.id.n2013Button);
        button2013.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent i=new Intent(LuminosityActivity.this,YouTubeActivityPlaylist.class);
                i.putExtra("playlist", "PLmh4GKNPRUM85mYf1Q8oAUJdOWeZG2-8h");
                LuminosityActivity.this.startActivity(i);
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
        Intent i = new Intent(LuminosityActivity.this, PlatformsActivity.class);
        i.putExtra("festival", "Luminosity");
        LuminosityActivity.this.startActivity(i);
    }

}
