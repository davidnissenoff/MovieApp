package com.example.davidnissenoff.movieapp;
import java.io.InputStream;
import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by David Nissenoff on 2/13/2018.
 */

public class Movie {
    public String title;
    public String imageUrl;
    public String description;
    public String characters;
    public String hasSeen;

    public static ArrayList<Movie> getMoviesFromFIle(String filename, Context context){

        ArrayList<Movie> movieList = new ArrayList<Movie>();

        try {
            String jsonString = loadJsonFromAsset("movies.json", context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray movies = json.getJSONArray("movies");
            for (int i = 0; i < movies.length(); i++){
                Movie movie = new Movie();
                movie.title = movies.getJSONObject(i).getString("title");
                movie.description = movies.getJSONObject(i).getString("description");
                movie.imageUrl = movies.getJSONObject(i).getString("poster");
                movie.characters = movies.getJSONObject(i).getString("main_characters").replace("[", "").replace("\"", "").replace("]","").replace(",",", ");

                movieList.add(movie);
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
        return movieList;
    }
    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }
}
