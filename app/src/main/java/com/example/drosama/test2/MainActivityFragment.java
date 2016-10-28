package com.example.drosama.test2;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivityFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        getActivity().setTitle("Popular Movies");

        // Recycler View Initialization
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.movies_grid);
        final RecyclerAdapter mRecAdapter=new RecyclerAdapter();
        recyclerView.setAdapter(mRecAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        new GetMovies(new GetMovies.SetOnSuccess() {
            @Override
            public void onSuccess(Movie[] result) {
                if (result != null) {
                    ArrayList<Movie> movies = new ArrayList<>(Arrays.asList(result));
                    mRecAdapter.addAll(movies);
                }
            }
        }).execute("https://api.themoviedb.org/3/movie/popular?api_key=33be9732d89e6e88a6922c96c52332ef&language=en-US&page="+"1");

        return rootView;
    }

}
