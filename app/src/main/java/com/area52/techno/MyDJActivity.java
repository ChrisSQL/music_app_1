package com.area52.techno;

           // Get user details identified by userID
//        // Required
//        String name;
//        String PhotoUrl;
//        String djLogoImage;
//        String county;
//        String country;
//        String email;
//        String phoneNumber;

//        // Optional
//        String facebookUserID;
//        String bio;
//        String soundcloudLink;
//        String youtubeLink;
//        String mixcloudLink;
//        String facebookLink;
//        String instagramLink;
//        String spotifyLink;
//        String bookingEmail;
//        String genre;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.area52.techno.dj.MainActivityDJ;
import com.area52.techno.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.List;

import static junit.framework.Assert.fail;

public class MyDJActivity extends AppCompatActivity {

    SharedPreferences preferences;
    TextView ProfileName;
    String photoUrl, FacebookLink, djName, getPhotoUrlDJ, SoundcloudLink, YoutubeLink, BookingLink,  country, genre, eventLink, videosLink;
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]
    private FirebaseFirestore db;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference myRef ;
    List<User> list;
    ImageView profilePicture, dj_profile_card1_image, dj_profile_card2_image, dj_profile_card3_image, dj_profile_card4_image, dj_profile_card5_image, dj_profile_card6_image;
    LinearLayout profileBackground;
    Bundle extras;
    Query userQuery;
    User userProfile;
    Intent intent;
    ImageButton facebook_profile_button, youtube_profile_button, bookings_profile_button, soundcloud_profile_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dj);

        intent = getIntent();
        declareVariables();
        loadUserButtons();
        updateGUI();

     //   Get userID
     //   declareVariables();// Choose User
     //   chooseUSer();

    }

    private void updateGUI() {

//        FacebookLink = "https://facebook.com/profile.php?id=" + userProfile.getFbID();
        ProfileName.setText(djName);
        PicassoImageLoad(profilePicture, profileBackground, ProfileName, getPhotoUrlDJ);

        Picasso.with(MyDJActivity.this)
                .load(getPhotoUrlDJ)
                .placeholder(R.drawable.sesh_logo)
                .into(profilePicture);

    }

    private void declareVariables() {

        profilePicture = (ImageView) findViewById(R.id.friendProfilePicture);

        dj_profile_card1_image = (ImageView) findViewById(R.id.dj_profile_card1_image);
        dj_profile_card2_image = (ImageView) findViewById(R.id.dj_profile_card2_image);
        dj_profile_card3_image = (ImageView) findViewById(R.id.dj_profile_card3_image);
        dj_profile_card4_image = (ImageView) findViewById(R.id.dj_profile_card4_image);
        dj_profile_card5_image = (ImageView) findViewById(R.id.dj_profile_card5_image);
        dj_profile_card6_image = (ImageView) findViewById(R.id.dj_profile_card6_image);


        profileBackground = (LinearLayout) findViewById(R.id.profileBackground);
        ProfileName = (TextView) findViewById(R.id.ProfileName);




        db = FirebaseFirestore.getInstance();
        database = FirebaseDatabase.getInstance();

        // Get Firebase User
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        extras = getIntent().getExtras();

        if (extras != null) {

            djName = extras.getString("djName");
            getPhotoUrlDJ = extras.getString("getPhotoUrlDJ");
            FacebookLink = extras.getString("FacebookLink");
            SoundcloudLink = extras.getString("SoundcloudLink");
            BookingLink = extras.getString("FacebookLink");
            YoutubeLink = extras.getString("YoutubeLink");
            country = extras.getString("Country");
            genre = extras.getString("genre");

        //    Toast.makeText(this, SoundcloudLink, Toast.LENGTH_SHORT).show();


        }else {
            // Redirect to DJ List
            Intent i=new Intent(this, MainActivityDJ.class);
            this.startActivity(i);
        }

        myRef = database.getReference("DJ");
    }

    private void loadUserButtons() {

        eventLink = FacebookLink + "events";
        videosLink = FacebookLink + "videos";

        // Biography
        dj_profile_card1_image = (ImageView)  findViewById(R.id.dj_profile_card1_image);
        dj_profile_card1_image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(FacebookLink));
                startActivity(i);

            }
        });

        // Facebook
        dj_profile_card2_image = (ImageView)  findViewById(R.id.dj_profile_card2_image);
        dj_profile_card2_image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(FacebookLink));
                startActivity(i);

            }
        });

        // Share
        dj_profile_card3_image = (ImageView)  findViewById(R.id.dj_profile_card3_image);
        dj_profile_card3_image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.area52.techno&hl=en");
                startActivity(Intent.createChooser(shareIntent, "Share link using"));

            }
        });

        // Events
        dj_profile_card4_image = (ImageView)  findViewById(R.id.dj_profile_card4_image);
        dj_profile_card4_image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(eventLink));
                startActivity(i);

            }
        });

        // Youtube
        dj_profile_card5_image = (ImageView)  findViewById(R.id.dj_profile_card5_image);
        dj_profile_card5_image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(videosLink));
                startActivity(i);

            }
        });

        // Soundcloud
        dj_profile_card6_image = (ImageView)  findViewById(R.id.dj_profile_card6_image);
        dj_profile_card6_image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(SoundcloudLink));
                startActivity(i);

            }
        });

        Picasso.with(MyDJActivity.this)
                .load(photoUrl)
                .placeholder(R.drawable.sesh_logo)
                .into(profilePicture);

        Picasso.with(MyDJActivity.this)
                .load(photoUrl)
                .placeholder(R.drawable.informationpng)
                .into(dj_profile_card1_image);

        Picasso.with(MyDJActivity.this)
                .load(photoUrl)
                .placeholder(R.drawable.facebookpng)
                .into(dj_profile_card2_image);

        Picasso.with(MyDJActivity.this)
                .load(photoUrl)
                .placeholder(R.drawable.sharepng)
                .into(dj_profile_card3_image);

        Picasso.with(MyDJActivity.this)
                .load(photoUrl)
                .placeholder(R.drawable.calendarpng)
                .into(dj_profile_card4_image);

        Picasso.with(MyDJActivity.this)
                .load(photoUrl)
                .placeholder(R.drawable.youtubepng)
                .into(dj_profile_card5_image);

        Picasso.with(MyDJActivity.this)
                .load(photoUrl)
                .placeholder(R.drawable.soundcloudpng)
                .into(dj_profile_card6_image);

    }

    private void PicassoImageLoad(ImageView profilePicture, LinearLayout profileBackground, TextView profileName, String photoUrlIn) {
        Picasso.with(MyDJActivity.this)
                .load(getPhotoUrlDJ)
                .resize(200, 200)
                .centerCrop()
                .into(profilePicture);
    }

    public class RoundedTransformation implements com.squareup.picasso.Transformation {
        private final int radius;
        private final int margin;  // dp

        // radius is corner radii in dp
        // margin is the board in dp
        public RoundedTransformation(final int radius, final int margin) {
            this.radius = radius;
            this.margin = margin;
        }

        @Override
        public Bitmap transform(final Bitmap source) {


            final Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

            Bitmap output = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);
            canvas.drawRoundRect(new RectF(margin, margin, source.getWidth() - margin, source.getHeight() - margin), radius, radius, paint);

            if (source != output) {
                source.recycle();
            }

            Paint paint1 = new Paint();
            paint1.setColor(Color.BLACK);
            paint1.setStyle(Paint.Style.STROKE);
            paint1.setAntiAlias(true);
            paint1.setStrokeWidth(2);
            canvas.drawCircle((source.getWidth() - margin)/2, (source.getHeight() - margin)/2, radius-2, paint1);


            return output;
        }

        @Override
        public String key() {
            return "rounded";
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

//    @Override
//    public void onBackPressed() {
//        startActivity(new Intent(MyDJActivity.this, MainActivity.class));
//    }

}
