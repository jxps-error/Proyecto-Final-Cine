package cine;

import javax.swing.*;

public class Sistema {

    Usuario usuarios[] = new Usuario[10];
    Cliente clientes[] = new Cliente[10];
    Pelicula peliculas[] = new Pelicula[10];
    Reserva reservas[] = new Reserva[50];
    Sala salas[] = new Sala[2];

    int cu = 0, cc = 0, cp = 0, cr = 0;
    int contadorReserva = 1;

    public Sistema() {

        usuarios[cu++] = new Usuario("Admin", "1", "1234", Rol.ADMIN);
        usuarios[cu++] = new Usuario("Operador", "2", "1111", Rol.OPERADOR);

        peliculas[cp++] = new Pelicula(1, "Batman", "Accion", 120, "PG13", 3000);
        peliculas[cp++] = new Pelicula(2, "Mario", "Animacion", 90, "G", 2500);
        peliculas[cp++] = new Pelicula(3, "Avengers", "Accion", 150, "PG13", 3500);
        peliculas[cp++] = new Pelicula(4, "Spiderman", "Accion", 130, "PG13", 3200);
        peliculas[cp++] = new Pelicula(5, "Frozen", "Animacion", 100, "G", 2800);

        salas[0] = new Sala(1, 6, 6);
        salas[1] = new Sala(2, 6, 6);
    }

    // 🔥 NUEVO INICIO
    public void inicio() {

        int opcion = Integer.parseInt(JOptionPane.showInputDialog(
                "=== BIENVENIDO A CINE PELICULEANDO ===\n\n"
                + "1. Administrador\n"
                + "2. Operador\n"
                + "3. Salir"
        ));

        if (opcion == 3) return;

        Rol rolSeleccionado = (opcion == 1) ? Rol.ADMIN : Rol.OPERADOR;

        login(rolSeleccionado);
    }

    // 🔥 LOGIN CON ROL
    public void login(Rol rolEsperado) {

        String nombre = JOptionPane.showInputDialog("Usuario:");
        String cedula = JOptionPane.showInputDialog("Cedula:");
        String pin = JOptionPane.showInputDialog("PIN:");

        for (int i = 0; i < cu; i++) {

            if (usuarios[i].getCedula().equals(cedula)
                    && usuarios[i].getPin().equals(pin)
                    && usuarios[i].getRol() == rolEsperado) {

                JOptionPane.showMessageDialog(null, "Bienvenido " + usuarios[i].getNombre());

                if (rolEsperado == Rol.ADMIN)
                    menuAdmin();
                else
                    menuOperador();

                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Credenciales incorrectas");
        inicio(); // vuelve al inicio
    }

    // 🔥 CARTELERA
    public void verPeliculas() {
        String lista = "=== CARTELERA ===\n\n";

        for (int i = 0; i < cp; i++) {
            lista += peliculas[i].getCodigo() + " - " + peliculas[i].getTitulo()
                    + " - ₡" + peliculas[i].getPrecio() + "\n";
        }

        JOptionPane.showMessageDialog(null, lista);
    }

    public void menuAdmin() {
    int op;

    do {
        op = Integer.parseInt(JOptionPane.showInputDialog(
                "=== ADMIN ===\n"
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
                    "=== OPERADOR ===\n"
                    + "1. Ver cartelera\n"
                    + "2. Reservar\n"
                    + "3. Buscar comprobante\n"
                    + "4. Salir"
            ));

            switch (op) {
                case 1 -> verPeliculas();
                case 2 -> reservar();
                
            }

        } while (op != 4);

        inicio();
    }

    public void registrarUsuario() {
        usuarios[cu++] = new Usuario(
                JOptionPane.showInputDialog("Nombre"),
                JOptionPane.showInputDialog("Cedula"),
                JOptionPane.showInputDialog("PIN"),
                Rol.OPERADOR);
    }

    public void registrarCliente() {
        clientes[cc++] = new Cliente(
                JOptionPane.showInputDialog("Nombre"),
                JOptionPane.showInputDialog("Cedula"),
                JOptionPane.showInputDialog("Correo"),
                JOptionPane.showInputDialog("Telefono"));
    }

    public void reservar() {

    verPeliculas();

    int iP = Integer.parseInt(JOptionPane.showInputDialog("Seleccione pelicula")) - 1;
    int iS = Integer.parseInt(JOptionPane.showInputDialog("Sala (1-2)")) - 1;

    JOptionPane.showMessageDialog(null, salas[iS].mostrar());

    int f = Integer.parseInt(JOptionPane.showInputDialog("Fila")) - 1;
    int c = Integer.parseInt(JOptionPane.showInputDialog("Columna")) - 1;

    if (!salas[iS].ocuparAsiento(f, c)) {
        JOptionPane.showMessageDialog(null, "Asiento ocupado");
        return;
    }

    // 🔥 NUEVA LOGICA
    Cliente cli = obtenerORegistrarCliente();

    Reserva r = new Reserva(contadorReserva++, cli, peliculas[iP], 1);
    reservas[cr++] = r;

    JOptionPane.showMessageDialog(null, r.generar());
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
    
    public Cliente obtenerORegistrarCliente() {

    String cedula = JOptionPane.showInputDialog("Cedula cliente:");

    // Buscar cliente existente
    for (int i = 0; i < cc; i++) {
        if (clientes[i].getCedula().equals(cedula)) {
            return clientes[i];
        }
    }

    // Si no existe → registrar
    JOptionPane.showMessageDialog(null, "Cliente no existe, se registrará");

    String nombre = JOptionPane.showInputDialog("Nombre:");
    String correo = JOptionPane.showInputDialog("Correo:");
    String telefono = JOptionPane.showInputDialog("Telefono:");

    Cliente nuevo = new Cliente(nombre, cedula, correo, telefono);
    clientes[cc++] = nuevo;

    return nuevo;
}
    
    public void verUsuarios() {

    if (cu == 0) {
        JOptionPane.showMessageDialog(null, "No hay usuarios");
        return;
    }

    String lista = "=== USUARIOS ===\n\n";

    for (int i = 0; i < cu; i++) {
        lista += usuarios[i].getNombre()
                + " | Cedula: " + usuarios[i].getCedula()
                + " | Rol: " + usuarios[i].getRol()
                + "\n";
    }

    JOptionPane.showMessageDialog(null, lista);
}
    
    public void verClientes() {

    if (cc == 0) {
        JOptionPane.showMessageDialog(null, "No hay clientes");
        return;
    }

    String lista = "=== CLIENTES ===\n\n";

    for (int i = 0; i < cc; i++) {
        lista += clientes[i].getNombre()
                + " | Cedula: " + clientes[i].getCedula()
                + "\n";
    }

    JOptionPane.showMessageDialog(null, lista);
}
    
    
}