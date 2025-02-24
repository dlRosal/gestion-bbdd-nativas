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
}
