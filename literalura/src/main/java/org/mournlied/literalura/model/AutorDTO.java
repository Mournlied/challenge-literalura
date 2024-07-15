package org.mournlied.literalura.model;

public record AutorDTO(
        String nombre,
        Integer fechaNacimiento,
        Integer fechaDefuncion) {
    public AutorDTO(String nombre, Integer fechaNacimiento, Integer fechaDefuncion) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaDefuncion = fechaDefuncion;
    }
}
