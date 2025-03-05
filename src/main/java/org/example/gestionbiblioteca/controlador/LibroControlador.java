package org.example.gestionbiblioteca.controlador;

import org.example.gestionbiblioteca.modelo.Libro;
import org.example.gestionbiblioteca.servicio.LibroService;

import java.util.List;
import java.util.Scanner;

public class LibroControlador {
    private final LibroService libroService;
    private final Scanner scanner;

    public LibroControlador() {
        this.libroService = new LibroService();
        this.scanner = new Scanner(System.in);
    }

    // ✅ Crear una nueva colección
    public void crearColeccion() {
        System.out.print("📂 Ingrese el nombre de la nueva colección: ");
        String nombreColeccion = scanner.nextLine();
        libroService.crearColeccion(nombreColeccion);
    }

    // ✅ Eliminar una colección
    public void eliminarColeccion() {
        System.out.print("🗑️ Ingrese el nombre de la colección a eliminar: ");
        String nombreColeccion = scanner.nextLine();
        libroService.eliminarColeccion(nombreColeccion);
    }

    // ✅ Agregar un libro a una colección
    public void agregarLibro() {
        System.out.print("📂 Ingrese el nombre de la colección: ");
        String nombreColeccion = scanner.nextLine();
        System.out.print("📖 Ingrese el título del libro: ");
        String titulo = scanner.nextLine();
        System.out.print("👤 Ingrese el autor: ");
        String autor = scanner.nextLine();
        System.out.print("📚 Ingrese el género: ");
        String genero = scanner.nextLine();
        System.out.print("📅 Ingrese el año de publicación: ");
        int anio = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        libroService.agregarLibro(nombreColeccion, titulo, autor, genero, anio);
    }

    // ✅ Listar libros de una colección
    public void listarLibros() {
        System.out.print("📂 Ingrese el nombre de la colección: ");
        String nombreColeccion = scanner.nextLine().trim();

        List<Libro> listaLibros = libroService.obtenerLibros(nombreColeccion);

        if (listaLibros.isEmpty()) {
            System.out.println("❌ No hay libros en la colección '" + nombreColeccion + "'.");
            return;
        }

        System.out.println("\n📚 Libros en la colección '" + nombreColeccion + "':");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        for (Libro libro : listaLibros) {
            System.out.println("📖 ID: " + libro.getId() +
                    " | Título: " + libro.getTitulo() +
                    " | Autor: " + libro.getAutor() +
                    " | Género: " + libro.getGenero() +
                    " | Año: " + libro.getAnio());
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        }
    }

    // ✅ Modificar un libro en una colección
    public void modificarLibro() {
        System.out.print("📂 Ingrese el nombre de la colección: ");
        String nombreColeccion = scanner.nextLine();
        System.out.print("Ingrese el id del libro a modificar: ");
        String tituloAntiguo = scanner.nextLine();
        System.out.print("📖 Nuevo título: ");
        String tituloNuevo = scanner.nextLine();
        System.out.print("👤 Nuevo autor: ");
        String autorNuevo = scanner.nextLine();
        System.out.print("📚 Nuevo género: ");
        String generoNuevo = scanner.nextLine();
        System.out.print("📅 Nuevo año de publicación: ");
        int anioNuevo = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        libroService.modificarLibro(nombreColeccion, tituloAntiguo, tituloNuevo, autorNuevo, generoNuevo, anioNuevo);
    }

    // ✅ Eliminar un libro de una colección por ID
    public void eliminarLibro() {
        System.out.print("📂 Ingrese el nombre de la colección: ");
        String nombreColeccion = scanner.nextLine();
        System.out.print("📖 Ingrese el ID del libro a eliminar: ");

        // ✅ Validación para asegurarse de que el usuario ingrese un número
        int idLibro;
        try {
            idLibro = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Debe ingresar un número válido para el ID.");
            return;
        }

        libroService.eliminarLibroPorId(nombreColeccion, idLibro);
    }
    public void buscarLibroPorId() {
        System.out.print("📂 Ingrese el nombre de la colección: ");
        String nombreColeccion = scanner.nextLine();
        System.out.print("🔍 Ingrese el ID del libro: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir la línea

        Libro libro = libroService.buscarLibroPorId(nombreColeccion, id);
        if (libro != null) {
            System.out.println(libro);
        }
    }

    public void buscarLibroPorTitulo() {
        System.out.print("📂 Ingrese el nombre de la colección: ");
        String nombreColeccion = scanner.nextLine();
        System.out.print("🔍 Ingrese el título del libro: ");
        String titulo = scanner.nextLine();

        Libro libro = libroService.buscarLibroPorTitulo(nombreColeccion, titulo);
        if (libro != null) {
            System.out.println(libro);
        }
    }

    public void buscarLibrosPorAutor() {
        System.out.print("📂 Ingrese el nombre de la colección: ");
        String nombreColeccion = scanner.nextLine();
        System.out.print("🔍 Ingrese el nombre del autor: ");
        String autor = scanner.nextLine();

        List<Libro> libros = libroService.buscarLibrosPorAutor(nombreColeccion, autor);
        if (!libros.isEmpty()) {
            libros.forEach(System.out::println);
        } else {
            System.out.println("❌ No se encontraron libros de " + autor);
        }
    }


}
