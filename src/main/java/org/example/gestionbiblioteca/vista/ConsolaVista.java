package org.example.gestionbiblioteca.vista;

import org.example.gestionbiblioteca.controlador.LibroControlador;
import java.util.Scanner;

public class ConsolaVista {
    private static final Scanner scanner = new Scanner(System.in);
    private static final LibroControlador controlador = new LibroControlador();

    public static void mostrarMenu() {
        while (true) {
            System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("          📚 GESTIÓN DE BIBLIOTECA 📚          ");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println(" 1️⃣  ➤ Agregar Libro");
            System.out.println(" 2️⃣  ➤ Listar Libros");
            System.out.println(" 3️⃣  ➤ Modificar un Libro");
            System.out.println(" 4️⃣  ➤ Eliminar un Libro");
            System.out.println(" 5️⃣  ➤ Crear Colección");
            System.out.println(" 6️⃣  ➤ Eliminar Colección");
            System.out.println(" 7️⃣  ➤ Buscar Libro por ID");
            System.out.println(" 8️⃣  ➤ Buscar Libro por Título");
            System.out.println(" 9️⃣  ➤ Buscar Libros por Autor");
            System.out.println(" 🔟  🔴 Salir");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.print(" 📝 Selecciona una opción: ");

            int opcion;
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Ingresa un número.");
                continue;
            }

            switch (opcion) {
                case 1:
                    controlador.agregarLibro();
                    break;
                case 2:
                    controlador.listarLibros();
                    break;
                case 3:
                    controlador.modificarLibro();
                    break;
                case 4:
                    controlador.eliminarLibro();
                    break;
                case 5:
                    controlador.crearColeccion();
                    break;
                case 6:
                    controlador.eliminarColeccion();
                    break;
                case 7:
                    controlador.buscarLibroPorId();
                    break;
                case 8:
                    controlador.buscarLibroPorTitulo();
                    break;
                case 9:
                    controlador.buscarLibrosPorAutor();
                    break;
                case 10:
                    System.out.println("\n👋 ¡Gracias por usar la Biblioteca! Hasta pronto.");
                    return;
                default:
                    System.out.println("❌ Opción inválida. Intenta de nuevo.");
            }
        }
    }
}
