package org.example.gestionbiblioteca;

import org.example.gestionbiblioteca.vista.ConsolaVista;
import org.example.gestionbiblioteca.modelo.BaseXConnection;

public class Main {
    public static void main(String[] args) {
        System.out.println("📚 Iniciando aplicación de Biblioteca...");

        ConsolaVista.mostrarMenu();

        BaseXConnection.cerrarConexion();
    }
}
