package com.area52.techno.soundcloud;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.area52.techno.R;

import static com.area52.techno.MusicService.CHANNEL_ID;

public class MixCloudActivity extends Activity  {

    private WebView wv1;
    private String url;
    private NotificationManagerCompat mNotificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_cloud);

        String page="<html><body><iframe width=\"100%\" height=\"96%\" src=\"https://www.mixcloud.com/widget/iframe/?autoplay=1&feed=%2Fchris-maher7%2Fplaylists%2Fawakenings-2018%2F\" frameborder=\"0\" ></iframe></body></html>";

        url = "https://www.mixcloud.com/chris-maher7/playlists/awakenings-2018/";

        wv1=(WebView)findViewById(R.id.webView);
        wv1.setWebViewClient(new MyBrowser());
        wv1.getSettings().setLoadsImagesAutomatically(true);
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        //wv1.loadUrl(url);
        wv1.loadDataWithBaseURL("x-data://base", page,
                "text/html", "UTF-8",
                null);

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



}
