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
import com.area52.techno.activities.PlatformsActivity;
import com.area52.techno.soundcloud.MixCloudActivity;
import com.area52.techno.soundcloud.SoundCloudActivity;
import com.area52.techno.youtube.YouTubeActivityPlaylist;

public class FestivalsActivity3 extends AppCompatActivity {

    Intent intentPlatform;
    String platform, festival;
    Button buttonHeader, buttonHeader2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festivals_years_rework);

        intentPlatform = getIntent();
        platform = intentPlatform.getStringExtra("platform");
        festival = intentPlatform.getStringExtra("festival");

        buttonHeader = (Button) findViewById(R.id.nHeaderButton);
        buttonHeader2 = (Button) findViewById(R.id.nHeaderButtonPlatform);

        if(festival != null){ setHeaderImage();}

        // Toast.makeText(FestivalsActivity3.this, platform, Toast.LENGTH_SHORT).show();


        final Button button2018 = (Button) findViewById(R.id.n2018Button);
        button2018.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Perform action on click
                if(platform.equals("Youtube")){

                    Intent i=new Intent(FestivalsActivity3.this,YouTubeActivityPlaylist.class);

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
                    FestivalsActivity3.this.startActivity(i);

                }else if(platform.equals("SoundCloud")){
                    Intent i=new Intent(FestivalsActivity3.this,SoundCloudActivity.class);

                    if(festival.equals("Awakenings")){
                        i.putExtra("url", "https://soundcloud.com/awakenings/sets/awakenings-festival-2017");
                    }else if(festival.equals("TimeWarp")){
                    }else if(festival.equals("Futur")){
                    }else if(festival.equals("Printworks")){
                    }else if(festival.equals("ADE")){
                    }else if(festival.equals("Lumo")){
                    }

                    i.putExtra("festival", festival);
                    FestivalsActivity3.this.startActivity(i);

                }else if(platform.equals("MixCloud")){
                    Intent i=new Intent(FestivalsActivity3.this,MixCloudActivity.class);

                    if(festival.equals("Awakenings")){
                        i.putExtra("playlist", "https://m.mixcloud.com/chris-maher7/playlists/awakenings-2018/");
                    }else if(festival.equals("TimeWarp")){
                    }else if(festival.equals("Futur")){
                    }else if(festival.equals("Printworks")){
                    }else if(festival.equals("ADE")){
                    }else if(festival.equals("Lumo")){
                    }

                    i.putExtra("festival", festival);
                    FestivalsActivity3.this.startActivity(i);

                }



            }
        });
        final Button button2017 = (Button) findViewById(R.id.n2017Button);
        button2017.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                if(platform.equals("Youtube")){
                    Intent i=new Intent(FestivalsActivity3.this,YouTubeActivityPlaylist.class);

                    if(festival.equals("Awakenings")){
                        i.putExtra("playlist", "PLx2MVuP_pkQiakzRVOUem73hIQIsnTZP-");
                    }else if(festival.equals("TimeWarp")){
                        i.putExtra("playlist", "PLW_7xvV4xv5j9cr7ZYgwN0c4ee8uosvYH");
                    }else if(festival.equals("Futur")){
                        i.putExtra("playlist", "PLW_7xvV4xv5g_lMAo2aaDp5FIfNtEAXh_");
                    }else if(festival.equals("Printworks")){
                        i.putExtra("playlist", "PLW_7xvV4xv5gQpleBLgrdNdcREWiesZC9");

                    }else if(festival.equals("ADE")){
                        i.putExtra("playlist", "PLW_7xvV4xv5jJ3aPxGxj5Th6jGQgt1ZKY");
                    }else if(festival.equals("Lumo")){
                        i.putExtra("playlist", "PLmh4GKNPRUM-XYmZRNtsHszE5c_hcdJYl");
                    }

                    i.putExtra("festival", festival);
                    FestivalsActivity3.this.startActivity(i);

                }else if(platform.equals("SoundCloud")){
                    Intent i=new Intent(FestivalsActivity3.this,SoundCloudActivity.class);

                    if(festival.equals("Awakenings")){
                        i.putExtra("url", "https://soundcloud.com/awakenings/sets/awakenings-festival-2017");
                    }else if(festival.equals("TimeWarp")){
                    }else if(festival.equals("Futur")){
                    }else if(festival.equals("Printworks")){
                    }else if(festival.equals("ADE")){
                    }else if(festival.equals("Lumo")){
                    }

                    i.putExtra("festival", festival);
                    FestivalsActivity3.this.startActivity(i);

                }else if(platform.equals("MixCloud")){
                    Intent i=new Intent(FestivalsActivity3.this,MixCloudActivity.class);

                    if(festival.equals("Awakenings")){
                        i.putExtra("playlist", "https://m.mixcloud.com/chris-maher7/playlists/awakenings-2017/");
                    }else if(festival.equals("TimeWarp")){
                    }else if(festival.equals("Futur")){
                    }else if(festival.equals("Printworks")){
                    }else if(festival.equals("ADE")){
                    }else if(festival.equals("Lumo")){
                    }

                    i.putExtra("festival", festival);
                    FestivalsActivity3.this.startActivity(i);
                }
            }
        });
        final Button button2016 = (Button) findViewById(R.id.n2016Button);
        button2016.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                if(platform.equals("Youtube")){
                    Intent i=new Intent(FestivalsActivity3.this,YouTubeActivityPlaylist.class);

                    if(festival.equals("Awakenings")){
                        i.putExtra("playlist", "PLx2MVuP_pkQiakzRVOUem73hIQIsnTZP-");
                    }else if(festival.equals("TimeWarp")){
                        i.putExtra("playlist", "PLmh4GKNPRUM81GJbBJCDxEcjo_iRDUITm");
                    }else if(festival.equals("Futur")){
                        i.putExtra("playlist", "PLmh4GKNPRUM-poSEov2M1BjSrKyL6Uzzi");
                    }else if(festival.equals("Printworks")){
                        i.putExtra("playlist", "PLW_7xvV4xv5gQpleBLgrdNdcREWiesZC9");
                    }else if(festival.equals("ADE")){
                        i.putExtra("playlist", "PLmh4GKNPRUM9cx_jF-prtQc2Ra4knttP_");
                    }else if(festival.equals("Lumo")){
                        i.putExtra("playlist", "PLmh4GKNPRUM81GJbBJCDxEcjo_iRDUITm");
                    }

                    i.putExtra("festival", festival);
                    FestivalsActivity3.this.startActivity(i);

                }else if(platform.equals("SoundCloud")){
                    Intent i=new Intent(FestivalsActivity3.this,SoundCloudActivity.class);

                    if(festival.equals("Awakenings")){
                        i.putExtra("playlist", "https://m.mixcloud.com/chris-maher7/playlists/awakenings-2016/");
                    }else if(festival.equals("TimeWarp")){
                    }else if(festival.equals("Futur")){
                    }else if(festival.equals("Printworks")){
                    }else if(festival.equals("ADE")){
                    }else if(festival.equals("Lumo")){
                    }

                    i.putExtra("festival", festival);
                    FestivalsActivity3.this.startActivity(i);

                }else if(platform.equals("MixCloud")){
                    Intent i=new Intent(FestivalsActivity3.this,MixCloudActivity.class);

                    if(festival.equals("Awakenings")){
                        i.putExtra("url", "https://soundcloud.com/awakenings/sets/awakenings-festival-2017");
                    }else if(festival.equals("TimeWarp")){
                    }else if(festival.equals("Futur")){
                    }else if(festival.equals("Printworks")){
                    }else if(festival.equals("ADE")){
                    }else if(festival.equals("Lumo")){
                    }

                    i.putExtra("festival", festival);
                    FestivalsActivity3.this.startActivity(i);
                }
            }
        });
        final Button button2015 = (Button) findViewById(R.id.n2015Button);
        button2015.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                if(platform.equals("Youtube")){
                    Intent i=new Intent(FestivalsActivity3.this,YouTubeActivityPlaylist.class);

                    if(festival.equals("Awakenings")){
                        i.putExtra("playlist", "PLx2MVuP_pkQiakzRVOUem73hIQIsnTZP-");
                    }else if(festival.equals("TimeWarp")){
                        i.putExtra("playlist", "PLmh4GKNPRUM_bfmS6sLcclfZEoHc1xPBW");
                    }else if(festival.equals("Futur")){
                        i.putExtra("playlist", "PLryUKXcw5PbwoK8CsJmicUT9KYw5tl020");
                    }else if(festival.equals("Printworks")){
                        i.putExtra("playlist", "PLW_7xvV4xv5gQpleBLgrdNdcREWiesZC9");
                    }else if(festival.equals("ADE")){
                    }else if(festival.equals("Lumo")){
                        i.putExtra("playlist", "PLmh4GKNPRUM9ASj3IakXfrSvXsF79oL4T");
                    }

                    i.putExtra("festival", festival);
                    FestivalsActivity3.this.startActivity(i);

                }else if(platform.equals("SoundCloud")){
                    Intent i=new Intent(FestivalsActivity3.this,SoundCloudActivity.class);

                    if(festival.equals("Awakenings")){
                        i.putExtra("url", "https://soundcloud.com/chris-maher-28/sets/awakenings-2015");
                    }else if(festival.equals("TimeWarp")){
                    }else if(festival.equals("Futur")){
                    }else if(festival.equals("Printworks")){
                    }else if(festival.equals("ADE")){
                    }else if(festival.equals("Lumo")){
                    }

                    i.putExtra("festival", festival);
                    FestivalsActivity3.this.startActivity(i);

                }else if(platform.equals("MixCloud")){
                    Intent i=new Intent(FestivalsActivity3.this,MixCloudActivity.class);

                    if(festival.equals("Awakenings")){
                        i.putExtra("playlist", "https://m.mixcloud.com/chris-maher7/playlists/awakenings-2015/");
                    }else if(festival.equals("TimeWarp")){
                    }else if(festival.equals("Futur")){
                    }else if(festival.equals("Printworks")){
                    }else if(festival.equals("ADE")){
                    }else if(festival.equals("Lumo")){
                    }

                    i.putExtra("festival", festival);
                    FestivalsActivity3.this.startActivity(i);
                }
            }
        });
        final Button button2014 = (Button) findViewById(R.id.n2014Button);
        button2014.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                if(platform.equals("Youtube")){
                    Intent i=new Intent(FestivalsActivity3.this,YouTubeActivityPlaylist.class);

                    if(festival.equals("Awakenings")){
                        i.putExtra("playlist", "PLx2MVuP_pkQiakzRVOUem73hIQIsnTZP-");
                    }else if(festival.equals("TimeWarp")){
                        i.putExtra("playlist", "PLmh4GKNPRUM_5owpicvtHBtVvnZElaVX5");
                    }else if(festival.equals("Futur")){
                        i.putExtra("playlist", "PLryUKXcw5Pbx1FTzsSMRX3gIwh0Mio15e");
                    }else if(festival.equals("Printworks")){
                        i.putExtra("playlist", "PLW_7xvV4xv5gQpleBLgrdNdcREWiesZC9");
                    }else if(festival.equals("ADE")){
                    }else if(festival.equals("Lumo")){
                        i.putExtra("playlist", "PLmh4GKNPRUM-9iav7B4uzFMNT09BpHk-f");
                    }

                    i.putExtra("festival", festival);
                    FestivalsActivity3.this.startActivity(i);

                }else if(platform.equals("SoundCloud")){
                    Intent i=new Intent(FestivalsActivity3.this,SoundCloudActivity.class);

                    if(festival.equals("Awakenings")){
                        i.putExtra("url", "https://soundcloud.com/awakenings/sets/awakenings-festival-2014");
                    }else if(festival.equals("TimeWarp")){
                    }else if(festival.equals("Futur")){
                    }else if(festival.equals("Printworks")){
                    }else if(festival.equals("ADE")){
                    }else if(festival.equals("Lumo")){
                    }

                    i.putExtra("festival", festival);
                    FestivalsActivity3.this.startActivity(i);

                }else if(platform.equals("MixCloud")){
                    Intent i=new Intent(FestivalsActivity3.this,MixCloudActivity.class);

                    if(festival.equals("Awakenings")){
                        i.putExtra("playlist", "https://m.mixcloud.com/chris-maher7/playlists/awakenings-2014/");
                    }else if(festival.equals("TimeWarp")){
                    }else if(festival.equals("Futur")){
                    }else if(festival.equals("Printworks")){
                    }else if(festival.equals("ADE")){
                    }else if(festival.equals("Lumo")){
                    }

                    i.putExtra("festival", festival);
                    FestivalsActivity3.this.startActivity(i);
                }
            }
        });
        final Button button2013 = (Button) findViewById(R.id.n2013Button);
        button2013.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                if(platform.equals("Youtube")){
                    Intent i=new Intent(FestivalsActivity3.this,YouTubeActivityPlaylist.class);

                    if(festival.equals("Awakenings")){
                        i.putExtra("playlist", "PLx2MVuP_pkQiakzRVOUem73hIQIsnTZP-");
                    }else if(festival.equals("TimeWarp")){
                        i.putExtra("playlist", "PLmh4GKNPRUM8eZM30brviQNR7Rn1PV-Cz");
                    }else if(festival.equals("Futur")){
                        i.putExtra("playlist", "PLryUKXcw5Pbw7y_H0X2l4FzS6JFpoI8aT");
                    }else if(festival.equals("Printworks")){
                        i.putExtra("playlist", "PLW_7xvV4xv5gQpleBLgrdNdcREWiesZC9");
                    }else if(festival.equals("ADE")){
                    }else if(festival.equals("Lumo")){
                        i.putExtra("playlist", "PLmh4GKNPRUM85mYf1Q8oAUJdOWeZG2-8h");

                    }

                    i.putExtra("festival", festival);
                    FestivalsActivity3.this.startActivity(i);

                }else if(platform.equals("SoundCloud")){
                    Intent i=new Intent(FestivalsActivity3.this,SoundCloudActivity.class);

                    if(festival.equals("Awakenings")){
                        i.putExtra("url", "https://soundcloud.com/awakenings/sets/awakenings-festival-2013");
                    }else if(festival.equals("TimeWarp")){
                    }else if(festival.equals("Futur")){
                    }else if(festival.equals("Printworks")){
                    }else if(festival.equals("ADE")){
                    }else if(festival.equals("Lumo")){
                    }

                    i.putExtra("festival", festival);
                    FestivalsActivity3.this.startActivity(i);

                }else if(platform.equals("MixCloud")){
                    Intent i=new Intent(FestivalsActivity3.this,MixCloudActivity.class);

                    if(festival.equals("Awakenings")){
                        i.putExtra("playlist", "https://m.mixcloud.com/chris-maher7/playlists/awakenings-2013/");
                    }else if(festival.equals("TimeWarp")){
                    }else if(festival.equals("Futur")){
                    }else if(festival.equals("Printworks")){
                    }else if(festival.equals("ADE")){
                    }else if(festival.equals("Lumo")){
                    }

                    i.putExtra("festival", festival);
                    FestivalsActivity3.this.startActivity(i);
                }
            }
        });

    }

    private void setHeaderImage() {

        if(platform.equals("Youtube")){
            buttonHeader2.setBackgroundResource(R.drawable.youtube_1500);
        }else if(platform.equals("SoundCloud")){
            buttonHeader2.setBackgroundResource(R.drawable.soundcloud_1500);
        }else if(platform.equals("MixCloud")){
            buttonHeader2.setBackgroundResource(R.drawable.mixcloud_1500);
        }

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

        Intent i = new Intent(FestivalsActivity3.this, FestivalsActivity2.class);
        i.putExtra("festival", festival);
        FestivalsActivity3.this.startActivity(i);

    }



}
