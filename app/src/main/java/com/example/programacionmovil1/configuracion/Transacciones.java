package com.example.programacionmovil1.configuracion;

public class Transacciones {
    //Nombre de la BD
    public static final String nambeBD = "PM012023";

    //Tablas de la BD
    public static final String tabla = "personas";

    //Campos de la tabla
    public static final String id = "id";
    public static final String nombres = "nombres";
    public static final String apellidos = "apellidos";
    public static final String edad = "edad";
    public static final String correo = "correo";

    //Consultas de base de datos DDL
    //Creando la tabla con sus campos
    public static final String CreateTablePersonas = "CREATE TABLE personas "+"(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombres TEXT, apellidos TEXT, edad INTEGER, " +
            "correo TEXT)";



    //Para borrar la tabla
    public static final String DropTablePersonas = "DROP TABLE IF EXISTS personas";

    //Consultas de base de datos DML
    public static final String SelectTablePersonas = "SELECT * FROM " + Transacciones.tabla;
}
