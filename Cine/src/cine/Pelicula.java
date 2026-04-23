package cine;

public class Pelicula {

    String codigo;
    String nombre;
    String genero;
    int duracion;
    String clasificacion;
    double precio;
    int ventas;

    public Pelicula(String codigo, String nombre, String genero, int duracion, String clasificacion, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.genero = genero;
        this.duracion = duracion;
        this.clasificacion = clasificacion;
        this.precio = precio;
        this.ventas = 0;
    }

}