package cine;

import javax.swing.*;

public class Sistema {

    Usuario usuarios[] = new Usuario[10];
    Cliente clientes[] = new Cliente[10];
    Pelicula peliculas[] = new Pelicula[10];
    Reserva reservas[] = new Reserva[50];
    Sala salas[] = new Sala[2];

    Usuario usuarioActual;
    
    int cu = 0, cc = 0, cr = 0;
    int contadorReserva = 1;

    public Sistema() {

        usuarios[cu++] = new Usuario("Admin", "1", "1234", Rol.ADMIN);
        usuarios[cu++] = new Usuario("Operador", "2", "1111", Rol.OPERADOR);

        peliculas[0] = new Pelicula("P01","Batman","Acción",120,"PG-13",3000);
        peliculas[1] = new Pelicula("P02","Mario","Animación",100,"TP",2500);
        peliculas[2] = new Pelicula("P03","Avengers","Acción",150,"PG-13",3500);
        peliculas[3] = new Pelicula("P04","Spiderman","Acción",130,"PG-13",3200);
        peliculas[4] = new Pelicula("P05","Jurassic","Aventura",140,"PG-13",2800);

        salas[0] = new Sala(1, 6, 6);
        salas[1] = new Sala(2, 6, 6);
    }

    public void inicio() {

        int opcion = Integer.parseInt(JOptionPane.showInputDialog(
                "=== BIENVENIDO A CINE PELICULEANDO ===\n\n"
                + "1. Administrador\n"
                + "2. Operador\n"
                + "3. Salir"
        ));

        if (opcion == 3) return;

        login((opcion == 1) ? Rol.ADMIN : Rol.OPERADOR);
    }

        public void login(Rol rolEsperado) {

    String cedula = JOptionPane.showInputDialog("Cedula:");
    String pin = JOptionPane.showInputDialog("PIN:");

    for (int i = 0; i < cu; i++) {

        if (usuarios[i].getCedula().equals(cedula)
                && usuarios[i].getPin().equals(pin)
                && usuarios[i].getRol() == rolEsperado) {

            usuarioActual = usuarios[i];

            JOptionPane.showMessageDialog(null, "Bienvenido " + usuarios[i].getNombre());

            if (rolEsperado == Rol.ADMIN)
                menuAdmin();
            else
                menuOperador();

            return;
        }
    }

    JOptionPane.showMessageDialog(null, "Credenciales incorrectas");
    inicio();
}



    public void verPeliculas() {

    String lista = "=== CARTELERA ===\n\n";

    for (int i = 0; i < peliculas.length; i++) {

        if (peliculas[i] != null) {

            lista += "Código: " + peliculas[i].codigo + "\n";
            lista += "Nombre: " + peliculas[i].nombre + "\n";
            lista += "Género: " + peliculas[i].genero + "\n";
            lista += "Duración: " + peliculas[i].duracion + " min\n";
            lista += "Clasificación: " + peliculas[i].clasificacion + "\n";
            lista += "Precio: ₡" + peliculas[i].precio + "\n";
            lista += "-------------------------\n";
        }
    }

    JOptionPane.showMessageDialog(null, lista);
    }

    public void menuAdmin() {

        int op;

        do {
            op = Integer.parseInt(JOptionPane.showInputDialog(
                    "=== ADMIN (" + usuarioActual.getNombre() + ") ===\n"
                    + "1. Ver cartelera\n"
                    + "2. Registrar usuario\n"
                    + "3. Ver usuarios\n"
                    + "4. Ver clientes\n"
                    + "5. Reservar\n"
                    + "6. Buscar comprobante\n"
                    + "7. Salir"
            ));

            switch (op) {
                case 1 -> verPeliculas();
                case 2 -> registrarUsuario();
                case 3 -> verUsuarios();
                case 4 -> verClientes();
                case 5 -> reservar();
                case 6 -> buscar();
            }

        } while (op != 7);

        inicio();
    }

    public void menuOperador() {

        int op;

        do {
            op = Integer.parseInt(JOptionPane.showInputDialog(
                    "=== OPERADOR (" + usuarioActual.getNombre() + ") ===\n"
                    + "1. Ver cartelera\n"
                    + "2. Reservar\n"
                    + "3. Buscar comprobante\n"
                    + "4. Salir"
            ));

            switch (op) {
                case 1 -> verPeliculas();
                case 2 -> reservar();
                case 3 -> buscar(); // 🔥 ARREGLADO
            }

        } while (op != 4);

        inicio();
    }

    public void registrarUsuario() {

    String nombre = JOptionPane.showInputDialog("Nombre:");
    String cedula = JOptionPane.showInputDialog("Cedula:");
    String pin = JOptionPane.showInputDialog("PIN:");

    int opcionRol = Integer.parseInt(JOptionPane.showInputDialog(
            "Seleccione rol:\n"
            + "1. Administrador\n"
            + "2. Operador"
    ));

    Rol rol;

    if (opcionRol == 1) {
        rol = Rol.ADMIN;
    } else {
        rol = Rol.OPERADOR;
    }

    usuarios[cu++] = new Usuario(nombre, cedula, pin, rol);

    JOptionPane.showMessageDialog(null, "Usuario registrado correctamente");
    }

    public Cliente obtenerORegistrarCliente() {

        String cedula = JOptionPane.showInputDialog("Cedula cliente:");

        for (int i = 0; i < cc; i++) {
            if (clientes[i].getCedula().equals(cedula)) {
                return clientes[i];
            }
        }

        String nombre = JOptionPane.showInputDialog("Nombre:");
        String correo = JOptionPane.showInputDialog("Correo:");
        String telefono = JOptionPane.showInputDialog("Telefono:");

        Cliente nuevo = new Cliente(nombre, cedula, correo, telefono);
        clientes[cc++] = nuevo;

        return nuevo;
    }

    public void reservar() {

        verPeliculas();

        int iP = Integer.parseInt(JOptionPane.showInputDialog("Seleccione pelicula")) - 1;
        int iS = Integer.parseInt(JOptionPane.showInputDialog("Sala (1-2)")) - 1;
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de asientos:"));

        String asientosSeleccionados = "";

        for (int i = 0; i < cantidad; i++) {

            JOptionPane.showMessageDialog(null, salas[iS].mostrar());

            String asiento = JOptionPane.showInputDialog("Seleccione asiento (Ej: D4)");

            if (!salas[iS].ocuparAsientoTexto(asiento)) {
                JOptionPane.showMessageDialog(null, "Asiento inválido");
                i--;
            } else {
                asientosSeleccionados += asiento + " ";
            }
        }

        Cliente cli = obtenerORegistrarCliente();

        Reserva r = new Reserva(contadorReserva++, cli, peliculas[iP], cantidad, asientosSeleccionados, iS + 1);
        reservas[cr++] = r;

        JOptionPane.showMessageDialog(null,
                r.generar());
    }

    public void buscar() {

        int num = Integer.parseInt(JOptionPane.showInputDialog("Numero comprobante"));

        for (int i = 0; i < cr; i++) {
            if (reservas[i].getNumero() == num) {
                JOptionPane.showMessageDialog(null, reservas[i].generar());
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "No existe");
    }

    public void verUsuarios() {

        String lista = "";

        for (int i = 0; i < cu; i++) {
            lista += usuarios[i].getNombre() + " - " + usuarios[i].getRol() + "\n";
        }

        JOptionPane.showMessageDialog(null, lista);
    }

    public void verClientes() {

        String lista = "";

        for (int i = 0; i < cc; i++) {
            lista += clientes[i].getNombre() + "\n";
        }

        JOptionPane.showMessageDialog(null, lista);
    }
}