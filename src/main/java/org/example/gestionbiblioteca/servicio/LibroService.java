package org.example.gestionbiblioteca.servicio;

import org.example.gestionbiblioteca.modelo.BaseXConnection;
import org.example.gestionbiblioteca.modelo.Libro;
import org.basex.core.cmd.*;

import java.util.ArrayList;
import java.util.List;

public class LibroService {

    // Método para agregar un libro
    public void agregarLibro(Libro libro) {
        try {
            String xquery = "insert node <libro id='" + libro.getId() + "'>" +
                    "<titulo>" + libro.getTitulo() + "</titulo>" +
                    "<autor>" + libro.getAutor() + "</autor>" +
                    "<genero>" + libro.getGenero() + "</genero>" +
                    "<anio>" + libro.getAnio() + "</anio>" +
                    "</libro> into //biblioteca";
            new XQuery(xquery).execute(BaseXConnection.getContext());
            System.out.println("Libro agregado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Libro> obtenerLibros() {
        List<Libro> listaLibros = new ArrayList<>();
        try {
            // Usamos db:get() para asegurarnos de que estamos consultando la base de datos correcta
            String xquery = "for $libro in db:get('Biblioteca')//libro " +
                    "return concat($libro/@id, ',', $libro/titulo, ',', $libro/autor, ',', $libro/genero, ',', $libro/anio)";

            String resultado = new XQuery(xquery).execute(BaseXConnection.getContext());

            if (resultado.isEmpty()) {
                System.out.println("No hay libros en la base de datos.");
                return listaLibros;
            }

            // Procesamos el resultado para convertirlo en objetos Libro
            String[] librosArray = resultado.split("\n");
            for (String libroCSV : librosArray) {
                if (!libroCSV.trim().isEmpty()) {
                    String[] datos = libroCSV.split(",");
                    listaLibros.add(new Libro(datos[0], datos[1], datos[2], datos[3], Integer.parseInt(datos[4])));
                }
            }

            System.out.println("Libros obtenidos correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaLibros;
    }

    public void modificarLibro(String id, String nuevoTitulo, String nuevoAutor, String nuevoGenero, int nuevoAnio) {
        try {
            String xquery =
                    "let $libro := //libro[@id='" + id + "'] " +
                            "return (" +
                            "replace value of node $libro/titulo with '" + nuevoTitulo + "'," +
                            "replace value of node $libro/autor with '" + nuevoAutor + "'," +
                            "replace value of node $libro/genero with '" + nuevoGenero + "'," +
                            "replace value of node $libro/anio with '" + nuevoAnio + "'" +
                            ")";

            new XQuery(xquery).execute(BaseXConnection.getContext());
            System.out.println("Libro con ID " + id + " modificado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void eliminarLibro(String id) {
        try {
            String xquery = "delete node //libro[@id='" + id + "']";
            new XQuery(xquery).execute(BaseXConnection.getContext());
            System.out.println("Libro con ID " + id + " eliminado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
