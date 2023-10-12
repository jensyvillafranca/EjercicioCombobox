package com.example.programacionmovil1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.programacionmovil1.Models.Personas;
import com.example.programacionmovil1.configuracion.SQLiteConexion;
import com.example.programacionmovil1.configuracion.Transacciones;

import java.util.ArrayList;

public class ActivityCombo extends AppCompatActivity {

    //Conexión a la BD
    SQLiteConexion conexion;

    //Objeto de tipo Spinner
    Spinner combopersonas;

    EditText nombres, apellidos, correo;

    //Arreglo de tipo personas
    ArrayList<Personas> listapersonas;

    //Arreglo String
    ArrayList<String> arreglopersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo);

        //Conexión a la base de datos
        conexion = new SQLiteConexion(this, Transacciones.nambeBD, null, 1);

        //Obteniendo el valor.
        combopersonas = (Spinner) findViewById(R.id.spinner);

        //Casteando los objetos
        nombres = (EditText) findViewById(R.id.cbNombre);
        apellidos = (EditText) findViewById(R.id.cbApellido);
        correo = (EditText) findViewById(R.id.cbCorreo);
        getPersonas();

        //Llenar el combo
        ArrayAdapter<CharSequence> adp = new ArrayAdapter(this, android.R.layout.simple_spinner_item,arreglopersonas);
        combopersonas.setAdapter(adp);

        combopersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nombres.setText(listapersonas.get(i).getNombres());
                apellidos.setText(listapersonas.get(i).getApellidos());
                correo.setText(listapersonas.get(i).getCorreo());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void getPersonas() {
        //Poniendo la BD en modo lectura.
        SQLiteDatabase db = conexion.getReadableDatabase();

        //Creando objeto de clase personas
        Personas person = null;

        //Creando una instancia del arreglo lista personas
        listapersonas = new ArrayList<Personas>();

        //Creando un cursor para poder seleccionar la data, es decir obtener todos los registros de la tabla personas.
        //SelectTablePersonas viene de la clase transacciones
        //null es el argumento pero no le vamos a pasar nada.
        Cursor cursor = db.rawQuery(Transacciones.SelectTablePersonas,null);

        //Recorrer el cursor, o todos los datos que vienen de la tabla personas
        while(cursor.moveToNext()){
            //Instancia de la clase Personas.
            person = new Personas();

            //Accediendo a los métodos setter de la clase personas, capturando valores.
            person.setId(cursor.getInt(0)); // posicion 0 porque el id de la persona es el 0, veamoslo como un arreglo.
            person.setNombres(cursor.getString(1));
            person.setApellidos(cursor.getString(2));
            person.setEdad(cursor.getInt(3));
            person.setCorreo(cursor.getString(4));

            //Meter esos datos dentro de la lista
            listapersonas.add(person);
        }
        //Cerrar el objeto en memoria
        cursor.close();

        //Llenar el objeto de lista.
        FillCombo();
    }

    private void FillCombo() {
        //recorrer la lista

        //inicializar el arreglo.
        arreglopersonas = new ArrayList<String>();
        for (int i = 0; i < listapersonas.size(); i++) {
            //Llenando el arreglo de tipo String "arreglopersonas" con lo que trae el arreglo que llenamos arriba.
            arreglopersonas.add(listapersonas.get(i).getId() + " - " +
                    listapersonas.get(i).getNombres() + " - " +
                    listapersonas.get(i).getApellidos());
        }
    }
}