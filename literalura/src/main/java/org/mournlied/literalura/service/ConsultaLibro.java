package org.mournlied.literalura.service;

import org.mournlied.literalura.model.GetDatosLibro;
import org.mournlied.literalura.model.GetListaLibros;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ConsultaLibro {
    private final String URL_BASE = "https://gutendex.com/books";
    ConsumeAPI consumeAPI = new ConsumeAPI();
    ConvierteDatos convierteDatos = new ConvierteDatos();
    
    public GetListaLibros consultaLibrosPorTitulo(String tituloUsuario){
        var json = consumeAPI.obtenerDatos(URL_BASE+"?search="+URLEncoder.encode(tituloUsuario,StandardCharsets.UTF_8));
        return convierteDatos.parseDatos(json, GetListaLibros.class);
    }
    public GetListaLibros consultaLibrosPorLenguaje(String lenguajeUsuario){
        var json = consumeAPI.obtenerDatos(URL_BASE+"?languages="+lenguajeUsuario);
        return  convierteDatos.parseDatos(json, GetListaLibros.class);
    }
    public GetListaLibros consultaLibrosPorFechaAutores(Integer fechaInicial, Integer fechaFinal, int opcion){
        GetListaLibros resultados = null;
        if (opcion == 1){
            var json = consumeAPI.obtenerDatos(URL_BASE+"?author_year_start="+fechaInicial);
            resultados = convierteDatos.parseDatos(json, GetListaLibros.class);
        } else if (opcion == 2) {
            var json = consumeAPI.obtenerDatos(URL_BASE+"?author_year_end="+fechaFinal);
            resultados = convierteDatos.parseDatos(json, GetListaLibros.class);
        } else {
            var json = consumeAPI.obtenerDatos(URL_BASE+"?author_year_start="+fechaInicial+"&author_year_end="+fechaFinal);
            resultados = convierteDatos.parseDatos(json, GetListaLibros.class);
        }
        return  resultados;
    }
    public GetDatosLibro consultarLibroPorId(Long id){
        var json = consumeAPI.obtenerDatos(URL_BASE+"/"+id);
        return  convierteDatos.parseDatos(json, GetDatosLibro.class);
    }
}
