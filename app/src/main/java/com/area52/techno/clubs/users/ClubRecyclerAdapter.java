package com.area52.techno.clubs.users;

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

import com.area52.techno.R;
import com.area52.techno.models.Club;
import com.area52.techno.models.User;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by csa on 3/7/2017.
 */

public class ClubRecyclerAdapter extends RecyclerView.Adapter<ClubRecyclerAdapter.MyHoder>{

    List<Club> list;
    Context context;

    public ClubRecyclerAdapter(List<Club> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.card_club,parent,false);
        MyHoder myHoder = new MyHoder(view);

        return myHoder;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    @Override
    public void onBindViewHolder(MyHoder holder, int position) {
        Club mylist = list.get(position);
        holder.name.setText(mylist.getName());

        if( (int) Math.round(round(mylist.getMiles(),0)) > 200.0) {
            holder.name.setText(mylist.getName());
        }
        else if( (int) Math.round(round(mylist.getMiles(),0)) == 1.0){
            holder.name.setText(mylist.getName() + " - " + (int) Math.round(round(mylist.getMiles(),0))  + " Mile");
        }else{
            holder.name.setText(mylist.getName() + " - " + (int) Math.round(round(mylist.getMiles(),0))  + " Miles");
        }

        //holder.name.setText(mylist.getName() + " - " + (int) Math.round(round(mylist.getMiles(),0))  + " Miles");
        holder.name.setSelected(true);

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(mylist.getFacebookLink()));
                context.startActivity(i);
            }
        });

//        Glide.with(context)
//                .load(mylist.getPhotoUrl())
//                .apply(new RequestOptions()
//                        .fitCenter()
//                        .format(DecodeFormat.PREFER_ARGB_8888)
//                        .override(Target.SIZE_ORIGINAL)
//                .placeholder(R.drawable.sesh_logo))
//                .into(holder.thumbnail);
//
//        Picasso.with(context)
//                .load(mylist.getPhotoUrl())
//                .placeholder(R.drawable.sesh_logo)
//                .into(holder.thumbnail);

        Picasso.with(context)
                .load(mylist.getPhotoUrl())
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
                                .load(mylist.getPhotoUrl())
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

        public MyHoder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.vname);
        //    email= (TextView) itemView.findViewById(R.id.vemail);
            thumbnail= (ImageView) itemView.findViewById(R.id.thumbnail);


        }
    }
}
