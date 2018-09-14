package com.area52.techno.soundcloud;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.graphics.Palette;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.area52.techno.MusicService;
import com.area52.techno.R;
import com.area52.techno.utils.NavigationUtils;
import com.area52.techno.utils.TimberUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import static com.area52.techno.MusicService.CHANNEL_ID;

public class SoundCloudActivity extends Activity  {

    private WebView wv1;
    private String url;
    Intent intentPlatform;
    String platforms, festival;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_cloud);

        intentPlatform = getIntent();
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



}
