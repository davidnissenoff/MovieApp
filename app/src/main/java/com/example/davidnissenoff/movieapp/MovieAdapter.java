package com.example.davidnissenoff.movieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

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
    public Object getItem(int position) {
        return mMovieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.list_item_movie, parent, false);
            holder = new ViewHolder();
            holder.titleTextView = convertView.findViewById(R.id.movie_name_text_view);
            holder.descriptionTextView = convertView.findViewById(R.id.description_text_view);
            holder.characterTextView = convertView.findViewById(R.id.characters_text_view);
            holder.movieImageView = convertView.findViewById(R.id.movie_image);
            holder.hasSeenTextView = convertView.findViewById(R.id.has_seen_text_view);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        TextView titleTextView = holder.titleTextView;
        TextView descriptionTextView = holder.descriptionTextView;
        TextView characterTextView = holder.characterTextView;
        ImageView movieImageView = holder.movieImageView;
        TextView hasSeenTextView = holder.hasSeenTextView;
        Movie movie = (Movie) getItem(position);
        titleTextView.setText(movie.title);
        titleTextView.setTextSize(20);

        descriptionTextView.setText(movie.description);
        descriptionTextView.setTextSize(11);





        characterTextView.setTextSize(14);

        String character_1 = movie.main_characters.get(0);
        String character_2 = movie.main_characters.get(1);
        String character_3 = movie.main_characters.get(2);
        String charas = character_1 + ", " + character_2 + ", " + character_3;
        characterTextView.setText(charas);
        int seen = movie.getHasSeen();
        switch (seen){
            case 1:
                hasSeenTextView.setText("Already seen");
                break;
            case 2:
                hasSeenTextView.setText("Want to see");
                break;
            case 3:
                hasSeenTextView.setText("Do not like");
                break;
            case 4:
                hasSeenTextView.setText("Pick one");
                break;
            default:
                hasSeenTextView.setText("Has Seen?");
                break;
        }

        Picasso.with(mContext).load(movie.imageUrl).into(movieImageView);
        return convertView;
    }
   private static class ViewHolder{
        public TextView titleTextView;
        public TextView descriptionTextView;
        public TextView characterTextView;
        public ImageView movieImageView;
        public TextView hasSeenTextView;
    }

}
