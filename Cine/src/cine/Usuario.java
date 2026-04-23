package cine;

public class Usuario {

    private String nombre;
    private String cedula;
    private String pin;
    private Rol rol;

    public Usuario(String nombre, String cedula, String pin, Rol rol) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.pin = pin;
        this.rol = rol;
    }

    public String getCedula() { return cedula; }
    public String getPin() { return pin; }
    public Rol getRol() { return rol; }
    public String getNombre() { return nombre; }
}