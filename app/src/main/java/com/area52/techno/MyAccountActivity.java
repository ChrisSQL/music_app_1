package com.area52.techno;

           // Get user details identified by userID
//        // Required
//        String name;
//        String image;
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
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.area52.techno.activities.MainActivity;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class MyAccountActivity extends AppCompatActivity {

    SharedPreferences preferences;
    TextView FBName;
    String photoUrl, profileURL;
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        // [START get_firestore_instance]
        db = FirebaseFirestore.getInstance();

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        String facebookUserId = "";
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ImageView profilePicture = (ImageView) findViewById(R.id.friendProfilePicture);
        LinearLayout profileBackground = (LinearLayout) findViewById(R.id.profileBackground);
        TextView ProfileName = (TextView) findViewById(R.id.ProfileName);

        ProfileName.setText(user.getDisplayName());

        // find the Facebook profile and get the user's id
        for(UserInfo profile : user.getProviderData()) {
            // check if the provider id matches "facebook.com"
            if(FacebookAuthProvider.PROVIDER_ID.equals(profile.getProviderId())) {
                facebookUserId = profile.getUid();
            }
        }

        // construct the URL to the profile picture, with a custom height
        // alternatively, use '?type=small|medium|large' instead of ?height=
        photoUrl = "https://graph.facebook.com/" + facebookUserId + "/picture?height=500";
        profileURL = "https://facebook.com/100010092107570";

        PicassoImageLoad(profilePicture, profileBackground, ProfileName);

        final ImageView profileFacebookButton = (ImageView) findViewById(R.id.profileFacebookButton);
        profileFacebookButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(profileURL));
                startActivity(i);

            }
        });

//        final FloatingActionButton button2018 = (FloatingActionButton) findViewById(R.id.profileYoutube);
//        button2018.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//
//                // Perform action on click
//
//                String url = "http://www.youtube.com";
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
//                startActivity(i);
//
//            }
//        });

//        final Button button2017 = (Button) findViewById(R.id.TimeWarpButton);
//        button2017.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Perform action on click
//
//            }
//        });
//        final Button button2016 = (Button) findViewById(R.id.FuturButton);
//        button2016.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Perform action on click
//
//            }
//        });
//        final Button button2015 = (Button) findViewById(R.id.PrintWorksButton);
//        button2015.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Perform action on click
//
//            }
//        });


    }

    private void PicassoImageLoad(ImageView profilePicture, LinearLayout profileBackground, TextView profileName) {
        Picasso.with(MyAccountActivity.this)
                .load(photoUrl)
                .resize(200, 200)
                .transform(new RoundedTransformation(100, 1))
                .centerCrop()
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        assert profilePicture != null;
                        profilePicture.setImageBitmap(bitmap);
                        Palette.from(bitmap)
                                .generate(new Palette.PaletteAsyncListener() {
                                    @Override
                                    public void onGenerated(Palette palette) {
                                        Palette.Swatch textSwatch = palette.getVibrantSwatch();
                                        if (textSwatch == null) {
                                            //Toast.makeText(MyAccountActivity.this, "Null swatch :(", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        profileBackground.setBackgroundColor(textSwatch.getRgb());
                                        profileName.setTextColor(textSwatch.getTitleTextColor());
                                        // bodyColorText.setTextColor(textSwatch.getBodyTextColor());
                                    }
                                });
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
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

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MyAccountActivity.this, MainActivity.class));
    }

}
