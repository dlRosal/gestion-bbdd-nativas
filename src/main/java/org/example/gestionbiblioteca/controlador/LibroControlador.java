package org.example.gestionbiblioteca.controlador;

import org.example.gestionbiblioteca.servicio.LibroService;
import org.example.gestionbiblioteca.modelo.Libro;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class LibroControlador {
    private LibroService libroService = new LibroService();
    private Scanner scanner = new Scanner(System.in);

    public void agregarLibro() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Autor: ");
        String autor = scanner.nextLine();

        System.out.print("Género: ");
        String genero = scanner.nextLine();

        System.out.print("Año: ");
        int anio = scanner.nextInt();
        scanner.nextLine();

        libroService.agregarLibro(titulo, autor, genero, anio);
    }


    public void listarLibros() {
        List<Libro> libros = libroService.obtenerLibros();

        if (libros.isEmpty()) {
            System.out.println("❌ No hay libros en la base de datos.");
        } else {
            System.out.println("\n📚 LISTADO DE LIBROS 📚");
            for (Libro libro : libros) {
                System.out.println("📖 ID: " + libro.getId() +
                        " | Título: " + libro.getTitulo() +
                        " | Autor: " + libro.getAutor() +
                        " | Género: " + libro.getGenero() +
                        " | Año: " + libro.getAnio());
            }
        }
    }
    public void modificarLibro() {
        System.out.print("Ingrese el ID del libro a modificar: ");
        String id = scanner.nextLine();

        System.out.print("Nuevo título: ");
        String nuevoTitulo = scanner.nextLine();

        System.out.print("Nuevo autor: ");
        String nuevoAutor = scanner.nextLine();

        System.out.print("Nuevo género: ");
        String nuevoGenero = scanner.nextLine();

        System.out.print("Nuevo año: ");
        int nuevoAnio = scanner.nextInt();
        scanner.nextLine();

        libroService.modificarLibro(id, nuevoTitulo, nuevoAutor, nuevoGenero, nuevoAnio);
    }

    public void eliminarLibro() {
        System.out.print("Ingrese el ID del libro a eliminar: ");
        String id = scanner.nextLine();

        libroService.eliminarLibro(id);
    }

}
