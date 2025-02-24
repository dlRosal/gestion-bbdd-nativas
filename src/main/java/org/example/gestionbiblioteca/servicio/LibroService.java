package org.example.gestionbiblioteca.servicio;

import org.example.gestionbiblioteca.modelo.BaseXConnection;
import org.example.gestionbiblioteca.modelo.Libro;
import org.basex.api.client.ClientSession;

import java.util.ArrayList;
import java.util.List;

public class LibroService {

    // Método para agregar un libro
    public void agregarLibro(String titulo, String autor, String genero, int anio) {
        try {
            ClientSession session = BaseXConnection.getSession();

            // Obtener el ID más alto de la base de datos
            String xqueryMaxId = "let $max := max((/biblioteca/libro/@id)) return if ($max) then $max else 0";
            String resultadoMaxId = session.execute("XQUERY " + xqueryMaxId);

            // Convertir el resultado en un número y calcular el nuevo ID
            int nuevoId = Integer.parseInt(resultadoMaxId.trim()) + 1;

            // Insertar el nuevo libro con el ID autoincrementado
            String xqueryInsert = "insert node <libro id='" + nuevoId + "'>" +
                    "<titulo>" + titulo + "</titulo>" +
                    "<autor>" + autor + "</autor>" +
                    "<genero>" + genero + "</genero>" +
                    "<anio>" + anio + "</anio>" +
                    "</libro> into /biblioteca";

            session.execute("XQUERY " + xqueryInsert);
            System.out.println("✅ Libro agregado correctamente con ID: " + nuevoId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Método para obtener todos los libros
    public List<Libro> obtenerLibros() {
        List<Libro> listaLibros = new ArrayList<>();
        try {
            ClientSession session = BaseXConnection.getSession();
            String xquery = "for $libro in db:get('Biblioteca')//libro " +
                    "return concat($libro/@id, '|', $libro/titulo, '|', $libro/autor, '|', $libro/genero, '|', $libro/anio)";

            String resultado = session.execute("XQUERY " + xquery);

            if (resultado.isEmpty()) {
                return listaLibros; // No imprimimos aquí, solo devolvemos la lista vacía
            }

            String[] librosArray = resultado.split("\n");
            for (String libroCSV : librosArray) {
                if (!libroCSV.trim().isEmpty()) {
                    String[] datos = libroCSV.split("\\|");

                    if (datos.length == 5) {
                        try {
                            int anio = Integer.parseInt(datos[4].trim());
                            listaLibros.add(new Libro(datos[0], datos[1], datos[2], datos[3], anio));
                        } catch (NumberFormatException e) {
                            System.out.println("⚠️ Error al convertir el año para el libro: " + datos[1]);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaLibros;
    }



    // Método para modificar un libro
    public void modificarLibro(String id, String nuevoTitulo, String nuevoAutor, String nuevoGenero, int nuevoAnio) {
        try {
            ClientSession session = BaseXConnection.getSession();
            String xquery =
                    "let $libro := /biblioteca/libro[@id='" + id + "'] " +
                            "return (" +
                            "replace value of node $libro/titulo with '" + nuevoTitulo + "'," +
                            "replace value of node $libro/autor with '" + nuevoAutor + "'," +
                            "replace value of node $libro/genero with '" + nuevoGenero + "'," +
                            "replace value of node $libro/anio with '" + nuevoAnio + "'" +
                            ")";

            session.execute("XQUERY " + xquery);
            System.out.println("✅ Libro con ID " + id + " modificado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un libro
    public void eliminarLibro(String id) {
        try {
            ClientSession session = BaseXConnection.getSession();
            String xquery = "delete node /biblioteca/libro[@id='" + id + "']";

            session.execute("XQUERY " + xquery);
            System.out.println("✅ Libro con ID " + id + " eliminado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
