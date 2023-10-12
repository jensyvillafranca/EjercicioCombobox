package com.example.programacionmovil1.Models;

public class Personas {
    private Integer id;
    private String nombres;
    private String apellidos;
    private Integer edad;
    private String correo;

    //Clic derecho y generar para crear este constructor.
    public Personas(Integer id, String nombres, String apellidos, Integer edad, String correo) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.correo = correo;
    }

    //Constructor Vacio
    public Personas() {
    }

    //metodo get-set ID
    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    //metodo get-set Nombres
    public String getNombres() {

        return nombres;
    }

    public void setNombres(String nombres) {

        this.nombres = nombres;
    }

    //metodo get-set Apellidos
    public String getApellidos() {

        return apellidos;
    }

    public void setApellidos(String apellidos) {

        this.apellidos = apellidos;
    }

    //metodo get-set Edad
    public Integer getEdad() {

        return edad;
    }

    public void setEdad(Integer edad) {

        this.edad = edad;
    }

    //metodo get-set Correo
    public String getCorreo() {

        return correo;
    }

    public void setCorreo(String correo) {

        this.correo = correo;
    }
}
