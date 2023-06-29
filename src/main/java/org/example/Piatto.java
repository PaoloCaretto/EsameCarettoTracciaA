package org.example;

public class Piatto {
    private int id;
    private String name;
    private String descrizione;
    private double calories;
    private boolean vegan;

    public Piatto(int id, String name, String descrizione, double calories, boolean vegan) {
        this.id = id;
        this.name = name;
        this.descrizione = descrizione;
        this.calories = calories;
        this.vegan = vegan;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public double getCalories() {
        return calories;
    }

    public boolean isVegan() {
        return vegan;
    }
}

