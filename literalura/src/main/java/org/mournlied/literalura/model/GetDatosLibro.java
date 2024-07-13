package org.mournlied.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GetDatosLibro(
        @JsonAlias("id") Long gutendexId,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<GetDatosAutores> autores,
        @JsonAlias("subjects") List<String> generos,
        @JsonAlias("languages") List<String> lenguajes,
        @JsonAlias("download_count") Long descargas) {
}
