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
    MovieAdapter adapter;
    Movie selectedMovie;
    private TextView hasSeenTextview;
    private static final int RESULT_ID = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        final ArrayList<Movie> movieList = Movie.getMoviesFromFIle("movies.json", this);
        MovieAdapter adapter = new MovieAdapter(this, movieList);

        mListView = findViewById(R.id.movies_list_view);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Movie selectedMovie = movieList.get(position);
                Intent detailIntent = new Intent(mContext, MovieDetailActivity.class);
                detailIntent.putExtra("position", position);
                detailIntent.putExtra("title", selectedMovie.title);
                detailIntent.putExtra("poster", selectedMovie.imageUrl);
                detailIntent.putExtra("description", selectedMovie.description);
                startActivityForResult(detailIntent, RESULT_ID);
            }
        }
        );

}
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        hasSeenTextview = findViewById(R.id.has_seen_text_view);
        if (requestCode == RESULT_ID){
            if (resultCode == RESULT_OK){ // second activity is sending data
                boolean alreadySeen = data.getBooleanExtra("has_seen", false);
                boolean wantSee = data.getBooleanExtra("want_to", false);
                boolean doNot = data.getBooleanExtra("do_not", false);
                int position = data.getIntExtra("position", 0);
                TextView hasSeen = mListView.getChildAt(position).findViewById(R.id.has_seen_text_view);
                if (alreadySeen){
                    hasSeen.setText("Already seen");

                } else if (wantSee){
                    hasSeen.setText("Want to see");
                }
                else if (doNot){
                    hasSeen.setText("Do not like");
                }
                else {
                    hasSeen.setText("Pick one");}
            }
        }
    }
}
