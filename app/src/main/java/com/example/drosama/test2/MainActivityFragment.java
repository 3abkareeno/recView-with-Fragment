package com.example.drosama.test2;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivityFragment extends Fragment {

    private RecyclerAdapter mRecAdapter;
    private static int mPage = 1;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        // Recycler View Initialization
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.movies_grid);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        mRecAdapter = new RecyclerAdapter(getActivity());
        recyclerView.setAdapter(mRecAdapter);

        // Displaying initial set of movies
        popularMovies();

        // Next | Previous Button Handling
        Button buttonPrev = (Button) rootView.findViewById(R.id.prev_button);
        if (mPage == 1){
            buttonPrev.setVisibility(View.GONE);
        }else{
            buttonPrev.setVisibility(View.VISIBLE);
        }
        buttonPrev.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                prevPage();
            }
        });

        Button buttonNext = (Button) rootView.findViewById(R.id.next_button);
        buttonNext.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                nextPage();
            }
        });

        return rootView;
    }
    private void nextPage(){
        mPage++;
        new GetMovies(new GetMovies.SetOnSuccess() {
            @Override
            public void onSuccess(Movie[] result) {
                if (result != null) {
                    mRecAdapter.updateMovies(result);
                }
            }
        },getContext() ).execute("https://api.themoviedb.org/3/movie/popular?api_key=33be9732d89e6e88a6922c96c52332ef&language=en-US&page="+mPage);
        Button buttonPrev = (Button) getActivity().findViewById(R.id.prev_button);
        if (mPage == 1){
            buttonPrev.setVisibility(View.GONE);
        }else{
            buttonPrev.setVisibility(View.VISIBLE);
        }
    }
    private void prevPage(){
        mPage--;
        new GetMovies(new GetMovies.SetOnSuccess() {
            @Override
            public void onSuccess(Movie[] result) {
                if (result != null) {
                    mRecAdapter.updateMovies(result);
                }
            }
        }, getContext()).execute("https://api.themoviedb.org/3/movie/popular?api_key=33be9732d89e6e88a6922c96c52332ef&language=en-US&page="+mPage);
        Button buttonPrev = (Button) getActivity().findViewById(R.id.prev_button);
        if (mPage == 1){
            buttonPrev.setVisibility(View.GONE);
        }else{
            buttonPrev.setVisibility(View.VISIBLE);
        }
    }
    private void popularMovies(){
        getActivity().setTitle("Popular Movies");
        new GetMovies(new GetMovies.SetOnSuccess() {
            @Override
            public void onSuccess(Movie[] result) {
                if (result != null) {
                    mRecAdapter.updateMovies(result);
                }
            }
        }, getActivity()).execute("https://api.themoviedb.org/3/movie/popular?api_key=33be9732d89e6e88a6922c96c52332ef&language=en-US&page="+mPage);
    }

}
