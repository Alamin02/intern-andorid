package com.example.internproject;

import android.os.AsyncTask;
import android.util.Log;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UploadTask extends AsyncTask<Void, Void, Void> {

    private static final String TAG = "Server Fragment";
    private static final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected Void doInBackground(Void... voids) {

        Log.d(TAG, Integer.toString(App.people.size()));

        for (int i = 0; i < App.people.size(); i++){
            dbRef.child("people").child("person"+Integer.toString(i)).setValue(App.people.get(i));
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        Log.d(TAG, "Task Finished!");
    }
}
