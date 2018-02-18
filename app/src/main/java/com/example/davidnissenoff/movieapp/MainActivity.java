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
    private TextView hasSeenTextview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        hasSeenTextview = findViewById(R.id.has_seen_text_view);
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
                detailIntent.putExtra("title", selectedMovie.title);
                detailIntent.putExtra("poster", selectedMovie.imageUrl);
                detailIntent.putExtra("description", selectedMovie.description);
                startActivityForResult(detailIntent, 1);
            }
        });

}
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if (resultCode == RESULT_OK){ // seconf activity is sending data
                boolean alreadySeen = data.getBooleanExtra("has_seen", false);
                boolean wantSee = data.getBooleanExtra("want_to", false);
                boolean doNot = data.getBooleanExtra("do_not", false);
                if (alreadySeen){
                    hasSeenTextview.setText("Already seen");

                } else if (wantSee){
                    hasSeenTextview.setText("Want to see");
                }
                else if (doNot){
                    hasSeenTextview.setText("Do not like");
                }
                else {
                    hasSeenTextview.setText("Pick one");}
            }
        }
    }
}
