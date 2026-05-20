package com.galeria;

// Clase que representa una tarjeta (sin imagen, solo título y texto)
public class Tarjeta {

    private int id;
    private String titulo;
    private String texto;

    // Constructor vacío (necesario para Jackson / JSON)
    public Tarjeta() {}

    public Tarjeta(int id, String titulo, String texto) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }
}
