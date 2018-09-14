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
import com.area52.techno.activities.MainActivity;
import com.area52.techno.activities.PlatformsActivity;
import com.area52.techno.soundcloud.MixCloudActivity;
import com.area52.techno.soundcloud.SoundCloudActivity;
import com.area52.techno.youtube.YouTubeActivityPlaylist;

public class AwakeningsActivity extends AppCompatActivity {

    Intent intentPlatform;
    String platform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festivals_awakenings_years);

        intentPlatform = getIntent();
        platform = intentPlatform.getStringExtra("platform");

        final Button button2018 = (Button) findViewById(R.id.n2018Button);
        button2018.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                if(platform.equals("Youtube")){
                    Intent i=new Intent(AwakeningsActivity.this,YouTubeActivityPlaylist.class);
                    i.putExtra("playlist", "PLx2MVuP_pkQiakzRVOUem73hIQIsnTZP-");
                    AwakeningsActivity.this.startActivity(i);
                }else if(platform.equals("Soundcloud")){
                    Intent i=new Intent(AwakeningsActivity.this,SoundCloudActivity.class);
                    i.putExtra("url", "https://soundcloud.com/awakenings/sets/awakenings-festival-2018");
                    AwakeningsActivity.this.startActivity(i);

                }else if(platform.equals("Mixcloud")){
                    Intent i=new Intent(AwakeningsActivity.this,MixCloudActivity.class);
                    i.putExtra("playlist", "https://www.mixcloud.com/chris-maher7/playlists/awakenings-2018/");
                    AwakeningsActivity.this.startActivity(i);

                }

            }
        });
        final Button button2017 = (Button) findViewById(R.id.n2017Button);
        button2017.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent i=new Intent(AwakeningsActivity.this,YouTubeActivityPlaylist.class);
                i.putExtra("playlist", "PLmh4GKNPRUM9Q2ukvCBwAj7Jzk-JHim2H");
                i.putExtra("festival", "Awakenings");
                AwakeningsActivity.this.startActivity(i);
            }
        });
        final Button button2016 = (Button) findViewById(R.id.n2016Button);
        button2016.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent i=new Intent(AwakeningsActivity.this,YouTubeActivityPlaylist.class);
                i.putExtra("playlist", "PLmh4GKNPRUM_UAY5SaEhzxIUdDe0OiL7w");
                i.putExtra("festival", "Awakenings");
                AwakeningsActivity.this.startActivity(i);
            }
        });
        final Button button2015 = (Button) findViewById(R.id.n2015Button);
        button2015.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent i=new Intent(AwakeningsActivity.this,YouTubeActivityPlaylist.class);
                i.putExtra("playlist", "PLmh4GKNPRUM8LtV8JnXcAcm9u4j5ZI6KO");
                i.putExtra("festival", "Awakenings");
                AwakeningsActivity.this.startActivity(i);
            }
        });
        final Button button2014 = (Button) findViewById(R.id.n2014Button);
        button2014.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent i=new Intent(AwakeningsActivity.this,YouTubeActivityPlaylist.class);
                i.putExtra("playlist", "PLmh4GKNPRUM-JbuJ1RncQKQgMmz2bsuiX");
                i.putExtra("festival", "Awakenings");
                AwakeningsActivity.this.startActivity(i);
            }
        });
        final Button button2013 = (Button) findViewById(R.id.n2013Button);
        button2013.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent i=new Intent(AwakeningsActivity.this,YouTubeActivityPlaylist.class);
                i.putExtra("playlist", "PLmh4GKNPRUM_smDbP5zR9n-nw5MCvKWjw");
                i.putExtra("festival", "Awakenings");
                AwakeningsActivity.this.startActivity(i);
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

        Intent i = new Intent(AwakeningsActivity.this, PlatformsActivity.class);
        i.putExtra("festival", "Awakenings");
        AwakeningsActivity.this.startActivity(i);
    }



}
