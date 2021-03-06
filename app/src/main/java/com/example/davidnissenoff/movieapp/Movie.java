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
    private int episode_no;
    public String imageUrl;
    public String description;
    public ArrayList<String> main_characters;
    public int hasSeen;
    public String url;

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
                movie.url = movies.getJSONObject(i).getString("url");
                JSONArray charas = movies.getJSONObject(i).getJSONArray("main_characters");
                ArrayList<String>Characters = new ArrayList<>();
                if (charas != null){
                    int length = charas.length();
                    for (int j = 0; j<length; j++){
                        Characters.add(charas.get(j).toString());
                    }
                }

                //movie.characters = movies.getJSONObject(i).getString("main_characters").replace("[", "").replace("\"", "").replace("]","").replace(",",", ");
                movie.main_characters = Characters;
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
    public int getHasSeen(){
        return hasSeen;
    }
    public void setHasSeen(int hasSeen){
        this.hasSeen = hasSeen;
    }
}
