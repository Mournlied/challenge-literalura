package org.mournlied.literalura.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mournlied.literalura.service.FormatoNombre;

import java.util.List;

@Entity(name = "Autor")
@Table(name = "autores")
@Getter
@NoArgsConstructor
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long autorId;
    @Column(unique = true)
    private String nombre;
    private Integer fechaNacimiento;
    private Integer fechaDefuncion;
    @ManyToMany(mappedBy = "autores", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Libro> libros;
    @Transient
    private FormatoNombre formato = new FormatoNombre();

    public Autor(GetDatosAutores apiAutores){
        try {
            this.nombre = formato.formatearNombre(apiAutores.nombre());
        } catch (Exception e) {
            this.nombre = apiAutores.nombre();
        }
        this.fechaNacimiento = apiAutores.fechaNacimiento();
        this.fechaDefuncion = apiAutores.fechaDefuncion();
    }

    @Override
    public String toString() {
        return nombre;
    }
}
