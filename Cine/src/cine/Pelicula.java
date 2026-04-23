package cine;

public class Pelicula {

    private int codigo;
    private String titulo;
    private String genero;
    private int duracion;
    private String clasificacion;
    private double precio;

    public Pelicula(int codigo, String titulo, String genero, int duracion, String clasificacion, double precio) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.genero = genero;
        this.duracion = duracion;
        this.clasificacion = clasificacion;
        this.precio = precio;
    }

    public int getCodigo() { return codigo; }
    public String getTitulo() { return titulo; }
    public double getPrecio() { return precio; }
}