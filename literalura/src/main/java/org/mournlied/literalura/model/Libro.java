package org.mournlied.literalura.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "Libro")
@Table(name = "libros")
@Setter
@Getter
@EqualsAndHashCode(of = "libroId")
@NoArgsConstructor
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long libroId;
    @Column(unique = true)
    private Long gutendexId;
    private String titulo;
    @ManyToOne
    private Autor autor;
    private List<String> generos;
    private String lenguaje;
    private Long descargas;

    public Libro(GetDatosLibro apiDatos) {
        this.gutendexId = apiDatos.gutendexId();
        this.titulo = apiDatos.titulo();
        if ((apiDatos.autores().stream().map(Autor::new).findFirst()).isPresent()){
            this.autor = apiDatos.autores().stream().map(Autor::new).findFirst().get();
        } else {
            this.autor = new Autor();
        }
        this.generos = apiDatos.generos();
        if ((apiDatos.lenguajes().stream().findFirst()).isPresent()){
            this.lenguaje = apiDatos.lenguajes().stream().findFirst().get();
        } else {
            this.lenguaje = "ndf";
        }
        this.descargas = apiDatos.descargas();
    }

    @Override
    public String toString() {
        return  "Titulo: "+ titulo +"\n"+
                "Autor(a): "+ autor +"\n"+
                "Genero(s): " + generos +"\n"+
                "Lenguaje(s): " + lenguaje +"\n"+
                "Total de descargas: " + descargas;
    }
}
