/*
 * Copyright (C) 2015 Naman Dwivedi
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 */

package com.area52.techno.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.area52.techno.R;
import com.area52.techno.activities.MainActivity;
import com.area52.techno.dj.DJRecyclerAdapterHome;
import com.area52.techno.dj.MainActivityDJ;
import com.area52.techno.dj.MyDJActivity;
import com.area52.techno.models.DJ;
import com.area52.techno.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.branch.referral.Branch;

public class HomeFragmentDJ extends Fragment implements View.OnClickListener, View.OnTouchListener {

    DatabaseReference myRef, myRefUser ;
    List<DJ> listDJs;
    List<User> list;
    RecyclerView recycle;
    Button view;

    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager mLayoutManager;
    private Boolean bool;
    Branch branch;
    String dj, djBranch, djIntent;
    private LinearLayoutManager mManager;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();

    TextView ProfileName, dj_profile_card1_name;
    String photoUrl, FacebookLink, djName, getPhotoUrlDJ, SoundcloudLink, YoutubeLink, BookingLink,  country, genre, eventLink, videosLink, djRef;
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]
    private FirebaseFirestore db;
    FirebaseUser user;

    ImageView profilePicture, dj_profile_card1_image, dj_profile_card2_image, dj_profile_card3_image, dj_profile_card4_image, dj_profile_card5_image, dj_profile_card6_image;
    LinearLayout profileBackground;
    Bundle extras;
    Query userQuery;
    User userProfile;
    Intent intent;
    ImageButton facebook_profile_button, youtube_profile_button, bookings_profile_button, soundcloud_profile_button;
    Context c;
    NestedScrollView nestedScrollView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        c = getActivity().getApplicationContext();
        // this = your fragment
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        djBranch = sharedPreferences.getString("djReferral", "Perc");
        firebaseFetch(djBranch);

        listDJs = new ArrayList<DJ>();

    //    Toast.makeText(getContext(), djBranch, Toast.LENGTH_SHORT).show();

        nestedScrollView = (NestedScrollView) inflater.inflate(R.layout.activity_dj4, container, false);

    //    recyclerView = (RecyclerView) nestedScrollView.findViewById(R.id.recycleDJ3);


        // Declare Variables ////////////////////////

        profilePicture = (ImageView) nestedScrollView.findViewById(R.id.friendProfilePicture);
        dj_profile_card1_image = (ImageView) nestedScrollView.findViewById(R.id.dj_profile_card1_image);
        dj_profile_card2_image = (ImageView) nestedScrollView.findViewById(R.id.dj_profile_card2_image);
        dj_profile_card3_image = (ImageView) nestedScrollView.findViewById(R.id.dj_profile_card3_image);
        dj_profile_card4_image = (ImageView) nestedScrollView.findViewById(R.id.dj_profile_card4_image);
        dj_profile_card5_image = (ImageView) nestedScrollView.findViewById(R.id.dj_profile_card5_image);
        dj_profile_card6_image = (ImageView) nestedScrollView.findViewById(R.id.dj_profile_card6_image);
        profileBackground = (LinearLayout) nestedScrollView.findViewById(R.id.profileBackground);
        ProfileName = (TextView) nestedScrollView.findViewById(R.id.ProfileName);
        dj_profile_card1_name = (TextView) nestedScrollView.findViewById(R.id.dj_profile_card1_name);
        dj_profile_card1_name.setText("Bio");

        // Biography
        dj_profile_card1_image = (ImageView)  nestedScrollView.findViewById(R.id.dj_profile_card1_image);
        dj_profile_card1_image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(FacebookLink));
                startActivity(i);

            }
        });

        // Facebook
        dj_profile_card2_image = (ImageView)  nestedScrollView.findViewById(R.id.dj_profile_card2_image);
        dj_profile_card2_image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(FacebookLink));
                startActivity(i);

            }
        });

        // Share
        dj_profile_card3_image = (ImageView)  nestedScrollView.findViewById(R.id.dj_profile_card3_image);
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
        dj_profile_card4_image = (ImageView)  nestedScrollView.findViewById(R.id.dj_profile_card4_image);
        dj_profile_card4_image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(eventLink));
                startActivity(i);

            }
        });

        // Youtube
        dj_profile_card5_image = (ImageView)  nestedScrollView.findViewById(R.id.dj_profile_card5_image);
        dj_profile_card5_image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(videosLink));
                startActivity(i);

            }
        });

        // Soundcloud
        dj_profile_card6_image = (ImageView)  nestedScrollView.findViewById(R.id.dj_profile_card6_image);
        dj_profile_card6_image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(SoundcloudLink));
                startActivity(i);

            }
        });

        Picasso.with(c)
                .load(photoUrl)
                .placeholder(R.drawable.sesh_logo)
                .into(profilePicture);

        Picasso.with(c)
                .load(photoUrl)
                .placeholder(R.drawable.informationpng)
                .into(dj_profile_card1_image);

        Picasso.with(c)
                .load(photoUrl)
                .placeholder(R.drawable.facebookpng)
                .into(dj_profile_card2_image);

        Picasso.with(c)
                .load(photoUrl)
                .placeholder(R.drawable.sharepng)
                .into(dj_profile_card3_image);

        Picasso.with(c)
                .load(photoUrl)
                .placeholder(R.drawable.calendarpng)
                .into(dj_profile_card4_image);

        Picasso.with(c)
                .load(photoUrl)
                .placeholder(R.drawable.youtubepng)
                .into(dj_profile_card5_image);

        Picasso.with(c)
                .load(photoUrl)
                .placeholder(R.drawable.soundcloudpng)
                .into(dj_profile_card6_image);

        db = FirebaseFirestore.getInstance();
        database = FirebaseDatabase.getInstance();

        // Get Firebase User
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        myRef = database.getReference("DJ");
        myRefUser = database.getReference("usersUsername");

        //////////////////////////////////////////////



      // Toast.makeText(getContext(), djBranch, Toast.LENGTH_SHORT).show();

      // recyclerView.setLayoutManager(mManager);

