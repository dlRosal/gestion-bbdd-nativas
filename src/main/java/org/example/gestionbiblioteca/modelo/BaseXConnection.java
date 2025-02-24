package org.example.gestionbiblioteca.modelo;

import org.basex.core.*;
import org.basex.core.cmd.*;

public class BaseXConnection {
    private static final Context context = new Context();
    private static final String DATABASE_NAME = "Biblioteca";

    static {
        try {
            // Intentamos abrir la base de datos
            new Open(DATABASE_NAME).execute(context);
            System.out.println("✅ Conectado a la base de datos: " + DATABASE_NAME);
        } catch (Exception e) {
            System.out.println("⚠️ No se encontró la base de datos, creándola...");
            try {
                new CreateDB(DATABASE_NAME, "<biblioteca/>").execute(context);
                System.out.println("✅ Base de datos '" + DATABASE_NAME + "' creada correctamente.");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static Context getContext() {
        return context;
    }

    public static void cerrarConexion() {
        try {
            new Close().execute(context);
            context.close();
            System.out.println("✅ Conexión con BaseX cerrada.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
