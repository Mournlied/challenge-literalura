package org.mournlied.literalura.repository;

import org.mournlied.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository <Libro,Long>{
}