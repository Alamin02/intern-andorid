package com.example.internproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    private List<Person> people;
    private ArrayList<String> designations = new ArrayList<>();
    private List<Integer> positions = new ArrayList<>();


    PersonAdapter(Context context, List<Person> list){
        people = list;
        Collections.sort(people, Person.personDesignationComparator);

        if (people.size() > 0){
             designations.add(people.get(0).getDesignation());
             positions.add(0);
        }

        for (int i = 1; i < people.size(); i++){
            if(people.get(i).getDesignation().equals(people.get(i-1).getDesignation())){
                continue;
            }
            designations.add(people.get(i).getDesignation());
            positions.add(i);
        }
    }

    class PersonViewHolder extends RecyclerView.ViewHolder {
        TextView displayName;
        TextView displayDesignation;
        TextView displayTeam;
        TextView displayCategory;

        PersonViewHolder(@NonNull View itemView) {
            super(itemView);

            displayName = itemView.findViewById(R.id.displayName);
            displayDesignation = itemView.findViewById(R.id.displayDesignation);
            displayTeam = itemView.findViewById(R.id.displayTeam);
            displayCategory = itemView.findViewById(R.id.displayCategory);
        }
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder viewHolder, int i) {

        viewHolder.itemView.setTag(people.get(i));

        // Set the category title visible for the first element and remove for the rest
        if(positions.contains(i)) {
            viewHolder.displayCategory.setText(designations.get(positions.indexOf(i)));
        }
        else {
            viewHolder.displayCategory.setVisibility(View.GONE);
        }

        viewHolder.displayName.setText(people.get(i).getName());
        viewHolder.displayDesignation.setText(people.get(i).getDesignation());
        viewHolder.displayTeam.setText(people.get(i).getTeam());

    }

    @Override
    public int getItemCount() {
        return people.size();
    }
}
