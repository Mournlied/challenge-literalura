package org.mournlied.literalura.service;

import org.mournlied.literalura.model.GetDatosLibro;
import org.mournlied.literalura.model.GetListaLibros;
import org.mournlied.literalura.model.Libro;

import java.util.List;

public class SeleccionaLibro {
    ConsultaLibro consulta = new ConsultaLibro();
    public void mostrarLista(GetListaLibros resultados){
        List<GetDatosLibro> listadoRaw = resultados.resultados();
        List<Libro> listado = listadoRaw.stream().map(Libro::new).toList();
        System.out.println("Total de resultados: "+resultados.total());
        listado.forEach(l-> System.out.println("ID:"+l.getGutendexId()+"-- Titulo: "+l.getTitulo()+"-- Autor(a) :"+l.getAutores()+"-- Descargas: "+l.getDescargas()));
    }
    public Libro mostrarLibroSeleccionado(Long id){
        Libro libroUsuario = new Libro(consulta.consultarLibroPorId(id));
        System.out.println(libroUsuario);
        return libroUsuario;
    }
}
