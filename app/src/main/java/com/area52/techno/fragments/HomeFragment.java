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

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.area52.techno.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment implements View.OnClickListener, View.OnTouchListener {

    private ImageView img_main_card2_share, img_main_card2_bookmark, img_main_card2_favorite;
    private boolean isBookmarkClicked, isFavoriteClicked, isBookmark1Clicked, isBookmark41Clicked, isBookmark42Clicked, isFavorite41Clicked, isFavorite42Clicked, isShare42Clicked;
    private LinearLayout ll_card_main3_rate;
    private ImageView btn_card_main1_action1, btn_card_main1_action2;
    Intent browserIntent2;

    // Main Card
    private ImageView img_main_card_1, img_main_card1_favorite, img_main_card1_bookmark, img_main_card1_share;
    // SubCard One
    private ImageView img_main_card_41, img_main_card41_favorite, img_main_card41_bookmark, img_main_card41_share;
    // SubCard Two
    private ImageView img_main_card_42, img_main_card42_favorite, img_main_card42_bookmark, img_main_card42_share_1;
    // SubCard Three
    private ImageView img_main_card_43, img_main_card43_favorite, img_main_card43_bookmark, img_main_card43_share;
    // SubCard Four
    private ImageView img_main_card_44, img_main_card44_favorite, img_main_card44_bookmark, img_main_card44_share;


    private CardView card_main_1_1, card_main_1_2, card_main_1_3, card_main_1_4_1, card_main_1_4_2, card_main_1_4_3, card_main_1_4_4;
    private AlphaAnimation alphaAnimation, alphaAnimationShowIcon;

    private CardView card_ad_card;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        NestedScrollView nestedScrollView = (NestedScrollView) inflater.inflate(R.layout.fragment_home, container, false);

        fIndViewObjects(nestedScrollView);
        glideImages();
        card_ad_card = nestedScrollView.findViewById(R.id.card_ad_card);

        return nestedScrollView;
    }

    private void fIndViewObjects(NestedScrollView nestedScrollView) {

        // Main Card
        img_main_card_1 = nestedScrollView.findViewById(R.id.img_main_card_1);
        img_main_card1_favorite = nestedScrollView.findViewById(R.id.img_main_card1_facebook);
        img_main_card1_bookmark = nestedScrollView.findViewById(R.id.img_main_card42_bookmark);
        img_main_card1_share = nestedScrollView.findViewById(R.id.img_main_card1_share);

        // SubCard One
        img_main_card_41 = nestedScrollView.findViewById(R.id.img_main_card_41);
        img_main_card41_favorite = nestedScrollView.findViewById(R.id.img_main_card41_facebook);
        img_main_card41_bookmark = nestedScrollView.findViewById(R.id.img_main_card41_bookmark);
        img_main_card41_share = nestedScrollView.findViewById(R.id.img_main_card41_share);

        // SubCard Two
        img_main_card_42 = nestedScrollView.findViewById(R.id.img_main_card_42);
        img_main_card42_favorite = nestedScrollView.findViewById(R.id.img_main_card42_favorite);
        img_main_card42_bookmark = nestedScrollView.findViewById(R.id.img_main_card1_events);
        img_main_card42_share_1 = nestedScrollView.findViewById(R.id.img_main_card42_share_1);

        // SubCard Three
        img_main_card_43 = nestedScrollView.findViewById(R.id.img_main_card_43);
        img_main_card43_favorite = nestedScrollView.findViewById(R.id.img_main_card43_favorite);
        img_main_card43_bookmark = nestedScrollView.findViewById(R.id.img_main_card43_bookmark);
        img_main_card43_share = nestedScrollView.findViewById(R.id.img_main_card43_share);

        // SubCard Four
        img_main_card_44 = nestedScrollView.findViewById(R.id.img_main_card_44);
        img_main_card44_favorite = nestedScrollView.findViewById(R.id.img_main_card44_favorite);
        img_main_card44_bookmark = nestedScrollView.findViewById(R.id.img_main_card44_bookmark);
        img_main_card44_share = nestedScrollView.findViewById(R.id.img_main_card44_share);



//        img_main_card2_share = nestedScrollView.findViewById(R.id.img_main_card2_share);
//        img_main_card2_bookmark = nestedScrollView.findViewById(R.id.img_main_card2_bookmark);
//        img_main_card2_favorite = nestedScrollView.findViewById(R.id.img_main_card2_favorite);
//        ll_card_main3_rate = nestedScrollView.findViewById(R.id.ll_card_main3_rate);
//
//        card_main_1_1 = nestedScrollView.findViewById(R.id.card_main_1_1);
//        card_main_1_2 = nestedScrollView.findViewById(R.id.card_main_1_2);
//        card_main_1_3 = nestedScrollView.findViewById(R.id.card_main_1_3);
//        card_main_1_4_1 = nestedScrollView.findViewById(R.id.card_main_1_4_1);
//        card_main_1_4_2 = nestedScrollView.findViewById(R.id.card_main_1_4_2);
//        card_main_1_4_3 = nestedScrollView.findViewById(R.id.card_main_1_4_3);
//        card_main_1_4_4 = nestedScrollView.findViewById(R.id.card_main_1_4_4);

    }

    private void glideImages() {

        // Main Card
         Glide.with(getContext()).load(R.drawable.dj999999999).apply(new RequestOptions().fitCenter()).into(img_main_card_1);
        // SubCard One
        Glide.with(getContext()).load(R.drawable.gracey_friel_card2).apply(new RequestOptions().fitCenter()).into(img_main_card_41);
        // Subcard Two
        Glide.with(getContext()).load(R.drawable.argy).apply(new RequestOptions().fitCenter()).into(img_main_card_42);
        // Subcard Three
        Glide.with(getContext()).load(R.drawable.gracey_friel_card2).apply(new RequestOptions().fitCenter()).into(img_main_card_43);
        // Subcard Four
        Glide.with(getContext()).load(R.drawable.argy).apply(new RequestOptions().fitCenter()).into(img_main_card_44);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setOnCLickListeners();

        alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(700);


        alphaAnimationShowIcon = new AlphaAnimation(0.2f, 1.0f);
        alphaAnimationShowIcon.setDuration(500);

    }

    private void setOnCLickListeners() {

    //    img_main_card2_bookmark.setOnClickListener(this);
    //    img_main_card2_favorite.setOnClickListener(this);
    //    img_main_card2_share.setOnClickListener(this);

        img_main_card41_favorite.setOnClickListener(this);
        img_main_card42_favorite.setOnClickListener(this);
        img_main_card1_favorite.setOnClickListener(this);
        img_main_card41_bookmark.setOnClickListener(this);
        img_main_card42_bookmark.setOnClickListener(this);
        img_main_card1_bookmark.setOnClickListener(this);
        img_main_card41_share.setOnClickListener(this);
        img_main_card1_share.setOnClickListener(this);
        img_main_card42_share_1.setOnClickListener(this);

        img_main_card43_favorite.setOnClickListener(this);
        img_main_card44_favorite.setOnClickListener(this);
        img_main_card43_bookmark.setOnClickListener(this);
        img_main_card44_bookmark.setOnClickListener(this);
        img_main_card43_share.setOnClickListener(this);
        img_main_card44_share.setOnClickListener(this);

//        card_main_1_1.setOnClickListener(this);
//        card_main_1_2.setOnClickListener(this);
//        card_main_1_4_1.setOnClickListener(this);
//        card_main_1_4_2.setOnClickListener(this);
//        card_main_1_4_3.setOnClickListener(this);
//        card_main_1_4_4.setOnClickListener(this);

        img_main_card_42.setOnClickListener(this);
        img_main_card_1.setOnClickListener(this);

        img_main_card_41.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {


            case R.id.img_main_card2_bookmark:
                if (!isBookmarkClicked) {
//                    img_main_card2_bookmark.setImageResource(R.drawable.ticket_86171);
//                    img_main_card2_bookmark.startAnimation(alphaAnimationShowIcon);
                    isBookmarkClicked = true;

                } else {
//                    img_main_card2_bookmark.setImageResource(R.drawable.ticket_86171);
//                    img_main_card2_bookmark.startAnimation(alphaAnimationShowIcon);
                    isBookmarkClicked = false;
                }
                break;

            case R.id.img_main_card2_favorite:
                if (!isFavoriteClicked) {
                    isFavoriteClicked = true;
                } else {
                    isFavoriteClicked = false;
                }
                break;

            case R.id.img_main_card42_favorite:
                if (!isFavorite42Clicked) {
                    isFavorite42Clicked = true;
                } else {
                    isFavorite42Clicked = false;
                }
                break;

            case R.id.img_main_card42_bookmark:
                if (!isBookmark42Clicked) {
                    isBookmark42Clicked = true;
                } else {
                    isBookmark42Clicked = false;
                }
                break;

            case R.id.img_main_card2_share:
                Intent sendIntent1 = new Intent();
                sendIntent1.setAction(Intent.ACTION_SEND);
                sendIntent1.putExtra(Intent.EXTRA_TEXT,
                        "Join the SESH: https://play.google.com/store/apps/details?id=com.area52.techno");
                sendIntent1.setType("text/plain");
                startActivity(sendIntent1);
                break;


            case R.id.img_main_card42_share_1:

                Intent sendIntent7 = new Intent();
                sendIntent7.setAction(Intent.ACTION_SEND);
                sendIntent7.putExtra(Intent.EXTRA_TEXT,
                        "Join the SESH: https://play.google.com/store/apps/details?id=com.area52.techno");
                sendIntent7.setType("text/plain");
                startActivity(sendIntent7);
                break;

            case R.id.img_main_card44_share:

                img_main_card44_share.bringToFront();
                Intent sendIntent6 = new Intent();
                sendIntent6.setAction(Intent.ACTION_SEND);
                sendIntent6.putExtra(Intent.EXTRA_TEXT,
                        "Join the SESH: https://play.google.com/store/apps/details?id=com.area52.techno");
                sendIntent6.setType("text/plain");
                startActivity(sendIntent6);
                break;

            case R.id.img_main_card43_share:

                img_main_card44_share.bringToFront();
                Intent sendIntent8 = new Intent();
                sendIntent8.setAction(Intent.ACTION_SEND);
                sendIntent8.putExtra(Intent.EXTRA_TEXT,
                        "Join the SESH: https://play.google.com/store/apps/details?id=com.area52.techno");
                sendIntent8.setType("text/plain");
                startActivity(sendIntent8);
                break;

            case R.id.img_main_card_42:

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://soundcloud.com/dj-argy/argy-uk-live-high-fidelity-003"));
                startActivity(browserIntent);
                break;


            // Main Card

            case R.id.img_main_card_1:

                browserIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://soundcloud.com/paragraph/slamradio-286-999999999?in=999999999music/sets/podcasts-mixes"));
                startActivity(browserIntent2);
                break;


            case R.id.img_main_card1_facebook:
                browserIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/999999999music/"));
                startActivity(browserIntent2);
                break;

            case R.id.img_main_card41_bookmark:
                browserIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/pg/999999999music/events/?ref=page_internal"));
                startActivity(browserIntent2);
                break;

            case R.id.img_main_card1_share:

                Intent sendIntent5 = new Intent();
                sendIntent5.setAction(Intent.ACTION_SEND);
                sendIntent5.putExtra(Intent.EXTRA_TEXT,
                        "Join the SESH: https://play.google.com/store/apps/details?id=com.area52.techno");
                sendIntent5.setType("text/plain");
                startActivity(sendIntent5);
                break;


             ////////////////////////////////////////////////////////////////////////////////////

            // Sub Card One

            case R.id.img_main_card_41:

                browserIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://soundcloud.com/paragraph/slamradio-286-999999999?in=999999999music/sets/podcasts-mixes"));
                startActivity(browserIntent2);
                break;


            case R.id.img_main_card41_facebook:
                browserIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/999999999music/"));
                startActivity(browserIntent2);
                break;

//            case R.id.img_main_card41_bookmark:
//                browserIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/pg/999999999music/events/?ref=page_internal"));
//                startActivity(browserIntent2);
//                break;

//            case R.id.img_main_card41_share:
//
//                Intent sendIntent5 = new Intent();
//                sendIntent5.setAction(Intent.ACTION_SEND);
//                sendIntent5.putExtra(Intent.EXTRA_TEXT,
//                        "Join the SESH: https://play.google.com/store/apps/details?id=com.area52.techno");
//                sendIntent5.setType("text/plain");
//                startActivity(sendIntent5);
//                break;


            ////////////////////////////////////////////////////////////////////////////////////

//            case R.id.ll_card_main3_rate:
//                break;

            case R.id.card_main_1_1:
                break;

            case R.id.card_main_1_2:
                break;

            case R.id.card_main_1_3:
                break;

            case R.id.card_main_1_4_1:
                break;

            case R.id.card_main_1_4_2:
                break;

            case R.id.card_main_1_4_3:
                break;

            case R.id.card_main_1_4_4:
                break;
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
        try {
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("app", MODE_PRIVATE);
            if (!sharedPreferences.getBoolean("isDonated", false)) {


                Animation animation = new AlphaAnimation(0.0f, 1.0f);
                animation.setDuration(500);
                card_ad_card.setVisibility(View.VISIBLE);
                card_ad_card.startAnimation(animation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

