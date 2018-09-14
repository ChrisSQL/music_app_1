package com.area52.techno.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.area52.techno.R;
import com.area52.techno.activities.WebViewActivity;
import com.bumptech.glide.Glide;
import com.prof.youtubeparser.VideoStats;
import com.prof.youtubeparser.models.stats.Statistics;
import com.prof.youtubeparser.models.videos.Video;

import java.util.ArrayList;

import android.widget.Button;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by marco on 6/15/16.
 *
 * Adapter for the recycler view
 *
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>{

    private ArrayList<Video> videos;
    private int rowLayout;
    private Context mContext;
    //TODO: delete
    private final String API_KEY = "AIzaSyBqirS7s6MeKSZQPzWT9lvD5RjacP1pqK4";

    YouTubePlayerView youTubePlayerView;
    Button button;
    YouTubePlayer.OnInitializedListener onInitializedListener;


    public VideoAdapter(ArrayList<Video> list, int rowLayout, Context context) {
        this.videos = list;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }

    public void clearData(){
        if (videos != null)
            videos.clear();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position)  {

        final Video currentVideo = videos.get(position);

        String pubDateString = currentVideo.getDate();
        final String videoTitle = currentVideo.getTitle();
        final String videoImage = currentVideo.getCoverLink().toString();
        final String videoId = currentVideo.getVideoId();

        //retrieve video link
        final String link = "https://www.youtube.com/watch?v=" + videoId;

        viewHolder.title.setText(currentVideo.getTitle());
        viewHolder.pubDate.setText(pubDateString);

        Glide.with(mContext).load(currentVideo.getCoverLink()).into(viewHolder.ytimage);

//        Picasso.with(mContext)
//                .load(currentVideo.getCoverLink())
//                .placeholder(R.drawable.progress_drawable_black)
//                .resize(100,100)
//                .into(viewHolder.ytimage);

        //show statistic of the selected video
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

         //       Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
         //       mContext.startActivity(intent1);


                Intent myIntent = new Intent(mContext, WebViewActivity.class);
                myIntent.putExtra("id", videoId);
                mContext.startActivity(myIntent);

                // Play YouTube video in Internal Player

            }
        });

        //open the video on Youtube
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                VideoStats videoStats = new VideoStats();
                String url = videoStats.generateStatsRequest(videoId, API_KEY);
                videoStats.execute(url);
                videoStats.onFinish(new VideoStats.OnTaskCompleted() {
                    @Override
                    public void onTaskCompleted(Statistics stats) {

                        String body = "Views: " + stats.getViewCount() + "\n" +
                                "Like: " + stats.getLikeCount() + "\n" +
                                "Dislike: " + stats.getDislikeCount() + "\n" +
                                "Number of comment: " + stats.getCommentCount() + "\n" +
                                "Number of favourite: " + stats.getFavoriteCount();

                        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
                        dialogBuilder.setTitle("Stats for: \"" + videoTitle + "\"");
                        dialogBuilder.setMessage(body);
                        dialogBuilder.setCancelable(false);
                        dialogBuilder.setNegativeButton(android.R.string.ok,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        dialogBuilder.show();
                    }

                    @Override
                    public void onError() {
                        Toast.makeText(mContext, "Unable to get statistic for this video. Please try again", Toast.LENGTH_SHORT).show();
                    }
                });
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return videos == null ? 0 : videos.size();
    }

    public ArrayList<Video> getList() {
        return videos;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView pubDate;
        ImageView ytimage;

        public ViewHolder(View itemView) {

            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            pubDate = (TextView) itemView.findViewById(R.id.pubDate);
            ytimage = (ImageView)itemView.findViewById(R.id.youtubeImage);

        }
    }
}
