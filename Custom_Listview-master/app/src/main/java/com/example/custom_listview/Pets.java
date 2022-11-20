package com.example.custom_listview;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pets_table")
public class Pets {

    //var
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "nom")
    private String nom;
    @ColumnInfo(name = "animal")
    private String animal;
    @ColumnInfo(name = "food")
    private int food;
    @ColumnInfo(name = "walk")
    private int walk;
    @ColumnInfo(name = "shower")
    private int shower;
    @ColumnInfo(name = "image")
    private int imageId;

    //const


    public Pets(String nom, String animal, int food, int walk, int shower, int imageId) {
        this.nom = nom;
        this.animal = animal;
        this.food = food;
        this.walk = walk;
        this.shower = shower;
        this.imageId = imageId;
    }

    public Pets() {
    }

    // Getters Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getWalk() {
        return walk;
    }

    public void setWalk(int walk) {
        this.walk = walk;
    }

    public int getShower() {
        return shower;
    }

    public void setShower(int shower) {
        this.shower = shower;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }


    //ToString


    @Override
    public String toString() {
        return "Pets{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", animal='" + animal + '\'' +
                ", food=" + food +
                ", walk=" + walk +
                ", shower=" + shower +
                ", imageId='" + imageId + '\'' +
                '}';
    }
}
