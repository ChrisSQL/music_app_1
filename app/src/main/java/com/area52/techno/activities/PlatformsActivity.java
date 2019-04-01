package com.area52.techno.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.area52.techno.R;
import com.area52.techno.festivals.ADE;
import com.area52.techno.festivals.AwakeningsActivity;
import com.area52.techno.festivals.FestivalsActivity;
import com.area52.techno.festivals.FuturActivity;
import com.area52.techno.festivals.LuminosityActivity;
import com.area52.techno.festivals.PrintworksActivity;
import com.area52.techno.festivals.TimeWarpActivity;
import com.area52.techno.soundcloud.MixCloudActivity;
import com.area52.techno.soundcloud.SoundCloudActivity;
import com.area52.techno.youtube.YouTubeActivityPlaylist;

public class PlatformsActivity extends AppCompatActivity {

    Intent intentPlatform;
    String platforms, festival;
    Button buttonHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platforms);

        intentPlatform = getIntent();
        platforms = intentPlatform.getStringExtra("platforms");
        festival = intentPlatform.getStringExtra("festival");

        Toast.makeText(this, festival, Toast.LENGTH_SHORT).show();

        buttonHeader = (Button) findViewById(R.id.buttonHeader);

        if(festival != null){ setHeaderImage();}

        final Button button2018 = (Button) findViewById(R.id.YoutubeButton2018);
        button2018.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                switch (festival) {
                    case "Awakenings": {
                        Intent i = new Intent(PlatformsActivity.this, AwakeningsActivity.class);
                        i.putExtra("platform", "Youtube");
                        i.putExtra("festival", festival);
                        PlatformsActivity.this.startActivity(i);
                        break;
                    }
                    case "TimeWarp": {
                        Intent i = new Intent(PlatformsActivity.this, TimeWarpActivity.class);
                        i.putExtra("platform", "Youtube");
                        i.putExtra("festival", festival);
                        PlatformsActivity.this.startActivity(i);
                        break;
                    }
                    case "Futur": {
                        Intent i = new Intent(PlatformsActivity.this, FuturActivity.class);
                        i.putExtra("platform", "Youtube");
                        i.putExtra("festival", festival);
                        PlatformsActivity.this.startActivity(i);
                        break;
                    }
                    case "Printworks": {
                        Intent i = new Intent(PlatformsActivity.this, PrintworksActivity.class);
                        i.putExtra("platform", "Youtube");
                        i.putExtra("festival", festival);
                        PlatformsActivity.this.startActivity(i);
                        break;
                    }
                    case "ADE": {
                        Intent i = new Intent(PlatformsActivity.this, ADE.class);
                        i.putExtra("platform", "Youtube");
                        i.putExtra("festival", festival);
                        PlatformsActivity.this.startActivity(i);
                        break;
                    }
                    case "Lumo": {
                        Intent i = new Intent(PlatformsActivity.this, LuminosityActivity.class);
                        i.putExtra("platform", "Youtube");
                        i.putExtra("festival", festival);
                        PlatformsActivity.this.startActivity(i);
                        break;
                    }
                    default:
                        Toast.makeText(PlatformsActivity.this, "Festival Not Added Yet.", Toast.LENGTH_SHORT).show();
                        break;
                }




            }
        });
        final Button button2017 = (Button) findViewById(R.id.SoundcloudButton2018);
        button2017.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                switch (festival) {
                    case "Awakenings": {
                        Intent i = new Intent(PlatformsActivity.this, AwakeningsActivity.class);
                        i.putExtra("platform", "SoundCloud");
                        i.putExtra("festival", festival);
                        PlatformsActivity.this.startActivity(i);
                        break;
                    }
                    case "TimeWarp": {
                        Intent i = new Intent(PlatformsActivity.this, TimeWarpActivity.class);
                        i.putExtra("platform", "SoundCloud");
                        i.putExtra("festival", festival);
                        PlatformsActivity.this.startActivity(i);
                        break;
                    }
                    case "Futur": {
                        Intent i = new Intent(PlatformsActivity.this, FuturActivity.class);
                        i.putExtra("platform", "SoundCloud");
                        i.putExtra("festival", festival);
                        PlatformsActivity.this.startActivity(i);
                        break;
                    }
                    case "Printworks": {
                        Intent i = new Intent(PlatformsActivity.this, PrintworksActivity.class);
                        i.putExtra("platform", "SoundCloud");
                        i.putExtra("festival", festival);
                        PlatformsActivity.this.startActivity(i);
                        break;
                    }
                    case "ADE": {
                        Intent i = new Intent(PlatformsActivity.this, ADE.class);
                        i.putExtra("platform", "SoundCloud");
                        i.putExtra("festival", festival);
                        PlatformsActivity.this.startActivity(i);
                        break;
                    }
                    case "Lumo": {
                        Intent i = new Intent(PlatformsActivity.this, LuminosityActivity.class);
                        i.putExtra("platform", "SoundCloud");
                        i.putExtra("festival", festival);
                        PlatformsActivity.this.startActivity(i);
                        break;
                    }
                    default:
                        Toast.makeText(PlatformsActivity.this, "Festival Not Added Yet.", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        final Button button2016 = (Button) findViewById(R.id.MixcloudButton2018);
        button2016.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                switch (festival) {
                    case "Awakenings": {
                        Intent i = new Intent(PlatformsActivity.this, AwakeningsActivity.class);
                        i.putExtra("platform", "MixCloud");
                        i.putExtra("festival", festival);
                        PlatformsActivity.this.startActivity(i);
                        break;
                    }
                    case "TimeWarp": {
                        Intent i = new Intent(PlatformsActivity.this, TimeWarpActivity.class);
                        i.putExtra("platform", "MixCloud");
                        i.putExtra("festival", festival);
                        PlatformsActivity.this.startActivity(i);
                        break;
                    }
                    case "Futur": {
                        Intent i = new Intent(PlatformsActivity.this, FuturActivity.class);
                        i.putExtra("platform", "MixCloud");
                        i.putExtra("festival", festival);
                        PlatformsActivity.this.startActivity(i);
                        break;
                    }
                    case "Printworks": {
                        Intent i = new Intent(PlatformsActivity.this, PrintworksActivity.class);
                        i.putExtra("platform", "MixCloud");
                        i.putExtra("festival", festival);
                        PlatformsActivity.this.startActivity(i);
                        break;
                    }
                    case "ADE": {
                        Intent i = new Intent(PlatformsActivity.this, ADE.class);
                        i.putExtra("platform", "MixCloud");
                        i.putExtra("festival", festival);
                        PlatformsActivity.this.startActivity(i);
                        break;
                    }
                    case "Lumo": {
                        Intent i = new Intent(PlatformsActivity.this, LuminosityActivity.class);
                        i.putExtra("platform", "MixCloud");
                        i.putExtra("festival", festival);
                        PlatformsActivity.this.startActivity(i);
                        break;
                    }
                    default:
                        Toast.makeText(PlatformsActivity.this, "Festival Not Added Yet.", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

//        final Button button2015 = (Button) findViewById(R.id.PrintWorksButton);
//        button2015.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Perform action on click
//                goToFestival("Printworks");
//            }
//        });
//        final Button button2014 = (Button) findViewById(R.id.adeButton);
//        button2014.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Perform action on click
//                goToFestival("ADE");
//            }
//        });
//        final Button button2013 = (Button) findViewById(R.id.lumoButton);
//        button2013.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Perform action on click
//                goToFestival("Lumo");
//            }
//        });

    }

    private void setHeaderImage() {

     //     Toast.makeText(this, festival, Toast.LENGTH_LONG).show();

        if(festival.equals("Awakenings")){
            buttonHeader.setBackgroundResource(R.drawable.awakenings_white_square_1500);
        }else if(festival.equals("TimeWarp")){
            buttonHeader.setBackgroundResource(R.drawable.timewarp_square_1500);
        }else if(festival.equals("Futur")){
            buttonHeader.setBackgroundResource(R.drawable.futur_1500);
        }else if(festival.equals("Printworks")){
            buttonHeader.setBackgroundResource(R.drawable.printworks_1500);
        }else if(festival.equals("ADE")){
            buttonHeader.setBackgroundResource(R.drawable.ade_square_1500);
        }else if(festival.equals("Lumo")){
            buttonHeader.setBackgroundResource(R.drawable.lumo_square_1500);
        }
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

        Intent i = new Intent(PlatformsActivity.this, FestivalsActivity.class);
        i.putExtra("festival", festival);
        PlatformsActivity.this.startActivity(i);

    }

    public void goToFestival(String festivalIn){

        if(festivalIn == "Youtube"){
            Intent i=new Intent(PlatformsActivity.this,YouTubeActivityPlaylist.class);
            i.putExtra("festival", festivalIn);
            PlatformsActivity.this.startActivity(i);
        }else if(festivalIn == "Soundcloud"){
            Intent i=new Intent(PlatformsActivity.this,SoundCloudActivity.class);
            i.putExtra("festival", festivalIn);
            PlatformsActivity.this.startActivity(i);
        }else if(festivalIn == "Mixcloud"){
            Intent i=new Intent(PlatformsActivity.this,MixCloudActivity.class);
            i.putExtra("festival", festivalIn);
            PlatformsActivity.this.startActivity(i);
        }

    }



}
