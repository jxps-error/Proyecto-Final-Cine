package cine;

public class Sala {

    private int numero;
    private int[][] asientos;

    public Sala(int numero, int filas, int columnas) {
        this.numero = numero;
        asientos = new int[filas][columnas];
    }

    public int getNumero() { return numero; }

    public boolean ocuparAsiento(int f, int c) {
        if (asientos[f][c] == 0) {
            asientos[f][c] = 1;
            return true;
        }
        return false;
    }

    public String mostrar() {

    String s = "        PANTALLA\n";
    s += "=========================\n\n";

    // encabezado columnas
    s += "   ";
    for (int j = 0; j < asientos[0].length; j++) {
        s += (j + 1) + "  ";
    }
    s += "\n";

    for (int i = 0; i < asientos.length; i++) {

        char fila = (char) ('A' + i);
        s += fila + "  ";

        for (int j = 0; j < asientos[i].length; j++) {
            s += (asientos[i][j] == 0 ? "O" : "X") + "  ";
        }
        s += "\n";
    }

    s += "\nO = Disponible | X = Ocupado";

    return s;
}
    
    public boolean ocuparAsientoTexto(String asiento) {

    asiento = asiento.toUpperCase();

    int fila = asiento.charAt(0) - 'A';
    int columna = Integer.parseInt(asiento.substring(1)) - 1;

    if (fila < 0 || fila >= asientos.length || columna < 0 || columna >= asientos[0].length) {
        return false;
    }

    if (asientos[fila][columna] == 0) {
        asientos[fila][columna] = 1;
        return true;
    }

    return false;
}
}