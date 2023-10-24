package com.example.programacionmovil1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityPhoto extends AppCompatActivity {

    //Constantes para los valores de los permisos
    static final int peticion_acceso_camara = 101;
    static final int peticion_toma_fotografia = 102;

    String currentPhotoPath;

    //Objeto de tipo imagen para tomar la foto
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


        //Evento de click en el botón de Tomar Foto.
        btntakefoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                permisos();
            }
        });
    }

    /************************************************************/
    private void permisos() {

        //lógica para poder crear un permiso

        //el va checar si el permiso dentro de la aplicacion para el permiso de la camara es distinto de un permiso otorgado
        //Significa que no le hemos dado el permiso y nosotros necesitamos otorgar a la aplicación el respectivo permiso.
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            //Request a la API del sistema operativo, esta es la peticion del permiso a la API del sistema operativo
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},peticion_acceso_camara);
        }
        else{
            //si ya tenemos el permiso y esta otorgado, entonces vamos a tener la fotografia.
            //TomarFoto();
            dispatchTakePictureIntent();
        }
    }

/**************************************************************************/
    //Función con la que responde el sistema operativo en base la solicitud de permiso de la camara
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //el sistema operativo nos manda el requestCode que le mandamos arriba.
        if(requestCode == peticion_acceso_camara){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //TomarFoto();
                dispatchTakePictureIntent();
            }
            //si denegamos el permiso
            else{
                Toast.makeText(getApplicationContext(), "Permiso denegado", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**********************************************************/
    private void TomarFoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //Intencion donde nosotros intentamos capturar una foto

        //levanta una actividad para que esa actividad se pueda resolver
        if(intent.resolveActivity(getPackageManager())!= null){//significa que capturamos una imagen
            startActivityForResult(intent, peticion_toma_fotografia);

        }
    }
    /******************************************************/

    private File createImageFile() throws IOException {

        // Create an image file name

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String imageFileName = "JPEG_" + timeStamp + "_";

        //Environment es para obtener variables de entorno del sistema//con esto podemos acceder a los directorios del celular
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES); //obtener directorio de las imagenes

        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        //currentPhotoPath permite obtener la url donde está ubicada nuestra imagen
        return image;
    }

    /*********************************************************/
    //Código para tomar una imagen y guardarla
    private void dispatchTakePictureIntent() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //para tomar la foto

        // Ensure that there's a camera activity to handle the intent

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile(); //cargamos la imagen directamente de de la url
            } catch (IOException ex) {

                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {

                //Obtener URL de nuestra imagen
                Uri photoURI = FileProvider.getUriForFile(this, "com.example.programacionmovil1.fileprovider", photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                startActivityForResult(takePictureIntent, peticion_toma_fotografia);
            }
        }
    }
/************************************************************/
    //Capturar lo que viene desde el ActivityForResult.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Obtener toda la informacion de la data, pueden ser imagenes, texto, vide etc.
        //viene como respuesta al callback de la respuesta de la API del sistema operativo.


        if(requestCode == peticion_toma_fotografia && resultCode == RESULT_OK){
            /*Obtener la información que viene de la data
            Bundle extras = data.getExtras();


            Bitmap image = (Bitmap) extras.get("data");
            imageView.setImageBitmap(image);*/

            try {
                File foto = new File(currentPhotoPath); //trae toda la url
                //mandar el objeto
                imageView.setImageURI(Uri.fromFile(foto));
            }
            catch (Exception ex){
                ex.toString();
            }
        }
    }
}