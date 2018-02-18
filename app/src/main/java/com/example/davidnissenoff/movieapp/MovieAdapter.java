package com.example.davidnissenoff.movieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/**
 * Created by David Nissenoff on 2/13/2018.
 */

public class MovieAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Movie> mMovieList;
    private ArrayList<Movie> mCharacters;
    private LayoutInflater mInflater;

    public MovieAdapter(Context mContext, ArrayList<Movie> mMovieList){
        this.mContext = mContext;
        this.mMovieList = mMovieList;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return mMovieList.size();
    }

    @Override
    public Object getItem(int i) {
        return mMovieList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.list_item_movie, parent, false);
            holder = new ViewHolder();
            holder.titleTextView = convertView.findViewById(R.id.movie_name_text_view);
            holder.descriptionTextView = convertView.findViewById(R.id.description_text_view);
            holder.characterTextView = convertView.findViewById(R.id.characters_text_view);
            holder.movieImageView = convertView.findViewById(R.id.movie_image);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        TextView titleTextView = holder.titleTextView;
        TextView descriptionTextView = holder.descriptionTextView;
        TextView characterTextView = holder.characterTextView;
        ImageView movieImageView = holder.movieImageView;
        Movie movie = (Movie) getItem(i);
        titleTextView.setText(movie.title);
        titleTextView.setTextSize(20);

        descriptionTextView.setText(movie.description);
        descriptionTextView.setTextSize(9);

        characterTextView.setText(movie.characters);

        characterTextView.setTextSize(14);


        Picasso.with(mContext).load(movie.imageUrl).into(movieImageView);
        return convertView;
    }
   private static class ViewHolder{
        public TextView titleTextView;
        public TextView descriptionTextView;
        public TextView characterTextView;
        public ImageView movieImageView;
    }

}
