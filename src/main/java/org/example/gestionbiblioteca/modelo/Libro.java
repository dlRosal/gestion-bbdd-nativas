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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    @Override
    public String toString() {
        return "📖 ID: " + id +
                " | Título: " + titulo +
                " | Autor: " + autor +
                " | Género: " + genero +
                " | Año: " + anio;
    }
}
