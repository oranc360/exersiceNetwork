package com.example.oran.exersicenetwork;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.oran.exersicenetwork.network.Hits;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HitsAdapter extends RecyclerView.Adapter<HitsAdapter.ViewHolder> {

    private ArrayList<Hits> hits;

    public HitsAdapter(ArrayList<Hits> hits) {
        this.hits = hits;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hit_row , parent , false);
        ImageView hitImage = view.findViewById(R.id.hitRow);
        return new ViewHolder(view , hitImage);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Hits hit = hits.get(position);
        Picasso.with(holder.view.getContext()).load(hit.getWebformatURL()).placeholder(R.drawable.ic_launcher_background).into(holder.hitImageView);
    }

    @Override
    public int getItemCount() {
        return hits.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView hitImageView;

        public ViewHolder(View itemView , ImageView hitImageView) {
            super(itemView);
            this.view = itemView;
            this.hitImageView = hitImageView;
        }
    }
}
