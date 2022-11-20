package com.example.custom_listview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class AddPetsActivity extends AppCompatActivity {

    private AppDataBase database;

    //widgets
    private EditText name;
    private SeekBar seekBarfood;
    private SeekBar seekBarwalk;
    private SeekBar seekBarshower;
    private TextView foodcount;
    private TextView walkcount;
    private TextView showercount;
    private Button addbutton;
    Pets p = new Pets();
    TextInputLayout textInputLayout;
    AutoCompleteTextView autoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pets);

        Intent intent = new Intent(this, Home.class);

        //bind
        ImageView home = findViewById(R.id.home);

        database = AppDataBase.getInstance(this);
        name = findViewById(R.id.name);
        foodcount = findViewById(R.id.foodcount);
        walkcount = findViewById(R.id.walkcount);
        showercount = findViewById(R.id.showercount);
        seekBarfood=findViewById(R.id.seekBarfood);
        seekBarwalk=findViewById(R.id.seekBarwalk);
        seekBarshower=findViewById(R.id.seekBarshower);
        addbutton=findViewById(R.id.addbutton);

        seekBarshower.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                showercount.setText(""+ progress );

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBarwalk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                walkcount.setText(""+ progress );

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBarfood.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                foodcount.setText(""+ progress );//+" Time(s)"
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });


        textInputLayout=findViewById(R.id.menu_drop);
        autoCompleteTextView=findViewById(R.id.drop_items);


        String [] items= {"Dog","Cat","Fish","Rabbit","bird"};
        ArrayAdapter<String> itemAdapter=new ArrayAdapter<>(AddPetsActivity.this , R.layout.items_list, items);
        autoCompleteTextView.setAdapter(itemAdapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                p.setAnimal(item);
                p.setImageId(position);
                System.out.println("@@@@@@@@@@");

                System.out.println(position);

            }

        });
    //action
        addbutton.setOnClickListener(view ->{
           p.setNom(name.getText().toString());
           p.setFood( Integer.parseInt(foodcount.getText().toString()));
           p.setWalk(Integer.parseInt(walkcount.getText().toString()));
            p.setShower(Integer.parseInt(showercount.getText().toString()));
            addPet(p);
            Context context = getApplicationContext();
            CharSequence text = "Hello "+p.getNom()+" Weclcome to the Family";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            startActivity(intent);
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent);

            }
        });
    }
    //methodes
    public void addPet(Pets p) {

        database.petsDAO().addPets(p);
    }
}