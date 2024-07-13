package org.mournlied.literalura.model;

import java.util.List;

public record LibroDTO(
        String titulo,
        List<Autor>autores,
        List<String> generos,
        List<String> lenguajes,
        Long descargas) {
    }
