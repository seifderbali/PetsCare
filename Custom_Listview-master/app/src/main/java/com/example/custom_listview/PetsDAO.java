package com.example.custom_listview;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface PetsDAO {

    @Insert
    public void addPets(Pets p);

    @Delete
    void delete(Pets p);

    @Query("SELECT * FROM pets_table")
    public List<Pets> getPets();

    @Query("SELECT * FROM pets_table WHERE id =:idd")
    public Pets find(int idd);

}
