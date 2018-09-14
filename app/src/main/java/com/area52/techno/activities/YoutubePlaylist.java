package com.area52.techno.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.area52.techno.R;
import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.VideoListResponse;
import com.prof.youtubeparser.models.videos.Video;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



import java.io.IOException;
import java.util.List;

public class YoutubePlaylist extends AppCompatActivity implements YouTubeThumbnailView.OnInitializedListener, YouTubeThumbnailLoader.OnThumbnailLoadedListener, YouTubePlayer.OnInitializedListener {



    YouTubeThumbnailView thumbnailView;
    YouTubeThumbnailLoader thumbnailLoader;
    RecyclerView VideoList;
    RecyclerView.Adapter adapter;
    List<Drawable> thumbnailViews;
    List<String> VideoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube_playlist_layout);
        thumbnailViews = new ArrayList<>();
        VideoList = (RecyclerView) findViewById(R.id.VideoListPlaylist);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        VideoList.setLayoutManager(layoutManager);
        adapter=new VideoListAdapter();
        VideoList.setAdapter(adapter);
        VideoId = new ArrayList<>();
        thumbnailView = new YouTubeThumbnailView(this);
        thumbnailView.initialize("AIzaSyBqirS7s6MeKSZQPzWT9lvD5RjacP1pqK4", this);
        //playerFragment = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.VideoFragmentPlaylist);
        //playerFragment.initialize("AIzaSyBqirS7s6MeKSZQPzWT9lvD5RjacP1pqK4", this);


    }




    @Override
    public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
        thumbnailLoader = youTubeThumbnailLoader;
        youTubeThumbnailLoader.setOnThumbnailLoadedListener(YoutubePlaylist.this);
        thumbnailLoader.setPlaylist("PLmh4GKNPRUM9cHFOUAXcWZwFNo8t9fLAM");
    }

    @Override
    public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

    }

    public void add() {
        adapter.notifyDataSetChanged();
        if (thumbnailLoader.hasNext())
            thumbnailLoader.next();
    }

    @Override
    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
        thumbnailViews.add(youTubeThumbnailView.getDrawable());
        VideoId.add(s);
        add();
    }

    @Override
    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//        Player=youTubePlayer;
//        Player.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {
//            @Override
//            public void onFullscreen(boolean b) {
//                VideoList.setVisibility(b?View.GONE:View.VISIBLE);
//            }
//        });
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.MyView>{

        public class MyView extends RecyclerView.ViewHolder{

            ImageView imageView;
            protected TextView videosTitleTextView;
            public MyView(View itemView) {
                super(itemView);
                imageView= (ImageView) itemView.findViewById(R.id.thumbnailView);
                videosTitleTextView = (TextView) itemView.findViewById(R.id.title);
            }

        }

        @Override
        public VideoListAdapter.MyView onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_row2, parent, false);
            return new MyView(itemView);
        }

        @Override
        public void onBindViewHolder(VideoListAdapter.MyView holder, final int position) {

            Toast.makeText(YoutubePlaylist.this, VideoId.get(position).toString(), Toast.LENGTH_SHORT).show();


            holder.imageView.setImageDrawable(thumbnailViews.get(position));
            holder.videosTitleTextView.setText("1234567890");

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Player.cueVideo(VideoId.get(position));
//                    Toast.makeText(YoutubePlaylist.this, "Clicked ", Toast.LENGTH_SHORT).show();

                    Intent myIntent = new Intent(YoutubePlaylist.this, WebViewActivity.class);
                    myIntent.putExtra("id", VideoId.get(position));
                    YoutubePlaylist.this.startActivity(myIntent);


                }
            });
        }



        @Override
        public int getItemCount() {
            return thumbnailViews.size();
        }
    }



}
