package com.example.programacionmovil1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class ActivityPhoto extends AppCompatActivity {
    ImageView imageView;

    Button btntakefoto;

    String pathfoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        imageView = (ImageView)findViewById(R.id.imageView);
        //btntakefoto
    }
}