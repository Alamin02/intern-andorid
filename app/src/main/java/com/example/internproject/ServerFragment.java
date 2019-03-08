package com.example.internproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ServerFragment extends Fragment {
    private static boolean firstCreated = true;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder> myAdapter;
    View view;
    List people = null;

    public static List<Person> peopleFromServer = new ArrayList<>();
    private static final String TAG = "Server Fragment";

    private static final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("people");

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(firstCreated){
            new UploadTask().execute();

            firstCreated = false;
        }

        resetRecyclerAdapter();

        dbRef.addValueEventListener(updateListener);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_server, container, false);

        return view;
    }

    private ValueEventListener updateListener = new ValueEventListener() {

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            peopleFromServer = new ArrayList<>();
            Log.d(TAG, Long.toString(dataSnapshot.getChildrenCount()));

            for (DataSnapshot child : dataSnapshot.getChildren()){
                peopleFromServer.add(child.getValue(Person.class));
            }

            resetRecyclerAdapter();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.d(TAG, "loadPost:onCancelled", databaseError.toException());
        }
    };

    public void resetRecyclerAdapter(){
        recyclerView = view.findViewById(R.id.recycler_server);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new PersonAdapter(getActivity(), peopleFromServer);
        recyclerView.setAdapter(myAdapter);
    }


}
