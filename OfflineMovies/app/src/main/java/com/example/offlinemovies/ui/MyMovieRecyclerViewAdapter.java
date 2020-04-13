package com.example.offlinemovies.ui;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.offlinemovies.R;
import com.example.offlinemovies.data.remote.ApiConstants;
import com.example.offlinemovies.data.local.entity.MovieEntity;

import java.util.List;


public class MyMovieRecyclerViewAdapter extends RecyclerView.Adapter<MyMovieRecyclerViewAdapter.ViewHolder> {

    private List<MovieEntity> movies;
    private Context context;

    public MyMovieRecyclerViewAdapter(Context context, List<MovieEntity> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = movies.get(position);
        Glide.with(context)
                .load(ApiConstants.IMAGE_URL + holder.mItem.getPosterPath())
                .into(holder.ivCover);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final ImageView ivCover;
        private MovieEntity mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            ivCover = view.findViewById(R.id.imageViewCover);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + ivCover.getId() + "'";
        }
    }
}
