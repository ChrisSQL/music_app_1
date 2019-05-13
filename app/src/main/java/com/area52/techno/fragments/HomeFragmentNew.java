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
import android.support.v7.widget.CardView;
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
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.area52.techno.R;
import com.area52.techno.dj.DJRecyclerAdapter;
import com.area52.techno.dj.DJRecyclerAdapterHome;
import com.area52.techno.dj.MainActivityDJ;
import com.area52.techno.models.DJ;
import com.area52.techno.models.User;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.branch.referral.Branch;
import io.branch.referral.BranchError;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.accountkit.internal.AccountKitController.getApplicationContext;

public class HomeFragmentNew extends Fragment implements View.OnClickListener, View.OnTouchListener {

    DatabaseReference myRef ;
    List<DJ> listDJs;
    List<User> list;
    RecyclerView recycle;
    Button view;

    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager mLayoutManager;
    private Boolean bool;
    Branch branch;
    String dj;
    private LinearLayoutManager mManager;
    Bundle extras;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        NestedScrollView nestedScrollView = (NestedScrollView) inflater.inflate(R.layout.activity_dj3, container, false);
        recyclerView = (RecyclerView) nestedScrollView.findViewById(R.id.recycleDJ3);

        SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String dj = pref.getString("dj", "empty");

      //    recyclerView.setLayoutManager(mManager);

        if (recyclerView!=null){
            recyclerView.setHasFixedSize(true);
        }

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("DJ");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                listDJs = new ArrayList<DJ>();
                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    DJ value = dataSnapshot1.getValue(DJ.class);
                    DJ dj = new DJ();

//                    Toast.makeText(MainActivityDJ.this, value.toString(), Toast.LENGTH_SHORT).show();

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

                    listDJs.add(dj);

//                    Toast.makeText(MainActivityDJ.this, listDJs.toString(), Toast.LENGTH_SHORT).show();

                }

                DJRecyclerAdapterHome djRecyclerAdapter = new DJRecyclerAdapterHome(listDJs,getContext());
                //    RecyclerView.LayoutManager recyce = new GridLayoutManager(MainActivityUser.this,2);
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
                recyclerView.setLayoutManager(manager);
                /// RecyclerView.LayoutManager recyce = new LinearLayoutManager(MainActivity.this);
                // recycle.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
                // recycle.setLayoutManager(recyce);
                recyclerView.setItemAnimator( new DefaultItemAnimator());
                recyclerView.setAdapter(djRecyclerAdapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Hello", "Failed to read value.", error.toException());
            }
        });

            return nestedScrollView;
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

}

