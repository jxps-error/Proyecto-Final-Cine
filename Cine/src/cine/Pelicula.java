package cine;

public class Pelicula {

    public String codigo;
    public String nombre;
    public String genero;
    public int duracion;
    public String clasificacion;
    public double precio;

    public Pelicula(String codigo, String nombre, String genero, int duracion, String clasificacion, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.genero = genero;
        this.duracion = duracion;
        this.clasificacion = clasificacion;
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }
}