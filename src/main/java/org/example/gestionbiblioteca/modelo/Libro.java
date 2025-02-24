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

    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getGenero() { return genero; }
    public int getAnio() { return anio; }
}
