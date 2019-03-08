package com.example.internproject;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.annotation.ColorInt;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DownloadTask extends AsyncTask<Void, Void, List> {
    private static final String TAG = "Server Fragment";
    private static final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("people");
    private static List<Person> peopleFromServer = new ArrayList<>();

    private ServerFragment serverFragment;

    DownloadTask(ServerFragment serverFragment){
        this.serverFragment = serverFragment;
    }

    @Override
    protected List doInBackground(Void... voids) {

        dbRef.addValueEventListener(updateListener);
        return peopleFromServer;
    }

    @Override
    protected void onPostExecute(List list) {
        super.onPostExecute(list);
        Log.d(TAG, "Invalidated...");
    }

    private ValueEventListener updateListener = new ValueEventListener() {

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            peopleFromServer = new ArrayList<>();
            Log.d(TAG, Long.toString(dataSnapshot.getChildrenCount()));

            for (DataSnapshot child : dataSnapshot.getChildren()){
                 peopleFromServer.add(child.getValue(Person.class));
            }

            serverFragment.resetRecyclerAdapter();
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.d(TAG, "loadPost:onCancelled", databaseError.toException());
        }
    };
}
