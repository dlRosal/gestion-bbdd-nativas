package org.example.gestionbiblioteca.modelo;

public class Libro {
    private String id;
    private String titulo;
    private String autor;
    private String genero;
    private int anio;

    public Libro(String id, String titulo, String autor, String genero, int anio) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.anio = anio;
    }

    // Getters y setters
}
