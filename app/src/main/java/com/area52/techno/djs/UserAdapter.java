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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dj_item_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder holder, int position) {
        holder.name.setText(items.get(position).getTitle());
        holder.time.setText(items.get(position).getTime());
        holder.djImage.setImageResource(R.drawable.gracey_friel_card2);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView time;
        public ImageView djImage;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            time= itemView.findViewById(R.id.time);
            djImage = itemView.findViewById((R.id.dj_list_image));

        }
    }
}
