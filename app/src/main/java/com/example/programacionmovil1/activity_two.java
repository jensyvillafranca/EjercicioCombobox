package com.example.programacionmovil1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_two extends AppCompatActivity {
    Button btncreate, btnList, btnCombo, btnvoz, btnTomarFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        /*Obteniendo el id del boton en la interfaz*/
        btncreate = (Button)findViewById(R.id.btnCrear);
        btnList = (Button)findViewById(R.id.btnLista);
        btnCombo = (Button)findViewById(R.id.btnCombo);
        btnvoz = (Button)findViewById(R.id.btnVoz);
        btnTomarFoto = (Button)findViewById(R.id.btnFotoTake);

        View.OnClickListener butonclick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class<?> actividad = null;

                if (view.getId() == R.id.btnCrear) {
                    actividad = MainActivity.class;
                }
                else if (view.getId() == R.id.btnLista) {
                    actividad = ActivityList.class;
                }
                else if (view.getId() == R.id.btnCombo) {
                    actividad = ActivityCombo.class;
                }
                else if (view.getId() == R.id.btnFotoTake) {
                    actividad = ActivityPhoto.class;
                }
                else if (view.getId() == R.id.btnVoz) {
                    actividad = activity_speech.class;
                }

                if(actividad != null){
                    MoveActivity (actividad);
                }
            }
        };
        /*Creando el evento de escucha del boton Crear Empleado*/
        btncreate.setOnClickListener(butonclick);

        /*Creando el boton de escucha del boton de listar empleados*/
        btnList.setOnClickListener(butonclick);

        /*Creando el boton de escucha del boton mostrar un combobox*/
        btnCombo.setOnClickListener(butonclick);

        btnvoz.setOnClickListener(butonclick);

        btnTomarFoto.setOnClickListener(butonclick);
    }

    private void MoveActivity(Class<?>actividad) {
        Intent intent = new Intent(getApplicationContext(), actividad);
        startActivity(intent);
    }
}