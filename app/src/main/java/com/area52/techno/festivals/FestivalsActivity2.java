package com.area52.techno.festivals;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.area52.techno.R;
import com.area52.techno.soundcloud.MixCloudActivity;
import com.area52.techno.soundcloud.SoundCloudActivity;
import com.area52.techno.youtube.YouTubeActivityPlaylist;

public class FestivalsActivity2 extends AppCompatActivity {

    Intent intentPlatform;
    String platform, festival;
    Button buttonHeader, buttonHeader2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platforms);

        intentPlatform = getIntent();
        festival = intentPlatform.getStringExtra("festival");

        buttonHeader = (Button) findViewById(R.id.buttonHeader);

        if(festival != null){ setHeaderImage();}

        final Button button2018 = (Button) findViewById(R.id.YoutubeButton);
        button2018.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                        Intent i = new Intent(FestivalsActivity2.this, FestivalsActivity3.class);
                        i.putExtra("platform", "Youtube");
                        i.putExtra("festival", festival);
                        FestivalsActivity2.this.startActivity(i);

                }
        });
        final Button button2017 = (Button) findViewById(R.id.SoundcloudButton);
        button2017.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Perform action on click
                Intent i = new Intent(FestivalsActivity2.this, FestivalsActivity3.class);
                i.putExtra("platform", "SoundCloud");
                i.putExtra("festival", festival);
                FestivalsActivity2.this.startActivity(i);
            }
        });
        final Button button2016 = (Button) findViewById(R.id.MixcloudButton);
        button2016.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Perform action on click
                Intent i = new Intent(FestivalsActivity2.this, FestivalsActivity3.class);
                i.putExtra("platform", "MixCloud");
                i.putExtra("festival", festival);
                FestivalsActivity2.this.startActivity(i);
            }
        });

    }

    private void setHeaderImage() {

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

        Intent i = new Intent(FestivalsActivity2.this, FestivalsActivity.class);
        i.putExtra("festival", festival);
        FestivalsActivity2.this.startActivity(i);

    }

}
