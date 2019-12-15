package com.fiid.moviescataloguesub2.Fragment;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fiid.moviescataloguesub2.DetailMoviesActivity;
import com.fiid.moviescataloguesub2.Movies;
import com.fiid.moviescataloguesub2.MoviesAdapter;
import com.fiid.moviescataloguesub2.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TVShowFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private String[] tvTitle;
    private String[] tvDesc;
    private TypedArray tvPost;
    private ArrayList<Movies> allMovies = new ArrayList<>();
    private RecyclerView rvMovies;
    private MoviesAdapter adapter;

    public static TVShowFragment newInstance(int index) {
        // Required empty public constructor
        TVShowFragment tv = new TVShowFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        tv.setArguments(bundle);
        return tv;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tvshow, container, false);
        rvMovies = view.findViewById(R.id.tv_recycler);
        rvMovies.setHasFixedSize(true);
        allMovies.addAll(getAllMovies());
        showRecyclerList();
        return view;
    }


    public ArrayList<Movies> getAllMovies() {
        tvTitle = getResources().getStringArray(R.array.tv_title);
        tvDesc = getResources().getStringArray(R.array.tv_desc);
        tvPost = getResources().obtainTypedArray(R.array.tv_photo);

        ArrayList<Movies> allMovies = new ArrayList<>();
        for (int i = 0; i < tvTitle.length; i++){
            Movies movies = new Movies(tvPost.getResourceId(i, -1), tvTitle[i], tvDesc[i]);
            allMovies.add(movies);
        }
        return allMovies;
    }

    private void showRecyclerList(){
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        MoviesAdapter moviesAdapter = new MoviesAdapter(allMovies);
        rvMovies.setAdapter(moviesAdapter);

        moviesAdapter.setOnItemClickCallback(new MoviesAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Movies movies) {
                Intent detailIntent = new Intent(getContext(), DetailMoviesActivity.class);
                detailIntent.putExtra(DetailMoviesActivity.EXTRA_MOVIES, movies);
                startActivity(detailIntent);
            }

        });
    }
}
