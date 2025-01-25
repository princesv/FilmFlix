package com.example.filmflix.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmflix.R;
import com.example.filmflix.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoviesRvAdapter extends RecyclerView.Adapter<MoviesRvAdapter.MoviesViewHolder> {
    List<Movie> movies= new ArrayList<>();
    private ListItemClickListener mClickListener;
    public interface ListItemClickListener{
        void onListItemClick(int listItemIndex);
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item,parent,false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        holder.tvName.setText(movies.get(position).getTitle());
        holder.tvDescription.setText(""+movies.get(position).getVote_average());
        //  holder.animalImage.setImageResource(animalImage[position]);
        Picasso.get().load("http://image.tmdb.org/t/p/w185//"+movies.get(position).getPoster_path()).into(holder.movieImage);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView tvName;
        public final TextView tvDescription;
        public final ImageView movieImage;
        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.textView);
            tvDescription = itemView.findViewById(R.id.textView2);
            movieImage = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedItemIndex = getAdapterPosition();
            mClickListener.onListItemClick(clickedItemIndex);
        }
    }
    public void updateMoviesList(List<Movie> movies,ListItemClickListener listener){
        this.movies=movies;
        mClickListener=listener;
        notifyDataSetChanged();
    }
}
