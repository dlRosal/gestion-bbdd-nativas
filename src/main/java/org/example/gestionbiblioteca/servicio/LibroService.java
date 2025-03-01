package org.example.gestionbiblioteca.servicio;

import org.example.gestionbiblioteca.modelo.BaseXConnection;
import org.example.gestionbiblioteca.modelo.Libro;
import org.basex.api.client.ClientSession;
import java.util.ArrayList;
import java.util.List;

public class LibroService {

    //  Crear una colección dentro de la Biblioteca
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

    //  Eliminar una colección dentro de la Biblioteca
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

    public void agregarLibro(String nombreColeccion, String titulo, String autor, String genero, int anio) {
        try {
            ClientSession session = BaseXConnection.getSession();

            //  Obtener el último ID de los libros dentro de la colección para autoincrementar
            String xqueryMaxId = "let $max := max(for $libro in collection('Biblioteca')//coleccion[@nombre='" + nombreColeccion + "']/libro return number($libro/@id)) " +
                    "return if ($max) then $max else 0";
            String resultadoMaxId = session.execute("XQUERY " + xqueryMaxId).trim();
            int nuevoId = resultadoMaxId.matches("\\d+") ? Integer.parseInt(resultadoMaxId) + 1 : 1;

            String xqueryInsert = "XQUERY insert node element libro { " +
                    "attribute id { '" + nuevoId + "' }, " +
                    "element titulo { '" + titulo + "' }, " +
                    "element autor { '" + autor + "' }, " +
                    "element genero { '" + genero + "' }, " +
                    "element anio { '" + anio + "' } " +
                    "} into collection('Biblioteca')//coleccion[@nombre=\"" + nombreColeccion + "\"]";



            session.execute(xqueryInsert);

            //  Asegurar la correcta estructura del XML
            session.execute("OPTIMIZE ALL");

            System.out.println("✅ Libro agregado correctamente con ID: " + nuevoId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Libro> obtenerLibros(String nombreColeccion) {
        List<Libro> listaLibros = new ArrayList<>();
        try {
            ClientSession session = BaseXConnection.getSession();

            String xquery = "XQUERY for $libro in collection('Biblioteca')//coleccion[@nombre='" + nombreColeccion + "']/libro " +
                    "return concat(string($libro/@id), '|', string($libro/titulo), '|', " +
                    "string($libro/autor), '|', string($libro/genero), '|', string($libro/anio))";

            String resultado = session.execute(xquery).trim();

            if (resultado.isEmpty()) {
                return listaLibros;
            }

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

    public void modificarLibro(String nombreColeccion, String idLibro, String nuevoTitulo, String nuevoAutor, String nuevoGenero, int nuevoAnio) {
        try {
            ClientSession session = BaseXConnection.getSession();

            String xqueryCheck = "XQUERY exists(collection('Biblioteca')//coleccion[@nombre='" + nombreColeccion + "']/libro[@id='" + idLibro + "'])";
            String existe = session.execute(xqueryCheck).trim();

            if ("false".equals(existe)) {
                System.out.println("❌ El libro con ID " + idLibro + " no existe en la colección '" + nombreColeccion + "'.");
                return;
            }

            String xqueryUpdateTitulo = "replace value of node collection('Biblioteca')//coleccion[@nombre='" + nombreColeccion + "']/libro[@id='" + idLibro + "']/titulo " +
                    "with '" + nuevoTitulo + "'";
            String xqueryUpdateAutor = "replace value of node collection('Biblioteca')//coleccion[@nombre='" + nombreColeccion + "']/libro[@id='" + idLibro + "']/autor " +
                    "with '" + nuevoAutor + "'";
            String xqueryUpdateGenero = "replace value of node collection('Biblioteca')//coleccion[@nombre='" + nombreColeccion + "']/libro[@id='" + idLibro + "']/genero " +
                    "with '" + nuevoGenero + "'";
            String xqueryUpdateAnio = "replace value of node collection('Biblioteca')//coleccion[@nombre='" + nombreColeccion + "']/libro[@id='" + idLibro + "']/anio " +
                    "with '" + nuevoAnio + "'";

            session.execute("XQUERY " + xqueryUpdateTitulo);
            session.execute("XQUERY " + xqueryUpdateAutor);
            session.execute("XQUERY " + xqueryUpdateGenero);
            session.execute("XQUERY " + xqueryUpdateAnio);

            session.execute("OPTIMIZE ALL");

            System.out.println("✅ Libro con ID " + idLibro + " modificado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  Eliminar un libro de una colección
    public void eliminarLibroPorId(String nombreColeccion, int idLibro) {
        try {
            ClientSession session = BaseXConnection.getSession();

            //  Verificar si el libro con ese ID existe antes de intentar eliminarlo
            String xqueryCheck = "XQUERY count(collection('Biblioteca')//coleccion[@nombre=\"" + nombreColeccion + "\"]/libro[@id='" + idLibro + "'])";
            String resultadoCheck = session.execute(xqueryCheck).trim();

            if (resultadoCheck.equals("0")) {
                System.out.println("❌ El libro con ID '" + idLibro + "' no existe en la colección '" + nombreColeccion + "'.");
                return;
            }

            //  Eliminar el nodo del libro correctamente usando el ID
            String xqueryDelete = "XQUERY delete node collection('Biblioteca')//coleccion[@nombre=\"" + nombreColeccion + "\"]/libro[@id='" + idLibro + "']";
            session.execute(xqueryDelete);

            //  Optimizar la base de datos para evitar problemas con la estructura
            session.execute("OPTIMIZE ALL");

            System.out.println("✅ Libro con ID '" + idLibro + "' eliminado correctamente de la colección '" + nombreColeccion + "'.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
