package com.example.internproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    private ArrayList<Person> people;

    public PersonAdapter(Context context, ArrayList<Person> list){
        people = list;
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {
        TextView displayName;
        TextView nameLabel;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);

            displayName = itemView.findViewById(R.id.displayName);
            nameLabel = itemView.findViewById(R.id.nameLabel);
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
        viewHolder.displayName.setText(people.get(i).getName());
        viewHolder.nameLabel.setText("Name: ");
    }

    @Override
    public int getItemCount() {
        return people.size();
    }
}
