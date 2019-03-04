package com.example.internproject;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

import java.util.ArrayList;

public class App extends Application {
    public static ArrayList people = new ArrayList<Person>();
    @Override
    public void onCreate() {
        super.onCreate();


        people.add(new Person("Alu", "16"));
        people.add(new Person("Alu", "16"));
        people.add(new Person("Alu", "16"));

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );

        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
