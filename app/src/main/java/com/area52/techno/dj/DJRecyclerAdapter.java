package com.area52.techno.dj;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.area52.techno.MyAccountActivity;
import com.area52.techno.MyDJActivity;
import com.area52.techno.R;
import com.area52.techno.models.DJ;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DJRecyclerAdapter extends RecyclerView.Adapter<DJRecyclerAdapter.MyHoder>{

    List<DJ> list;
    Context context;

    public DJRecyclerAdapter(List<DJ> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.card_dj,parent,false);
        MyHoder myHoder = new MyHoder(view);

        return myHoder;
    }

    @Override
    public void onBindViewHolder(MyHoder holder, int position) {
        DJ mylist = list.get(position);
        holder.name.setText(mylist.getName());

                holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Perform action on click
                Intent i=new Intent(context,MyDJActivity.class);
                i.putExtra("djName", mylist.getName());
                i.putExtra("getPhotoUrlDJ", mylist.getPhotoUrlDJ());
                i.putExtra("FacebookLink", mylist.getFacebookLink());
                i.putExtra("SoundcloudLink", mylist.getSoundcloudLink());
                i.putExtra("country", mylist.getCountry());
                i.putExtra("genre", mylist.getGenre());
                context.startActivity(i);
            }

        });

        Picasso.with(context)
                .load(mylist.getPhotoUrlDJ())
                .resize(200,200)
                .centerCrop()
                .placeholder(R.drawable.sesh_logo)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(holder.thumbnail, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(context)
                                .load(mylist.getPhotoUrlDJ())
                                .resize(200,200)
                                .centerCrop()
                                .placeholder(R.drawable.sesh_logo)
                                .error(R.drawable.sesh_logo)
                                .into(holder.thumbnail, new Callback() {
                                    @Override
                                    public void onSuccess() {

                                    }

                                    @Override
                                    public void onError() {
                                        Log.v("Picasso","Could not fetch image");
                                    }
                                });
                    }
                });

    //    Toast.makeText(context, mylist.getPhotoUrlDJ(), Toast.LENGTH_SHORT).show();



    }

    @Override
    public int getItemCount() {

        int arr = 0;

        try{
            if(list.size()==0){
                arr = 0;
            }
            else{
                arr=list.size();
            }

        }catch (Exception e){

        }

        return arr;
    }



    class MyHoder extends RecyclerView.ViewHolder{

        TextView name;
        ImageView thumbnail;
        String soundCloud;

        public MyHoder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.vname);
        //    email= (TextView) itemView.findViewById(R.id.vemail);
            thumbnail= (ImageView) itemView.findViewById(R.id.thumbnail_dj);



        }
    }
}
