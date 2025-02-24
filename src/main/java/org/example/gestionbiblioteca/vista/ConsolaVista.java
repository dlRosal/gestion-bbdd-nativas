package org.example.gestionbiblioteca.vista;

import org.example.gestionbiblioteca.controlador.LibroControlador;
import java.util.Scanner;

public class ConsolaVista {
    private static Scanner scanner = new Scanner(System.in);
    private static LibroControlador controlador = new LibroControlador();

    public static void mostrarMenu() {
        while (true) {
            System.out.println("\n📚 GESTIÓN DE BIBLIOTECA 📚");
            System.out.println("1. Agregar Libro");
            System.out.println("2. Listar Libros");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    controlador.agregarLibro();
                    break;
                case 2:
                    controlador.listarLibros();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
