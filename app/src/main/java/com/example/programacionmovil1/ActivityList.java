package com.example.programacionmovil1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.programacionmovil1.Models.Personas;
import com.example.programacionmovil1.configuracion.SQLiteConexion;
import com.example.programacionmovil1.configuracion.Transacciones;

import java.util.ArrayList;

public class ActivityList extends AppCompatActivity {

    //Declarando variables globales
    SQLiteConexion conexion;
    ListView lista;

    //Arreglo de tipo personas
    ArrayList<Personas> listapersonas;

    //Arreglo String
    ArrayList<String> arreglopersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        try {
            //Establecer conexión a la base de datos
            conexion = new SQLiteConexion(this, Transacciones.nambeBD, null, 1);

            //Amarrar/Castear el objeto de ListView al objeto gráfico de la interfaz.
            lista = (ListView) findViewById(R.id.listpersonas);

            //Obtener la lista de personas de la base de datos.
            getPersonas();

            //Crear un adaptador
            ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arreglopersonas);


            //Llenar la lista de la interaz de usuario
            lista.setAdapter(adp);

            //Podriamos seleccionar un elemento pero para ello necesitamos un evento, aquí se está creando ese evento
            //Al hacer clic en un elemento de la lista, lo vamos a setear.
            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //del arreglo de personas podemos obtener un elemento

                    //obteniendo la posición del arreglo
                    String posicionPersona = listapersonas.get(i).getNombres();

                    //Ver cual es el elemento que estamos tocando.
                    Toast.makeText(ActivityList.this, "Nombre" + posicionPersona, Toast.LENGTH_LONG).show();
                }
            });
        }
        catch (Exception ex)
        {
            ex.toString();
        }
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
        FillList();
    }

    private void FillList() {
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