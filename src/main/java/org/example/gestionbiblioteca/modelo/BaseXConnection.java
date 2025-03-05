package org.example.gestionbiblioteca.modelo;

import org.basex.api.client.ClientSession;

public class BaseXConnection {
    private static ClientSession session;

    static {
        try {
            // Conectar al servidor BaseX en localhost, puerto 1984, usuario "admin", sin contraseña
            session = new ClientSession("localhost", 1984, "admin", "admin");
            session.execute("OPEN Biblioteca"); // Abrir la base de datos
            System.out.println("✅ Conectado a la base de datos en BaseX Server.");
        } catch (Exception e) {
            System.out.println("⚠️ No se pudo conectar a BaseX Server.");
            e.printStackTrace();
        }
    }

    public static ClientSession getSession() {
        return session;
    }

    public static void cerrarConexion() {
        try {
            session.close();
            System.out.println("✅ Conexión con BaseX cerrada.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
