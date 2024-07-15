package org.mournlied.literalura.model;

import java.util.List;

public record LibroDTO(
        String titulo,
        Autor autor,
        List<String> generos,
        String lenguaje,
        Long descargas) {
    public LibroDTO(String titulo, Autor autor, List<String> generos, String lenguaje, Long descargas) {
        this.titulo = titulo;
        this.autor = autor;
        this.generos = generos;
        this.lenguaje = lenguaje;
        this.descargas = descargas;
    }
} 
