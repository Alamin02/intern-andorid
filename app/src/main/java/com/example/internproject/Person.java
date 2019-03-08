package com.example.internproject;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Comparator;

@Entity //(tableName = "people")
public class Person {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "team")
    private String team;

    @ColumnInfo(name = "imageUrl")
    private String imageUrl;

    @ColumnInfo(name = "designation")
    private String designation;


    Person(int id, String name, String designation, String team, String imageUrl){
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.team = team;
        this.imageUrl = imageUrl;
    }

    Person(){
    }

    String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }


    static Comparator<Person> personDesignationComparator = new Comparator<Person>() {

        @Override
        public int compare(Person o1, Person o2) {
            String designation1 = o1.getDesignation();
            String designation2 = o2.getDesignation();

            return designation1.compareTo(designation2);
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
