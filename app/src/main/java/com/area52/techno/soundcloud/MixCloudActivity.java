package com.area52.techno.soundcloud;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.area52.techno.R;
import com.area52.techno.activities.FestivalsActivity;

import static com.area52.techno.MusicService.CHANNEL_ID;

public class MixCloudActivity extends Activity  {

    private WebView webView;
    private String url,VIDEO_URL;
    private NotificationManagerCompat mNotificationManager;
    Intent intentPlatform;
    String platform, festival,playlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_cloud);

        VIDEO_URL = "133075162";

        intentPlatform = getIntent();
        platform = intentPlatform.getStringExtra("platform");
        festival = intentPlatform.getStringExtra("festival");
        playlist = intentPlatform.getStringExtra("playlist");
        //String url = "<iframe width=\"100%\" height=\"400\" src=\"" + playlist + "\" frameborder=\"0\" ></iframe>";

        String frameVideo = "<html><body><iframe width=\"100%\" height=\"100%\" src=\"https://www.mixcloud.com/widget/iframe/?autoplay=1&feed=%2Fchris-maher7%2Fplaylists%2Fawakenings-2018%2F\" frameborder=\"0\" ></iframe></body></html>";

        WebView displayYoutubeVideo = (WebView) findViewById(R.id.webView);
        displayYoutubeVideo.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        WebSettings webSettings = displayYoutubeVideo.getSettings();
        webSettings.setJavaScriptEnabled(true);
        displayYoutubeVideo.loadData(frameVideo, "text/html", "utf-8");
        notification();



    }

    private void notification() {

        Intent intent = new Intent(getApplicationContext(), MixCloudActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), (int)System.currentTimeMillis(), intent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MixCloudActivity.this, CHANNEL_ID)
                .setSmallIcon(R.drawable.video_icon_48)
                .setContentTitle("Sesh")
                .setContentText(url)
                .setContentIntent(pIntent);

        NotificationManager notificationManager = (NotificationManager) MixCloudActivity.this.getSystemService(
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

        Intent i = new Intent(MixCloudActivity.this, FestivalsActivity.class);
        i.putExtra("festival", festival);
        MixCloudActivity.this.startActivity(i);

    }

}
