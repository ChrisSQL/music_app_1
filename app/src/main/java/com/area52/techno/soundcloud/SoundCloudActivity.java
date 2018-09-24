package com.area52.techno.soundcloud;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.area52.techno.R;
import com.area52.techno.festivals.FestivalsActivity;

import static com.area52.techno.MusicService.CHANNEL_ID;

public class SoundCloudActivity extends Activity  {

    private WebView wv1;
    private String url;
    Intent intentPlatform;
    String platform, festival;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_cloud);

        intentPlatform = getIntent();
        platform = intentPlatform.getStringExtra("platform");
        festival = intentPlatform.getStringExtra("festival");
        url = intentPlatform.getStringExtra("url");

        //url = "https://soundcloud.com/awakenings/sets/awakenings-festival-2018";

        wv1=(WebView)findViewById(R.id.webView);
        wv1.setWebViewClient(new MyBrowser());
        wv1.getSettings().setLoadsImagesAutomatically(true);
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wv1.loadUrl(url);

        notification();



    }

    private void notification() {

        Intent intent = new Intent(getApplicationContext(), SoundCloudActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), (int)System.currentTimeMillis(), intent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(SoundCloudActivity.this, CHANNEL_ID)
                .setSmallIcon(R.drawable.video_icon_48)
                .setContentTitle("Sesh")
                .setContentText(url)
                .setContentIntent(pIntent);

        NotificationManager notificationManager = (NotificationManager) SoundCloudActivity.this.getSystemService(
                NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, "Sesh", NotificationManager.IMPORTANCE_HIGH);

            notificationManager.createNotificationChannel(mChannel);
        }

        notificationManager.notify(0, mBuilder.build());
    }




    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
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

        Intent i = new Intent(SoundCloudActivity.this, FestivalsActivity.class);
        i.putExtra("festival", festival);
        SoundCloudActivity.this.startActivity(i);

    }

}
