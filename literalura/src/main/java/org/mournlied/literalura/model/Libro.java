package org.mournlied.literalura.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "Libro")
@Table(name = "libros")
@Getter
@NoArgsConstructor
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long libroId;
    @Column(unique = true)
    private Long gutendexId;
    private String titulo;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Autor> autores;
    private List<String> generos;
    private List<String> lenguajes;
    private Long descargas;

    public Libro(GetDatosLibro apiDatos) {
        this.gutendexId = apiDatos.gutendexId();
        this.titulo = apiDatos.titulo();
        this.autores = apiDatos.autores().stream().map(Autor::new).collect(Collectors.toList());
        this.generos = apiDatos.generos();
        this.lenguajes = apiDatos.lenguajes();
        this.descargas = apiDatos.descargas();
    }

    @Override
    public String toString() {
        return  "Titulo: "+ titulo +"\n"+
                "Autor(a): "+ autores +"\n"+
                "Genero(s): " + generos +"\n"+
                "Lenguaje(s): " + lenguajes +"\n"+
                "Total de descargas: " + descargas;
    }
}
