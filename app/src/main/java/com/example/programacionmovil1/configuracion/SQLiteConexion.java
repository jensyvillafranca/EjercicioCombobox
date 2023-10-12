package com.example.programacionmovil1.configuracion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/*Esta clase es para la creación y la actualización de la base de datos.*/
public class SQLiteConexion extends SQLiteOpenHelper
{
    public SQLiteConexion(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //este es para crear los objetos de base de datos
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { //se llama cuando la BD se crea por primera vez
        sqLiteDatabase.execSQL(Transacciones.CreateTablePersonas); //Creando la tabla como tal.
    }

    //este es para actualizarlo o destruir los objetos
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(Transacciones.DropTablePersonas);
        onCreate(sqLiteDatabase); //aqui elimina el objecto que se creo primariamente, es decir la tabla y la vuelve a crear desde cero
    }
}
