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

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.area52.techno.R;
import com.area52.techno.activities.EventsActivity;
import com.area52.techno.activities.MainActivity;
import com.area52.techno.adapters.AlbumAdapter;
import com.area52.techno.dataloaders.AlbumLoader;
import com.area52.techno.models.Album;
import com.area52.techno.utils.PreferencesUtility;
import com.area52.techno.utils.SortOrder;
import com.area52.techno.widgets.BaseRecyclerView;
import com.area52.techno.widgets.DividerItemDecoration;
import com.area52.techno.widgets.FastScroller;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment implements View.OnClickListener, View.OnTouchListener {

    private ImageView img_main_card2_share, img_main_card2_bookmark, img_main_card2_favorite;
    private boolean isBookmarkClicked, isFavoriteClicked, isBookmark41Clicked, isBookmark42Clicked, isFavorite41Clicked, isFavorite42Clicked, isShare42Clicked;
    private LinearLayout ll_card_main3_rate;
    private ImageView btn_card_main1_action1, btn_card_main1_action2;
    private ImageView img_main_card_1, img_main_card_2, img_card_main_3, img_main_card_41, img_main_card_42, img_main_card_43, img_main_card_44,
            img_main_card41_favorite, img_main_card42_favorite, img_main_card41_bookmark, img_main_card42_bookmark,
            img_main_card41_share, img_main_card42_share,
            img_main_card43_favorite, img_main_card44_favorite, img_main_card43_bookmark, img_main_card44_bookmark,
            img_main_card43_share, img_main_card44_share;
    private CardView card_main_1_1, card_main_1_2, card_main_1_3, card_main_1_4_1, card_main_1_4_2, card_main_1_4_3, card_main_1_4_4;
    private AlphaAnimation alphaAnimation, alphaAnimationShowIcon;

    private CardView card_ad_card;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        NestedScrollView nestedScrollView = (NestedScrollView) inflater.inflate(R.layout.fragment_home, container, false);


//        btn_card_main1_action1 = nestedScrollView.findViewById(R.uID.btn_card_main1_action1);
//        btn_card_main1_action2 = nestedScrollView.findViewById(R.uID.btn_card_main1_action2);
        img_main_card2_share = nestedScrollView.findViewById(R.id.img_main_card2_share);
        img_main_card2_bookmark = nestedScrollView.findViewById(R.id.img_main_card2_bookmark);
        img_main_card2_favorite = nestedScrollView.findViewById(R.id.img_main_card2_favorite);
        ll_card_main3_rate = nestedScrollView.findViewById(R.id.ll_card_main3_rate);

        img_main_card_1 = nestedScrollView.findViewById(R.id.img_main_card_1);
        img_main_card_2 = nestedScrollView.findViewById(R.id.img_main_card_2);
    //    img_card_main_3 = nestedScrollView.findViewById(R.uID.img_card_main_3);
        img_main_card_41 = nestedScrollView.findViewById(R.id.img_main_card_41);
        img_main_card_42 = nestedScrollView.findViewById(R.id.img_main_card_42);
        img_main_card_43 = nestedScrollView.findViewById(R.id.img_main_card_43);
        img_main_card_44 = nestedScrollView.findViewById(R.id.img_main_card_44);

        img_main_card41_favorite = nestedScrollView.findViewById(R.id.img_main_card41_favorite);
        img_main_card42_favorite = nestedScrollView.findViewById(R.id.img_main_card42_favorite);
        img_main_card41_bookmark = nestedScrollView.findViewById(R.id.img_main_card41_bookmark);
        img_main_card42_bookmark = nestedScrollView.findViewById(R.id.img_main_card42_bookmark);
        img_main_card41_share = nestedScrollView.findViewById(R.id.img_main_card41_share);
        img_main_card42_share = nestedScrollView.findViewById(R.id.img_main_card42_share);

        img_main_card43_favorite = nestedScrollView.findViewById(R.id.img_main_card43_favorite);
        img_main_card44_favorite = nestedScrollView.findViewById(R.id.img_main_card44_favorite);
        img_main_card43_bookmark = nestedScrollView.findViewById(R.id.img_main_card43_bookmark);
        img_main_card44_bookmark = nestedScrollView.findViewById(R.id.img_main_card44_bookmark);
        img_main_card43_share = nestedScrollView.findViewById(R.id.img_main_card43_share);
        img_main_card44_share = nestedScrollView.findViewById(R.id.img_main_card44_share);

        card_main_1_1 = nestedScrollView.findViewById(R.id.card_main_1_1);
        card_main_1_2 = nestedScrollView.findViewById(R.id.card_main_1_2);
        card_main_1_3 = nestedScrollView.findViewById(R.id.card_main_1_3);
        card_main_1_4_1 = nestedScrollView.findViewById(R.id.card_main_1_4_1);
        card_main_1_4_2 = nestedScrollView.findViewById(R.id.card_main_1_4_2);
        card_main_1_4_3 = nestedScrollView.findViewById(R.id.card_main_1_4_3);
        card_main_1_4_4 = nestedScrollView.findViewById(R.id.card_main_1_4_4);

        Glide.with(getContext()).load(R.drawable.sunil_card).apply(new RequestOptions().fitCenter()).into(img_main_card_1);
        Glide.with(getContext()).load(R.drawable.material_design_4).apply(new RequestOptions().fitCenter()).into(img_main_card_2);
    //    Glide.with(getContext()).load(R.drawable.gracey_friel_card).apply(new RequestOptions().fitCenter()).into(img_card_main_3);
        Glide.with(getContext()).load(R.drawable.gracey_friel_card2).apply(new RequestOptions().fitCenter()).into(img_main_card_41);
        Glide.with(getContext()).load(R.drawable.argy).apply(new RequestOptions().fitCenter()).into(img_main_card_42);
        Glide.with(getContext()).load(R.drawable.awakenings_white_square).apply(new RequestOptions().fitCenter()).into(img_main_card_43);
        Glide.with(getContext()).load(R.drawable.lumo_square).apply(new RequestOptions().fitCenter()).into(img_main_card_44);



        card_ad_card = nestedScrollView.findViewById(R.id.card_ad_card);

        return nestedScrollView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        btn_card_main1_action1.setOnClickListener(this);
//        btn_card_main1_action2.setOnClickListener(this);
        img_main_card2_bookmark.setOnClickListener(this);
        img_main_card2_favorite.setOnClickListener(this);
        img_main_card2_share.setOnClickListener(this);
     //   ll_card_main3_rate.setOnClickListener(this);

        img_main_card41_favorite.setOnClickListener(this);
        img_main_card42_favorite.setOnClickListener(this);
        img_main_card41_bookmark.setOnClickListener(this);
        img_main_card42_bookmark.setOnClickListener(this);
        img_main_card41_share.setOnClickListener(this);
        img_main_card42_share.setOnClickListener(this);

        img_main_card43_favorite.setOnClickListener(this);
        img_main_card44_favorite.setOnClickListener(this);
        img_main_card43_bookmark.setOnClickListener(this);
        img_main_card44_bookmark.setOnClickListener(this);
        img_main_card43_share.setOnClickListener(this);
        img_main_card44_share.setOnClickListener(this);

        card_main_1_1.setOnClickListener(this);
        card_main_1_2.setOnClickListener(this);
    //    card_main_1_3.setOnClickListener(this);
        card_main_1_4_1.setOnClickListener(this);
        card_main_1_4_2.setOnClickListener(this);
        card_main_1_4_3.setOnClickListener(this);
        card_main_1_4_4.setOnClickListener(this);

        card_main_1_1.setOnTouchListener(this);
        card_main_1_2.setOnTouchListener(this);
   //     card_main_1_3.setOnTouchListener(this);
        card_main_1_4_1.setOnTouchListener(this);
        card_main_1_4_2.setOnTouchListener(this);
        card_main_1_4_3.setOnTouchListener(this);
        card_main_1_4_4.setOnTouchListener(this);

        alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(700);
        img_main_card_1.startAnimation(alphaAnimation);
        img_main_card_2.startAnimation(alphaAnimation);

        alphaAnimationShowIcon = new AlphaAnimation(0.2f, 1.0f);
        alphaAnimationShowIcon.setDuration(500);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

//            case R.uID.btn_card_main1_action1:
////                Snackbar.make(view, getString(R.string.main_flat_button_1_clicked), Snackbar.LENGTH_SHORT).show();
////                break;
////
////            case R.uID.btn_card_main1_action2:
////                Snackbar.make(view, getString(R.string.main_flat_button_2_clicked), Snackbar.LENGTH_SHORT).show();
////                break;

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
//                    img_main_card2_favorite.setImageResource(R.drawable.facebook48);
//                    img_main_card2_favorite.startAnimation(alphaAnimationShowIcon);
//                    img_main_card2_favorite.startAnimation(alphaAnimationShowIcon);
                    isFavoriteClicked = true;
                } else {
//                    img_main_card2_favorite.setImageResource(R.drawable.facebook48);
//                    img_main_card2_favorite.startAnimation(alphaAnimationShowIcon);
//                    img_main_card2_favorite.startAnimation(alphaAnimationShowIcon);
                    isFavoriteClicked = false;
                }
                break;

            case R.id.img_main_card41_favorite:
                if (!isFavorite41Clicked) {
//                    img_main_card41_favorite.setImageResource(R.drawable.facebook48);
//                    img_main_card41_favorite.startAnimation(alphaAnimationShowIcon);
                    isFavorite41Clicked = true;
                } else {
//                    img_main_card41_favorite.setImageResource(R.drawable.facebook48);
//                    img_main_card41_favorite.startAnimation(alphaAnimationShowIcon);
                    isFavorite41Clicked = false;
                }
                break;

            case R.id.img_main_card42_favorite:
                if (!isFavorite42Clicked) {
//                    img_main_card42_favorite.setImageResource(R.drawable.facebook48);
//                    img_main_card42_favorite.startAnimation(alphaAnimationShowIcon);
                    isFavorite42Clicked = true;
                } else {
//                    img_main_card42_favorite.setImageResource(R.drawable.facebook48);
//                    img_main_card42_favorite.startAnimation(alphaAnimationShowIcon);
                    isFavorite42Clicked = false;
                }
                break;

            case R.id.img_main_card41_bookmark:
                if (!isShare42Clicked) {
//                    img_main_card41_bookmark.setImageResource(R.drawable.ticket_86171);
//                    img_main_card41_bookmark.startAnimation(alphaAnimationShowIcon);
                    isShare42Clicked = true;
                } else {
//                    img_main_card41_bookmark.setImageResource(R.drawable.ticket_86171);
//                    img_main_card41_bookmark.startAnimation(alphaAnimationShowIcon);
                    isShare42Clicked = false;
                }
                break;

            case R.id.img_main_card42_bookmark:
                if (!isBookmark42Clicked) {
//                    img_main_card42_bookmark.setImageResource(R.drawable.ticket_86171);
//                    img_main_card42_bookmark.startAnimation(alphaAnimationShowIcon);
                    isBookmark42Clicked = true;
                } else {
//                    img_main_card42_bookmark.setImageResource(R.drawable.ticket_86171);
//                    img_main_card42_bookmark.startAnimation(alphaAnimationShowIcon);
                    isBookmark42Clicked = false;
                }
                break;

            case R.id.img_main_card2_share:
                break;

            case R.id.img_main_card41_share:
                break;

            case R.id.img_main_card42_share:
                if (!isBookmark42Clicked) {
//                    img_main_card42_share.setImageResource(R.drawable.share_48);
//                    img_main_card42_share.startAnimation(alphaAnimationShowIcon);

                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT,
                            "Join the SESH: https://play.google.com/store/apps/details?uID=com.area52.techno");
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);

                    isBookmark42Clicked = true;
                } else {
//                    img_main_card42_share.setImageResource(R.drawable.share_48);
//                    img_main_card42_share.startAnimation(alphaAnimationShowIcon);
                    isBookmark42Clicked = false;
                }
                break;

//            case R.uID.ll_card_main3_rate:
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

