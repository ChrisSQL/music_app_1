package com.area52.techno.djs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.area52.techno.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Created by azem on 12/22/17.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    List<DJListItem> items;

    public UserAdapter(List<DJListItem> items) {
        this.items = items;
    }

    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dj_item_row_2,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder holder, int position) {

        holder.dj_text1.setText(items.get(position).getTitle());
        holder.dj_image_left.setImageResource(R.drawable.sesh_logo);

        holder.dj_text2.setText(items.get(position).getTitle());
        holder.dj_image_right.setImageResource(R.drawable.sesh_logo);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView dj_text1;
        public ImageView dj_image_left;

        public TextView dj_text2;
        public ImageView dj_image_right;

        public ViewHolder(View itemView) {
            super(itemView);

            dj_text1 = itemView.findViewById(R.id.dj_text1);
            dj_image_left = itemView.findViewById((R.id.dj_image_left));

            dj_text2 = itemView.findViewById(R.id.dj_text2);
            dj_image_right = itemView.findViewById((R.id.dj_image_right));

        }
    }
}
