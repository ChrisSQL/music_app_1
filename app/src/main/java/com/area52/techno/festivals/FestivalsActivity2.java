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

        final Button button2018 = (Button) findViewById(R.id.YoutubeButton2018);
        button2018.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i=new Intent(FestivalsActivity2.this,YouTubeActivityPlaylist.class);

                if(festival.equals("Awakenings")){
                    i.putExtra("playlist", "PLx2MVuP_pkQiakzRVOUem73hIQIsnTZP-");
                }else if(festival.equals("TimeWarp")){
                    i.putExtra("playlist", "PLW_7xvV4xv5jdFXZmYmlbcLuUo_gFKl0C");
                }else if(festival.equals("Futur")){
                    i.putExtra("playlist", "PLW_7xvV4xv5iaNbNmifsESuLudowtf28L");
                }else if(festival.equals("Printworks")){
                    i.putExtra("playlist", "PLW_7xvV4xv5gQpleBLgrdNdcREWiesZC9");
                }else if(festival.equals("ADE")){

                }else if(festival.equals("Lumo")){
                    i.putExtra("playlist", "PLqdiEcHh28muXNP7BGxqTYGW01RYMKzH7");
                }

                i.putExtra("festival", festival);
                FestivalsActivity2.this.startActivity(i);


                }
        });
        final Button button2017 = (Button) findViewById(R.id.SoundcloudButton2018);
        button2017.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i=new Intent(FestivalsActivity2.this,YouTubeActivityPlaylist.class);

                if(festival.equals("Awakenings")){
                    i.putExtra("url", "https://soundcloud.com/awakenings/sets/awakenings-festival-2017");
                }else if(festival.equals("TimeWarp")){

                }else if(festival.equals("Futur")){

                }else if(festival.equals("Printworks")){

                }else if(festival.equals("ADE")){

                }else if(festival.equals("Lumo")){

                }

                i.putExtra("festival", festival);
                FestivalsActivity2.this.startActivity(i);

            }
        });
        final Button button2016 = (Button) findViewById(R.id.MixcloudButton2018);
        button2016.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i=new Intent(FestivalsActivity2.this,MixCloudActivity.class);

                if(festival.equals("Awakenings")){
                    i.putExtra("playlist", "https://m.mixcloud.com/chris-maher7/playlists/awakenings-2018/");
                }else if(festival.equals("TimeWarp")){

                }else if(festival.equals("Futur")){

                }else if(festival.equals("Printworks")){

                }else if(festival.equals("ADE")){

                }else if(festival.equals("Lumo")){

                }

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
