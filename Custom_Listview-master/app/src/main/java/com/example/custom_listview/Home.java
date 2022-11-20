package com.example.custom_listview;

import static java.lang.String.valueOf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;


import com.example.custom_listview.databinding.ActivityHomeBinding;
import com.example.custom_listview.databinding.ActivityMainBinding;
import com.example.custom_listview.databinding.ActivityUserBinding;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    List<Pets>petsList=new ArrayList<>();

    private AppDataBase database;
    ActivityHomeBinding binding;
    Button nButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        database = AppDataBase.getInstance(this);
        petsList=retrievePets();

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int[] imageId = {R.drawable.d,R.drawable.c,R.drawable.f,R.drawable.r,R.drawable.b};

        nButton = findViewById(R.id.nButton);
        System.out.println("Your message");
        nButton.setOnClickListener(view ->{
            Intent intent = new Intent(this, AddPetsActivity.class);
            startActivity(intent);
        });



        ListAdapter listAdapter = new ListAdapter(Home.this,petsList);

        binding.listview.setAdapter(listAdapter);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(Home.this,UserActivity.class);
                i.putExtra("name",petsList.get(position).getNom());
                i.putExtra("id",petsList.get(position).getId());
                i.putExtra("animal",petsList.get(position).getAnimal());
                i.putExtra("walk",valueOf(petsList.get(position).getWalk())+" Time(s) per Day");
                i.putExtra("food",valueOf(petsList.get(position).getFood())+" Time(s) per Day");
                i.putExtra("shower",valueOf(petsList.get(position).getShower())+" Time(s) per Week");
                i.putExtra("imageid",imageId[petsList.get(position).getImageId()]);

                startActivity(i);

            }
        });

    }
    public List<Pets> retrievePets() {
        return database.petsDAO().getPets();
    }

}