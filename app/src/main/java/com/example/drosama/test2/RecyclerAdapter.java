package com.example.drosama.test2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<Movie> movies = new ArrayList<>();
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.movie_pic);
        }

        public void bindMovie(Movie movie){
            String url = "http://image.tmdb.org/t/p/w500" + movie.getPosterpath();
            Picasso.with(imageView.getContext())
                    .load(url)
                    .into(imageView);
        }
    }

    public RecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        Log.d("Create", "was called");
        return new ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        final Movie movie = movies.get(position);
        holder.bindMovie(movie);
        Log.d("Bind", "was called");
    }
    @Override
    public int getItemCount() {
        return this.movies.size();
    }

    public void updateMovies(Movie[] result){
        this.movies.clear();
        Collections.addAll(this.movies, result);
        notifyDataSetChanged();
    }
}