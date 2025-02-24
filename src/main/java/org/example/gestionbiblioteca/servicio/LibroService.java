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

    // Método para obtener todos los libros
    public List<Libro> obtenerLibros() {
        List<Libro> listaLibros = new ArrayList<>();
        try {
            String resultado = new XQuery("//libro").execute(BaseXConnection.getContext());
            System.out.println("Lista de libros:\n" + resultado);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaLibros;
    }
}
