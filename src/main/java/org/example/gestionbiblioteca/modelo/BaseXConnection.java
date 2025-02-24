package org.example.gestionbiblioteca.modelo;

import org.basex.core.*;

public class BaseXConnection {
    private static Context context = new Context();

    public static Context getContext() {
        return context;
    }

    public static void cerrarConexion() {
        context.close();
    }
}
