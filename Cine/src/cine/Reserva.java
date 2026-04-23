package cine;

public class Reserva {

    private int numero;
    private Cliente cliente;
    private Pelicula pelicula;
    private int cantidad;
    private double subtotal;
    private double iva;
    private double total;

    public Reserva(int numero, Cliente cliente, Pelicula pelicula, int cantidad) {
        this.numero = numero;
        this.cliente = cliente;
        this.pelicula = pelicula;
        this.cantidad = cantidad;
        calcular();
    }

    private void calcular() {
        subtotal = cantidad * pelicula.getPrecio();
        iva = subtotal * 0.13;
        total = subtotal + iva;
    }

    public int getNumero() { return numero; }

    public String generar() {
        return "--- SISTEMA CINE ---\n"
                + "Comprobante: " + numero + "\n\n"
                + "Cliente: " + cliente.getNombre() + "\n"
                + "Cedula: " + cliente.getCedula() + "\n\n"
                + "Pelicula: " + pelicula.getTitulo() + "\n"
                + "Cantidad: " + cantidad + "\n\n"
                + "Subtotal: " + subtotal + "\n"
                + "IVA: " + iva + "\n"
                + "TOTAL: " + total;
    }
}