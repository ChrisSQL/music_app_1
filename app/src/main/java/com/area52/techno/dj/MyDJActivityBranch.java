package com.area52.techno.dj;


import android.content.Intent;
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

import com.area52.techno.FacebookActivityFirebase;
import com.area52.techno.R;
import com.area52.techno.activities.MainActivity;
import com.area52.techno.models.DJ;
import com.area52.techno.models.User;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyDJActivityBranch extends AppCompatActivity {


    TextView ProfileName;
    String photoUrl, FacebookLink, djName, getPhotoUrlDJ, SoundcloudLink, YoutubeLink, BookingLink,  country, genre, eventLink, videosLink;
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]
    private FirebaseFirestore db;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference myRef ;

    ImageView profilePicture, dj_profile_card1_image, dj_profile_card2_image, dj_profile_card3_image, dj_profile_card4_image, dj_profile_card5_image, dj_profile_card6_image;
    LinearLayout profileBackground;
    Bundle extras;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dj);
        profilePicture = (ImageView) findViewById(R.id.friendProfilePicture);

        // If
        extras = getIntent().getExtras();

        if (extras != null) {

        // Local
        djName = extras.getString("djName");
        firebaseFetch(djName);

        }else {

        // Go back to Main
            //
         Intent myIntent = new Intent(MyDJActivityBranch.this, MainActivity.class);
         MyDJActivityBranch.this.startActivity(myIntent);

        }

        intent = getIntent();
        declareVariables();

    }

    private void firebaseFetch(String dj) {
        // Firebase
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("DJ");
        myRef.orderByChild("name").startAt(dj).limitToFirst(1).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                DJ dj = dataSnapshot.getValue(DJ.class);
                // System.out.println(dataSnapshot.getKey() + " was " + dinosaur.height + " meters tall.");

                ProfileName.setText(djName);

                PicassoImageLoad(dj.PhotoUrl);

                FacebookLink = dj.facebookLink;
                SoundcloudLink = dj.soundcloudLink;
                BookingLink = dj.facebookLink;
                YoutubeLink = dj.youtubeLink;
                country = dj.country;
                genre = dj.genre;

                loadUserButtons();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

            // ...
        });
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

        myRef = database.getReference("DJ");

    }

    private void PicassoImageLoad(String photoUrlIn) {

        profilePicture = (ImageView) findViewById(R.id.friendProfilePicture);

    //    Toast.makeText(this, photoUrlIn, Toast.LENGTH_SHORT).show();

//        Picasso.with(MyDJActivityBranch.this)
//                .load(photoUrlIn)
//                .resize(200, 200)
//                .centerCrop()
//                .into(profilePicture);

        Glide.with(MyDJActivityBranch.this)
                .load(photoUrlIn)
                .thumbnail(0.1f)
                .apply(new RequestOptions().override(400, 400)
                .centerCrop())
                .into(profilePicture);

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

                String shareLink = "https://seshmusic.ie/" + djName.replaceAll(" ", "_").toLowerCase();

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                //shareIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.area52.techno&hl=en");
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareLink);
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

        Picasso.with(MyDJActivityBranch.this)
                .load(photoUrl)
                .placeholder(R.drawable.sesh_logo)
                .into(profilePicture);

        Picasso.with(MyDJActivityBranch.this)
                .load(photoUrl)
                .placeholder(R.drawable.informationpng)
                .into(dj_profile_card1_image);

        Picasso.with(MyDJActivityBranch.this)
                .load(photoUrl)
                .placeholder(R.drawable.facebookpng)
                .into(dj_profile_card2_image);

        Picasso.with(MyDJActivityBranch.this)
                .load(photoUrl)
                .placeholder(R.drawable.sharepng)
                .into(dj_profile_card3_image);

        Picasso.with(MyDJActivityBranch.this)
                .load(photoUrl)
                .placeholder(R.drawable.calendarpng)
                .into(dj_profile_card4_image);

        Picasso.with(MyDJActivityBranch.this)
                .load(photoUrl)
                .placeholder(R.drawable.youtubepng)
                .into(dj_profile_card5_image);

        Picasso.with(MyDJActivityBranch.this)
                .load(photoUrl)
                .placeholder(R.drawable.soundcloudpng)
                .into(dj_profile_card6_image);

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
        startActivity(new Intent(MyDJActivityBranch.this, MainActivity.class));
    }

}
