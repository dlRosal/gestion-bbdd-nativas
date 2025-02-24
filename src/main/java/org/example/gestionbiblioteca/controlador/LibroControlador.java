package org.example.gestionbiblioteca.controlador;

import org.example.gestionbiblioteca.servicio.LibroService;
import org.example.gestionbiblioteca.modelo.Libro;

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

        Libro libro = new Libro(UUID.randomUUID().toString(), titulo, autor, genero, anio);
        libroService.agregarLibro(libro);
    }

    public void listarLibros() {
        libroService.obtenerLibros();
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
