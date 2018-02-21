package com.example.davidnissenoff.movieapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private Context mContext;
    private TextView hasSeen;
    private Movie mMovie;
    private MovieAdapter mMovieAdapter;
    private int seen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seen = 0;
        mContext = this;
        final ArrayList<Movie> movieList = Movie.getMoviesFromFIle("movies.json", this);
        mMovieAdapter = new MovieAdapter(this, movieList);

        mListView = findViewById(R.id.movies_list_view);
        mListView.setAdapter(mMovieAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mMovie = movieList.get(position);
                Intent detailIntent = new Intent(mContext, MovieDetailActivity.class);
                detailIntent.putExtra("title", mMovie.title);
                detailIntent.putExtra("poster", mMovie.imageUrl);
                detailIntent.putExtra("description", mMovie.description);
                startActivityForResult(detailIntent, 1);
            }
        }
        );

}
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if (resultCode == RESULT_OK) {
                seen = data.getIntExtra("hasSeen", 0);
                mMovie.setHasSeen(seen);
                mMovieAdapter.notifyDataSetChanged();
            }
}}}
