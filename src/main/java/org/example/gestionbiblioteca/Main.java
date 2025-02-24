package org.example.gestionbiblioteca;

import org.example.gestionbiblioteca.vista.ConsolaVista;
import org.example.gestionbiblioteca.modelo.BaseXConnection;

public class Main {
    public static void main(String[] args) {
        System.out.println("📚 Iniciando aplicación de Biblioteca...");

        // Mostrar el menú en la consola para que el usuario elija opciones
        ConsolaVista.mostrarMenu();

        // Cerrar la conexión con BaseX al salir
        BaseXConnection.cerrarConexion();
    }
}
