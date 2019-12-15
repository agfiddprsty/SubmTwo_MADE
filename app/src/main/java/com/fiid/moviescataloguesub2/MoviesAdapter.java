package com.fiid.moviescataloguesub2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private ArrayList<Movies> allMovies;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }
    public MoviesAdapter(ArrayList<Movies> list){
        this.allMovies = list;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movietv_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, int position) {
        Movies movies = allMovies.get(position);

        Glide.with(holder.itemView.getContext())
                .load(movies.getPhoto())
                .apply(new RequestOptions().override(100,100 ))
                .into(holder.imgMov);
        holder.titleMov.setText(movies.getTitle());
        holder.descMov.setText(movies.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(allMovies.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return allMovies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView imgMov;
        TextView titleMov, descMov;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMov = itemView.findViewById(R.id.img_item);
            titleMov = itemView.findViewById(R.id.item_name);
            descMov = itemView.findViewById(R.id.item_desc);
        }
    }
    public interface OnItemClickCallback {
        void onItemClicked(Movies movies);
    }
}