//        if (recyclerView!=null){
//            recyclerView.setHasFixedSize(true);
//        }

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("DJ");
        myRefUser = database.getReference("usersUsername");

        myRefUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                // DJ One
                djSingle(dataSnapshot, "Sarah Mooney"); // Hannah Wants if not Brancdj

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Hello", "Failed to read value.", error.toException());
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                // DJ One

                djSingle(dataSnapshot, "Hannah Wants"); // Hannah Wants if not Brancdj

                djAll(dataSnapshot, djBranch);

                DJRecyclerAdapterHome djRecyclerAdapter = new DJRecyclerAdapterHome(listDJs,getContext());
                //    RecyclerView.LayoutManager recyce = new GridLayoutManager(MainActivityClub.this,2);
                GridLayoutManager manager = new GridLayoutManager(getContext(), 12, GridLayoutManager.VERTICAL, false);
                manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        // 7 is the sum of items in one repeated section
                        switch (position % 7) {
                            // first three items span 3 columns each
                            case 0:
                            case 1:
                            case 2:
                                return 4;
                            // next four items span 2 columns each

                            case 3:
                            case 4:
                            case 5:
                            case 6:
                                return 4;
                        }
                        throw new IllegalStateException("internal error");
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Hello", "Failed to read value.", error.toException());
            }
        });



            return nestedScrollView;
    }

    private void firebaseFetch(String dj) {

        // Firebase
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("DJ");
        myRef.orderByChild("name").startAt(djBranch).limitToFirst(1).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                DJ djIn = dataSnapshot.getValue(DJ.class);

                ProfileName.setText(djIn.getName());

//                 System.out.println(dataSnapshot.getKey() + " was " + djIn.name + " meters tall.");
//                 Toast.makeText(c, dataSnapshot.getKey() + " was " + djIn.name + " name.", Toast.LENGTH_LONG).show();

                eventLink = djIn.getFacebookLink() + "events";
                videosLink = djIn.getFacebookLink() + "videos";

                // Biography
                dj_profile_card1_image = (ImageView)  nestedScrollView.findViewById(R.id.dj_profile_card1_image);
                dj_profile_card1_image.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // Perform action on click

                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(djIn.getFacebookLink()));
                        startActivity(i);

                    }
                });

                // Facebook
                dj_profile_card2_image = (ImageView)  nestedScrollView.findViewById(R.id.dj_profile_card2_image);
                dj_profile_card2_image.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // Perform action on click

                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(djIn.getFacebookLink()));
                        startActivity(i);

                    }
                });

                // Share
                dj_profile_card3_image = (ImageView)  nestedScrollView.findViewById(R.id.dj_profile_card3_image);
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
                dj_profile_card4_image = (ImageView)  nestedScrollView.findViewById(R.id.dj_profile_card4_image);
                dj_profile_card4_image.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // Perform action on click

                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(eventLink));
                        startActivity(i);

                    }
                });

                // Youtube
                dj_profile_card5_image = (ImageView)  nestedScrollView.findViewById(R.id.dj_profile_card5_image);
                dj_profile_card5_image.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // Perform action on click

                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(videosLink));
                        startActivity(i);

                    }
                });

                // Soundcloud
                dj_profile_card6_image = (ImageView)  nestedScrollView.findViewById(R.id.dj_profile_card6_image);
                dj_profile_card6_image.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // Perform action on click

                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(djIn.getSoundcloudLink()));
                        startActivity(i);

                    }
                });

                Picasso.with(c)
                        .load(djIn.PhotoUrl)
                        .placeholder(R.drawable.sesh_logo)
                        .into(profilePicture);

                Picasso.with(c)
                        .load(photoUrl)
                        .placeholder(R.drawable.informationpng)
                        .into(dj_profile_card1_image);

                Picasso.with(c)
                        .load(photoUrl)
                        .placeholder(R.drawable.facebookpng)
                        .into(dj_profile_card2_image);

                Picasso.with(c)
                        .load(photoUrl)
                        .placeholder(R.drawable.sharepng)
                        .into(dj_profile_card3_image);

                Picasso.with(c)
                        .load(photoUrl)
                        .placeholder(R.drawable.calendarpng)
                        .into(dj_profile_card4_image);

                Picasso.with(c)
                        .load(photoUrl)
                        .placeholder(R.drawable.youtubepng)
                        .into(dj_profile_card5_image);

                Picasso.with(c)
                        .load(photoUrl)
                        .placeholder(R.drawable.soundcloudpng)
                        .into(dj_profile_card6_image);

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

    private void populateUser(String userIDIn) {

        Query myTopPostsQuery = myRef.child(userIDIn);

        myTopPostsQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);

                djRef = user.getRefDJ();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //  fail("Failed to get country: " + databaseError.getMessage());

            }
        });

    }

    private void djAll(DataSnapshot dataSnapshot, String djIn) {
        for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

            DJ value = dataSnapshot1.getValue(DJ.class);
            DJ dj = new DJ();

            dj.setuID(value.getuID());
            dj.setName(value.getName());
            dj.setEmail(value.getEmail());
            dj.setPhoto(value.getPhotoUrlDJ());
            dj.setFacebookUserID(value.getFacebookUserID());
            dj.setDjLogoImage(value.getDjLogoImage());
            dj.setCounty(value.getCounty());
            dj.setCountry(value.getCountry());
            dj.setPhoneNumber(value.getPhoneNumber());
            dj.setBio(value.getBio());
            dj.setSoundcloudLink(value.getSoundcloudLink());
            dj.setYoutubeLink(value.getYoutubeLink());
            dj.setMixcloudLink(value.getMixcloudLink());
            dj.setFacebookLink(value.getFacebookLink());
            dj.setInstagramLink(value.getInstagramLink());
            dj.setSpotifyLink(value.getSpotifyLink());
            dj.setBookingEmail(value.getBookingEmail());
            dj.setGenre(value.getGenre());

            if(!djIn.equalsIgnoreCase(value.getName())){
                listDJs.add(dj);
            }



        }
    }

    private void djSingle(DataSnapshot dataSnapshot, String djIn) {
        for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

            DJ value = dataSnapshot1.getValue(DJ.class);
            DJ dj = new DJ();



            dj.setuID(value.getuID());
            dj.setName(value.getName());
            dj.setEmail(value.getEmail());
            dj.setPhoto(value.getPhotoUrlDJ());
            dj.setFacebookUserID(value.getFacebookUserID());
            dj.setDjLogoImage(value.getDjLogoImage());
            dj.setCounty(value.getCounty());
            dj.setCountry(value.getCountry());
            dj.setPhoneNumber(value.getPhoneNumber());
            dj.setBio(value.getBio());
            dj.setSoundcloudLink(value.getSoundcloudLink());
            dj.setYoutubeLink(value.getYoutubeLink());
            dj.setMixcloudLink(value.getMixcloudLink());
            dj.setFacebookLink(value.getFacebookLink());
            dj.setInstagramLink(value.getInstagramLink());
            dj.setSpotifyLink(value.getSpotifyLink());
            dj.setBookingEmail(value.getBookingEmail());
            dj.setGenre(value.getGenre());

//           if(djIn.equalsIgnoreCase(value.getName())){
//               listDJs.add(dj);
//           //    Toast.makeText(getContext(), djIn, Toast.LENGTH_SHORT).show();
//           }

//            Toast.makeText(c, value.getPhotoUrlDJ(), Toast.LENGTH_SHORT).show();

//            if(djIn.equalsIgnoreCase("Hannah Wants")){
//                Picasso.with(c)
//                        .load(value.getPhotoUrlDJ())
//                        .placeholder(R.drawable.sesh_logo)
//                        .into(profilePicture);
//            }




        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {


        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                ObjectAnimator upAnim = ObjectAnimator.ofFloat(view, "translationZ", 16);
//                upAnim.setDuration(150);
//                upAnim.setInterpolator(new DecelerateInterpolator());
//                upAnim.start();
//                break;
//
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_CANCEL:
//                ObjectAnimator downAnim = ObjectAnimator.ofFloat(view, "translationZ", 0);
//                downAnim.setDuration(150);
//                downAnim.setInterpolator(new AccelerateInterpolator());
//                downAnim.start();
//                break;
        }
        return false;
    }

    public void showAd() {

    }

    private ArrayList<DJ> moveItemToTop(ArrayList<DJ> lists, int positionOfItem) {
        if (lists == null || positionOfItem < 0 || positionOfItem >= lists.size()) {
            return lists;
        }

        ArrayList<DJ> sortedList = new ArrayList<DJ>();
        //add the item to the top
        sortedList.add(lists.get(positionOfItem));

        for (int i=0; i<lists.size(); i++) {
            if (i != positionOfItem) {
                sortedList.add(lists.get(i));
            }
        }

        return sortedList;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }



}

