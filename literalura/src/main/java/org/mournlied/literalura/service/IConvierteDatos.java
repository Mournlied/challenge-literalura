package org.mournlied.literalura.service;

public interface IConvierteDatos {
    <T> T parseDatos(String json, Class<T> clase);
}
