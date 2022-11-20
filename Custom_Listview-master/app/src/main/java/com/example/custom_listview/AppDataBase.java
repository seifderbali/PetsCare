package com.example.custom_listview;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Pets.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {


    //var
    private static AppDataBase instance;
    public abstract PetsDAO petsDAO();

    public static AppDataBase getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "PetsCareDataBase")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;

    }

}


