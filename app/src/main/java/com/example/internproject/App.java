package com.example.internproject;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class App extends Application {
    public static ArrayList<Person> people = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize connection with the parse server
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );

        // Send a notice to the server on first installation
        ParseInstallation.getCurrentInstallation().saveInBackground();

        // Calling to start parsing JSON
        get_json();
    }

    // Method to parse JSON data and save that in an ArrayList
    public void get_json(){
        String json;
        try {
            // Read JSON file from assets folder
            InputStream is = this.getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);          // Make JSONArray object

            // Save JSONArray as Person objects in people ArrayList
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                people.add(new Person(obj.getString("name"), obj.getString("designation"), obj.getString("team"), obj.getString("imageUrl")));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
