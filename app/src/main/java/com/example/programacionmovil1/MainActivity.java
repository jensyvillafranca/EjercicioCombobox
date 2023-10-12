package com.example.programacionmovil1; //nombre del paquete

// importacion de librerias
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.programacionmovil1.configuracion.SQLiteConexion;
import com.example.programacionmovil1.configuracion.Transacciones;

public class MainActivity extends AppCompatActivity {//para poder usar la interfaz grafica de usuario
    //objetos globales
    EditText nombres, apellidos, edad, correo;
    Button btnprocesar;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //metodo onCreate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Esta línea conecta la interfaz gráfica de usuario con la logica

        /*aquí los tenemos como objetos,
        nombres = findViewById(R.id.txtNombres);
        apellidos = findViewById(R.id.txtApellidos);
        btnprocesar = findViewById(R.id.btnprocesar);*/

        //aqui ya le estamos haciendo el cast
        nombres = (EditText) findViewById(R.id.txtNombres);
        apellidos = (EditText)findViewById(R.id.txtApellidos);
        edad = (EditText)findViewById(R.id.txtEdad);
        correo = (EditText)findViewById(R.id.txtCorreo);

        btnprocesar = (Button) findViewById(R.id.btnprocesar);

        //nombres.setText(String.valueOf(getIntent().getIntExtra("Numero1",0)));
        //apellidos.setText(String.valueOf(getIntent().getIntExtra("Numero2",0)));


        //vamos a disparar un evento al dar click

        btnprocesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Toast.makeText(getApplicationContext(),
                        nombres.getText().toString() + apellidos.getText().toString(),
                        Toast.LENGTH_LONG).show();*/
                AddPerson();
            }
        });
    }
    private void AddPerson()
    {
        try {
            //Creando un objeto de tipo la clase conexion
            //Mandamos los parametros que el constructor de la clase SQLiteConexion recibe
            //this es el contexto y significa esta actividad
            //el cursorFactory es null
            //con esto establecemos la conexión.
            SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.nambeBD, null, 1);

            //Poner la base de datos en modo escribir
            SQLiteDatabase db = conexion.getWritableDatabase();

            //Este es un contenedor de valores, es como una llave hash que lleva una clave y un valor.
            //igual que una llave hash-table / key-value.
            ContentValues valores = new ContentValues();

            //Transacciones.nombres representa el campo de la tabla
            valores.put(Transacciones.nombres, nombres.getText().toString());
            valores.put(Transacciones.apellidos, apellidos.getText().toString());
            valores.put(Transacciones.edad, edad.getText().toString());
            valores.put(Transacciones.correo, correo.getText().toString());


            //Insertar en la base de datos. Estructura: nombre de la tabla, id autoincrementable, por ultimos los valores a insertar
            Long Result = db.insert(Transacciones.tabla, Transacciones.id, valores);

            //Para mostrar el mensaje exitoso
            Toast.makeText(this,getString(R.string.Respuesta), Toast.LENGTH_SHORT).show();
            db.close(); //cerrar conexión.

        }
        catch (Exception exception) {
            Toast.makeText(this, getString(R.string.Error), Toast.LENGTH_SHORT).show();
        }

    }
}