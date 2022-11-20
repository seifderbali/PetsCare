package com.example.custom_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.custom_listview.databinding.ActivityMainBinding;
import com.example.custom_listview.databinding.ActivityUserBinding;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class UserActivity extends AppCompatActivity {

    ActivityUserBinding binding;
    private Button deletbutton;
    private AppDataBase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intentt = new Intent(this, Home.class);

        ImageView home = findViewById(R.id.home);

        database = AppDataBase.getInstance(this);

        deletbutton=findViewById(R.id.delete);

        Intent intent = this.getIntent();

        if (intent != null){

            String name = intent.getStringExtra("name");
            String animal = intent.getStringExtra("animal");
            String food = intent.getStringExtra("food");
            String walk = intent.getStringExtra("walk");
            String shower = intent.getStringExtra("shower");
            int imageid = intent.getIntExtra("imageid",R.drawable.c);
            int id = intent.getIntExtra("id",0);
            String tae=System.lineSeparator();

            String ch ="Name: "+name+tae+"Animal Type: "+animal+tae+"Food: "+food+tae+"Walk: "+walk+tae+"Shower: "+shower ;

            binding.nameProfile.setText(name);
            binding.foodProfile.setText(food);
            binding.walkProfile.setText(walk);
            binding.showerProfile.setText(shower);
            binding.annimalProfile.setText(animal);



            binding.profileImage.setImageResource(imageid);


            ImageView qr = findViewById(R.id.qrcode);
            MultiFormatWriter mWriter = new MultiFormatWriter();
            try {
                BitMatrix mMatrix = mWriter.encode(ch, BarcodeFormat.QR_CODE, 400, 400);
                BarcodeEncoder mEncoder = new BarcodeEncoder();
                Bitmap mBitmap = mEncoder.createBitmap(mMatrix);

                qr.setImageBitmap(mBitmap);


            } catch (WriterException e) {
                e.printStackTrace();
            }


        }
        //action
        deletbutton.setOnClickListener(view ->{

           deletePet(findPet(intent.getIntExtra("id",0)));
            startActivity(intentt);
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intentt);

            }
        });




    }
    //methodes
    public Pets findPet(int id) {
        return database.petsDAO().find(id);
    }

    public void deletePet(Pets p) {
        database.petsDAO().delete(p);
    }
}