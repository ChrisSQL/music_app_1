package com.area52.techno.soundcloud;

import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.area52.techno.R;
import com.area52.techno.activities.MainActivity;

import static com.area52.techno.MusicService.CHANNEL_ID;

public class SoundCloudActivityNew extends AppCompatActivity {

    static final String AUDIO_PATH = "http://area52.club/music/sets/1.mp3";
    private MediaPlayer mediaPlayer;
    private int playbackPosition=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_cloud);

        sendNotification();

//        Exoplayer();
//
    }

    private void Exoplayer() {

//        WebView mWebView;
//        mWebView = (WebView)findViewById(R.id.webView);
//
//        // Enable Javascript
//        WebSettings webSettings = mWebView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//
//        mWebView.loadUrl("http://www.stephaniequinn.com/Music/Allegro%20from%20Duet%20in%20C%20Major.mp3");
//
//        // Force links and redirects to open in the WebView instead of in a browser
//        mWebView.setWebViewClient(new WebViewClient());

    }

    public void sendNotification() {

        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, SoundCloudActivityNew.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(99, mBuilder.build());
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
        startActivity(new Intent(SoundCloudActivityNew.this, MainActivity.class));
    }
}
