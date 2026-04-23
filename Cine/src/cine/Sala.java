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
        String s = "";
        for (int i = 0; i < asientos.length; i++) {
            for (int j = 0; j < asientos[i].length; j++) {
                s += asientos[i][j] == 0 ? "[L]" : "[X]";
            }
            s += "\n";
        }
        return s;
    }
}