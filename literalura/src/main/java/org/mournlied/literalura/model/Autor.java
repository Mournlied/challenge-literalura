package org.mournlied.literalura.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mournlied.literalura.service.FormatoNombre;
import org.mournlied.literalura.service.errores.FormatoNombreIncorrecto;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Autor")
@Table(name = "autores")
@Getter
@Setter
@EqualsAndHashCode(of = "nombre")
@NoArgsConstructor
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long autorId;
    @Column(unique = true)
    private String nombre;
    private Integer fechaNacimiento;
    private Integer fechaDefuncion;
    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Libro> libros = new ArrayList<Libro>();
    @Transient
    private FormatoNombre formato = new FormatoNombre();

    public Autor(GetDatosAutores apiAutores){
        try {
            this.nombre = formato.formatearNombre(apiAutores.nombre());
        } catch (FormatoNombreIncorrecto e) {
            this.nombre = apiAutores.nombre();
        }
        this.fechaNacimiento = apiAutores.fechaNacimiento();
        this.fechaDefuncion = apiAutores.fechaDefuncion();
    }

    public void addLibro(Libro libro) {
        this.libros.add(libro);
        libro.setAutor(this);
    }

    @Override
    public String toString() {
        return nombre;
    }
}
