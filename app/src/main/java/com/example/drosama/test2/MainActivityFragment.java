package com.example.drosama.test2;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivityFragment extends Fragment {

    private RecyclerAdapter mRecAdapter;
    static ArrayList<Movie> movies = new ArrayList<>();

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GetMovies(new GetMovies.SetOnSuccess() {
            @Override
            public void onSuccess(Movie[] result) {
                if (result != null) {
                    Collections.addAll(movies, result);
                    Log.v("Movie Size in onCreate", "" + movies.size());
                }
            }
        }).execute("https://api.themoviedb.org/3/movie/popular?api_key=33be9732d89e6e88a6922c96c52332ef&language=en-US&page=1");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        for (Movie m : movies) {
            Log.v("Movie entry", m.getTitle());
        }
        getActivity().setTitle("Popular Movies");

        // Recycler View Initialization
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.movies_grid);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        Log.v("Movie Size in onCreateView", "" + movies.size());
        for (Movie m : movies) {
            Log.v("Movie entry", m.getTitle());
        }
        mRecAdapter = new RecyclerAdapter(getActivity(), movies);
        recyclerView.setAdapter(mRecAdapter);

        return rootView;
    }

}
