package com.example.internproject;

import java.util.Comparator;

public class Person {
    private String name;
    private String team;
    private String imageUrl;
    private String designation;

    Person(String name, String designation, String team, String imageUrl){
        this.name = name;
        this.designation = designation;
        this.team = team;
        this.imageUrl = imageUrl;
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

}
