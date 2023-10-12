package com.example.programacionmovil1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_two extends AppCompatActivity {
    Button btncreate, btnList, btnCombo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        /*Obteniendo el id del boton en la interfaz*/
        btncreate = (Button)findViewById(R.id.btnCrear);
        btnList = (Button)findViewById(R.id.btnLista);
        btnCombo = (Button)findViewById(R.id.btnCombo);

        /*Creando el evento de escucha del boton Crear Empleado*/
        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intCrear = new Intent(getApplicationContext(), MainActivity.class);
                /*En la linea anterior por medio del objeto intCrear mandamos a llamar la actividad que deseamos mostrar*/

                /*intCrear.putExtra("Numero1",10); /*Para mandar los datos a la otra actividad
                intCrear.putExtra("Numero2",20);*/


                startActivity(intCrear); /*Iniciar la actividad*/

            }
        });

        /*Creando el boton de escucha del boton de listar empleados*/
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Mandando a llamar la actividad: ActivityList
                Intent intCrear = new Intent(getApplicationContext(), ActivityList.class);
                startActivity(intCrear);

            }
        });

        btnCombo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Mandando a llamar la actividad: ActivityList
                Intent intCrear = new Intent(getApplicationContext(), ActivityCombo.class);
                startActivity(intCrear);
            }
        });


    }
}