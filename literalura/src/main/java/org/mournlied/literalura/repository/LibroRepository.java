package org.mournlied.literalura.repository;

import org.mournlied.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.List;


public interface LibroRepository extends JpaRepository <Libro,Long>{
    
    List<Libro> findLibroByLenguaje(String idiomaUsuario);
    
    @Query("SELECT l FROM Libro l JOIN l.autor a WHERE a.nombre ILIKE %:autorUsuario%")
    Optional<List<Libro>> buscarLibroPorNombreAutor(String autorUsuario);

    Optional<List<Libro>> findLibroByDescargasGreaterThanEqual(Long descargasUsuario);

    @Query("SELECT l FROM Libro l ORDER BY l.descargas DESC LIMIT 5")
    List<Libro> top5Libros();
}
