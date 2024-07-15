package org.mournlied.literalura.repository;

import org.mournlied.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    Optional<Autor> findAutorByNombre(String nombre);
    
    @Query("SELECT a FROM Autor a WHERE :fechaUsuario between a.fechaNacimiento and a.fechaDefuncion")
    Optional<List<Autor>> autoresVivos(Integer fechaUsuario);
}
