package org.mournlied.literalura.service;


import org.mournlied.literalura.service.errores.FormatoNombreIncorrecto;

public class FormatoNombre {
    public String formatearNombre(String nombreOrigen) throws FormatoNombreIncorrecto {
        String[] nombreDividido = nombreOrigen.split(", ");
        if (nombreDividido.length!=2){
            throw new FormatoNombreIncorrecto();
        }
        String apellido = nombreDividido[0];
        String nombre = nombreDividido[1];

        return nombre + " " + apellido;
    }
}
