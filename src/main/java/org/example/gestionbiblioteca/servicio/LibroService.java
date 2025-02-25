package org.example.gestionbiblioteca.servicio;

import org.example.gestionbiblioteca.modelo.BaseXConnection;
import org.example.gestionbiblioteca.modelo.Libro;
import org.basex.api.client.ClientSession;
import java.util.ArrayList;
import java.util.List;

public class LibroService {

    // ✅ Crear una colección dentro de la Biblioteca
    public void crearColeccion(String nombreColeccion) {
        try {
            ClientSession session = BaseXConnection.getSession();
            String checkCollection = session.execute("XQUERY collection('Biblioteca')//coleccion[@nombre='" + nombreColeccion + "']");
            if (!checkCollection.isEmpty()) {
                System.out.println("❌ La colección '" + nombreColeccion + "' ya existe.");
                return;
            }
            String xqueryCreate = "insert node <coleccion nombre='" + nombreColeccion + "'></coleccion> into collection('Biblioteca')//biblioteca";
            session.execute("XQUERY " + xqueryCreate);
            System.out.println("✅ Colección '" + nombreColeccion + "' creada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ Eliminar una colección dentro de la Biblioteca
    public void eliminarColeccion(String nombreColeccion) {
        try {
            ClientSession session = BaseXConnection.getSession();
            String checkCollection = session.execute("XQUERY collection('Biblioteca')//coleccion[@nombre='" + nombreColeccion + "']");
            if (checkCollection.isEmpty()) {
                System.out.println("❌ La colección '" + nombreColeccion + "' no existe.");
                return;
            }
            String xqueryDelete = "delete node collection('Biblioteca')//coleccion[@nombre='" + nombreColeccion + "']";
            session.execute("XQUERY " + xqueryDelete);
            System.out.println("✅ Colección '" + nombreColeccion + "' eliminada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ Agregar un libro en una colección
    public void agregarLibro(String nombreColeccion, String titulo, String autor, String genero, int anio) {
        try {
            ClientSession session = BaseXConnection.getSession();

            // Obtener el último ID de los libros dentro de la colección
            String xqueryMaxId = "let $max := max(collection('Biblioteca')//coleccion[@nombre='" + nombreColeccion + "']/libro/@id) " +
                    "return if ($max) then $max else 0";
            String resultadoMaxId = session.execute("XQUERY " + xqueryMaxId).trim();

            int nuevoId = resultadoMaxId.matches("\\d+") ? Integer.parseInt(resultadoMaxId) + 1 : 1;

            // Insertar el nuevo libro con ID autoincremental
            String xqueryInsert = "insert node <libro id='" + nuevoId + "'>" +
                    "<titulo>" + titulo + "</titulo>" +
                    "<autor>" + autor + "</autor>" +
                    "<genero>" + genero + "</genero>" +
                    "<anio>" + anio + "</anio>" +
                    "</libro> into collection('Biblioteca')//coleccion[@nombre='" + nombreColeccion + "']";

            session.execute("XQUERY " + xqueryInsert);
            System.out.println("✅ Libro agregado correctamente con ID: " + nuevoId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // ✅ Obtener libros de una colección
    public List<Libro> obtenerLibros(String nombreColeccion) {
        List<Libro> listaLibros = new ArrayList<>();
        try {
            ClientSession session = BaseXConnection.getSession();

            // ✅ Modificación: aseguramos que el ID y Año se extraigan correctamente
            String xquery = "XQUERY for $libro in collection('Biblioteca')//coleccion[@nombre='" + nombreColeccion + "']/libro " +
                    "return concat(string($libro/@id), '|', string($libro/titulo), '|', " +
                    "string($libro/autor), '|', string($libro/genero), '|', string($libro/anio))";

            String resultado = session.execute(xquery).trim();

            if (resultado.isEmpty()) {
                return listaLibros; // Si no hay libros, devuelve una lista vacía
            }

            // ✅ Procesar correctamente cada libro
            String[] librosArray = resultado.split("\n");
            for (String libroCSV : librosArray) {
                if (!libroCSV.trim().isEmpty()) {
                    String[] datos = libroCSV.split("\\|");
                    if (datos.length == 5) {
                        String id = datos[0].trim().isEmpty() ? "0" : datos[0].trim();
                        String titulo = datos[1].trim();
                        String autor = datos[2].trim();
                        String genero = datos[3].trim();

                        int anio = 0;
                        try {
                            anio = Integer.parseInt(datos[4].trim());
                        } catch (NumberFormatException e) {
                            System.out.println("⚠️ Error al convertir el año para '" + titulo + "'. Se asigna 0.");
                        }

                        listaLibros.add(new Libro(id, titulo, autor, genero, anio));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaLibros;
    }





    // ✅ Modificar un libro en una colección
    public void modificarLibro(String nombreColeccion, String tituloAntiguo, String tituloNuevo, String autorNuevo, String generoNuevo, int anioNuevo) {
        try {
            ClientSession session = BaseXConnection.getSession();
            String xqueryUpdate = "replace value of node collection('Biblioteca')//coleccion[@nombre='" + nombreColeccion + "']/libro[titulo='" + tituloAntiguo + "'] with <libro>" +
                    "<titulo>" + tituloNuevo + "</titulo>" +
                    "<autor>" + autorNuevo + "</autor>" +
                    "<genero>" + generoNuevo + "</genero>" +
                    "<anio>" + anioNuevo + "</anio>" +
                    "</libro>";
            session.execute("XQUERY " + xqueryUpdate);
            System.out.println("✅ Libro modificado correctamente en la colección '" + nombreColeccion + "'.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ Eliminar un libro de una colección
    public void eliminarLibro(String nombreColeccion, String titulo) {
        try {
            ClientSession session = BaseXConnection.getSession();
            String xqueryDelete = "delete node collection('Biblioteca')//coleccion[@nombre='" + nombreColeccion + "']/libro[titulo='" + titulo + "']";
            session.execute("XQUERY " + xqueryDelete);
            System.out.println("✅ Libro eliminado correctamente de la colección '" + nombreColeccion + "'.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
