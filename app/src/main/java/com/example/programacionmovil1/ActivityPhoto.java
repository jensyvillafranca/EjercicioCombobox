package com.example.programacionmovil1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ActivityPhoto extends AppCompatActivity {

    //Objeto de tipo imagen
    ImageView imageView;

    //Objeto de tipo boton
    Button btntakefoto;

    String pathfoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        //Capturando la imagen del objeto de la interfaz
        imageView = (ImageView)findViewById(R.id.imageView);

        //Permitiendo interactuar con este botón en la GUI.
        btntakefoto = (Button) findViewById(R.id.btnPhoto);


        //Evento de click en el botón.
        btntakefoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permisos();
            }
        });
    }

    private void permisos() {

        //lógica para poder crear un permiso

        //el va checar si el permiso dentro de la aplicacion para el permiso de la camara es distinto de un permiso otorgado
        //Significa que no le hemos dado el permiso y nosotros necesitamos otorgar a la aplicación el respectivo permiso.
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            //Request a la API del sistema operativo, esta es la peticion del permiso a la API del sistema operativo
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},101);
        }
        else{
            //si ya tenemos el permiso y esta otorgado, entonces vamos a tener la fotografia.
            TomarFoto();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //el sistema operativo nos manda el requestCode que le mandamos arriba.
        if(requestCode == 101){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                TomarFoto();
            }
            //si denegamos el permiso
            else{
                Toast.makeText(getApplicationContext(), "Permiso denegado", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void TomarFoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //Intencion donde nosotros intentamos capturar una foto

        //levanta una actividad para que esa actividad se pueda resolver
        if(intent.resolveActivity(getPackageManager())!= null){//significa que capturamos una imagen
            startActivityForResult(intent, 102);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }
}