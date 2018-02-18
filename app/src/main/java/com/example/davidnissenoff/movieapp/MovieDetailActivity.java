package com.example.davidnissenoff.movieapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.AdapterView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by David Nissenoff on 2/17/2018.
 */


public class MovieDetailActivity extends AppCompatActivity {
    private ScrollView mScrollview;
    private Context mContext;
    private TextView descriptionTextview;
    private TextView titleView;
    private ImageView posterView;
    private Button submitButton;
    private boolean alreadySeen;
    private boolean wantSee;
    private boolean doNot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        String title = this.getIntent().getExtras().getString("title");
        String poster = this.getIntent().getExtras().getString("poster");
        String description = this.getIntent().getExtras().getString("description");

        setTitle(title);
        mScrollview = findViewById(R.id.scroll_View);
        descriptionTextview = findViewById(R.id.description_text_view);
        titleView = findViewById(R.id.movie_name_text_view);
        posterView = findViewById(R.id.movie_image);
        descriptionTextview.setText(description);
        Picasso.with(mContext).load(poster).into(posterView);
        titleView.setText(title);
        titleView.setTextSize(35);
        descriptionTextview.setTextSize(20);
        submitButton = (Button) findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view){
                // construct intent
                // send back to main activity

                Intent radioIntent = new Intent();
                radioIntent.putExtra("has_seen", alreadySeen);
                radioIntent.putExtra("want_to",wantSee);
                radioIntent.putExtra("do_not",doNot);
                setResult(RESULT_OK, radioIntent);
                finish(); // goes back to previous activity

            }
        });

    }
    public void onRadioButtonClicked1 (View view){
        alreadySeen = ((RadioButton) view).isChecked();
    }
    public void onRadioButtonClicked2 (View view){

        wantSee = ((RadioButton) view).isChecked();
    }
    public void onRadioButtonClicked3 (View view){

        doNot = ((RadioButton) view).isChecked();
    }


}
