package com.area52.techno.festivals_new.dj;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.area52.techno.R;
import com.area52.techno.activities.MainActivity;
import com.area52.techno.festivals.AwakeningsActivity;
import com.area52.techno.models.DJ;
import com.area52.techno.models.Festival;
import com.area52.techno.youtube.YouTubeActivityPlaylist;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class MyFestivalActivityBranch extends AppCompatActivity {


    TextView ProfileName;
    String photoUrl, FacebookLink, djName, getPhotoUrlDJ, SoundcloudLink, YoutubeLink, youtubePlaylistLink, InstaLink, BookingLink,  country, genre, eventLink, videosLink, ticketsLink;
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]
    private FirebaseFirestore db;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference myRef ;

    ImageView profilePicture, dj_profile_card1_image, dj_profile_card2_image, dj_profile_card3_image, dj_profile_card4_image, dj_profile_card5_image, dj_profile_card6_image, dj_profile_card7_image;
    LinearLayout profileBackground;
    Bundle extras;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_festival);
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
            Intent myIntent = new Intent(MyFestivalActivityBranch.this, MainActivity.class);
            MyFestivalActivityBranch.this.startActivity(myIntent);

        }

        intent = getIntent();
        declareVariables();

    }

    private void firebaseFetch(String dj) {
        // Firebase
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Festival");
        myRef.orderByChild("name").startAt(dj).limitToFirst(1).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                Festival dj = dataSnapshot.getValue(Festival.class);
                // System.out.println(dataSnapshot.getKey() + " was " + dinosaur.height + " meters tall.");

                ProfileName.setText(djName);

                PicassoImageLoad(dj.PhotoUrl);

                FacebookLink = dj.facebookLink;
                SoundcloudLink = dj.soundcloudLink;
                BookingLink = dj.facebookLink;
                YoutubeLink = dj.youtubeLink;
                youtubePlaylistLink = dj.youtubePlaylistLink;
                InstaLink = dj.instagramLink;
                country = dj.country;
                genre = dj.genre;
                ticketsLink = dj.ticketsLink;

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

        // dj_profile_card7_image = (ImageView) findViewById(R.id.dj_profile_card7_image);

        profileBackground = (LinearLayout) findViewById(R.id.profileBackground);
        ProfileName = (TextView) findViewById(R.id.ProfileName);


        db = FirebaseFirestore.getInstance();
        database = FirebaseDatabase.getInstance();

        // Get Firebase User
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        extras = getIntent().getExtras();

        myRef = database.getReference("Festival");

    }

    private void PicassoImageLoad(String photoUrlIn) {

        profilePicture = (ImageView) findViewById(R.id.friendProfilePicture);

        //    Toast.makeText(this, photoUrlIn, Toast.LENGTH_SHORT).show();

//        Picasso.with(MyGroupActivityBranch.this)
//                .load(photoUrlIn)
//                .resize(200, 200)
//                .centerCrop()
//                .into(profilePicture);

        Glide.with(MyFestivalActivityBranch.this)
                .load(photoUrlIn)
                .thumbnail(0.1f)
                .apply(new RequestOptions().override(400, 400)
                        .centerCrop())
                .into(profilePicture);

    }


    private void loadUserButtons() {

        eventLink = ticketsLink;
        videosLink = FacebookLink + "videos";


        // Instagram
        dj_profile_card1_image = (ImageView)  findViewById(R.id.dj_profile_card1_image);
        dj_profile_card1_image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(InstaLink));
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

                if (youtubePlaylistLink.equalsIgnoreCase("none")){

                    // Perform action on click
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(videosLink));
                    startActivity(i);

                }else {

                    Intent i=new Intent(MyFestivalActivityBranch.this, YouTubeActivityPlaylist.class);
                    i.putExtra("playlist", youtubePlaylistLink);
                    i.putExtra("festival", "Awakenings");
                    MyFestivalActivityBranch.this.startActivity(i);

                }



//                // Perform action on click
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(videosLink));
//                startActivity(i);

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

        Picasso.with(MyFestivalActivityBranch.this)
                .load(photoUrl)
                .placeholder(R.drawable.sesh_logo)
                .into(profilePicture);

        Picasso.with(MyFestivalActivityBranch.this)
                .load(photoUrl)
                .placeholder(R.drawable.instagrampng)
                .into(dj_profile_card1_image);

        Picasso.with(MyFestivalActivityBranch.this)
                .load(photoUrl)
                .placeholder(R.drawable.facebookpng)
                .into(dj_profile_card2_image);

        Picasso.with(MyFestivalActivityBranch.this)
                .load(photoUrl)
                .placeholder(R.drawable.sharepng)
                .into(dj_profile_card3_image);

        Picasso.with(MyFestivalActivityBranch.this)
                .load(photoUrl)
                .placeholder(R.drawable.calendarpng)
                .into(dj_profile_card4_image);

        Picasso.with(MyFestivalActivityBranch.this)
                .load(photoUrl)
                .placeholder(R.drawable.youtubepng)
                .into(dj_profile_card5_image);

        Picasso.with(MyFestivalActivityBranch.this)
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
        startActivity(new Intent(MyFestivalActivityBranch.this, MainActivityFestival.class));
    }

}
