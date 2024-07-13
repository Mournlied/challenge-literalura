package org.mournlied.literalura.principal;

import org.mournlied.literalura.repository.LibroRepository;
import org.mournlied.literalura.service.ConsultaLibro;
import org.mournlied.literalura.service.SeleccionaLibro;

import java.util.Scanner;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConsultaLibro consultaLibro = new ConsultaLibro();
    private SeleccionaLibro seleccionaLibro = new SeleccionaLibro();
    private LibroRepository repositorio;

    public Principal(LibroRepository repository) {
        this.repositorio = repository;
    }
    
    public void showMenu(){
        int ayudaMenuPrincipal = -1;
        System.out.println("Bienvenida o bienvenido al catálogo de libros Literalura!");
        while (ayudaMenuPrincipal !=0){
            System.out.println("""
                    
                    *****************************************
                    
                    Por favor, ingrese la opción que desea realizar:
                    
                    1- Buscar libro por título
                    2- Buscar libros por autor
                    3- Buscar libros por fecha de lanzamiento
                    4- Buscar libros por idioma
                    5- Mostrar lista completa de libros
                    6- Mostrar lista de libros por idioma
                    7- Buscar autores vivos por fecha
                    8- Mostrar lista completa de autores
                    
                    0- Salir de la aplicación
                    
                    *****************************************
                    
                    """);
            var opcionMenuPrincipal = scanner.nextInt();
            scanner.nextLine();
            switch (opcionMenuPrincipal){
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    buscarLibrosPorAutor();
                    break;
                case 3:
                    buscarLibrosPorLanzamiento();
                    break;
                case 4:
                    buscarLibrosPorIdioma();
                    break;
                case 5:
                    mostrarListaLibros();
                    break;
                case 6:
                    mostrarListaLibrosPorIdioma();
                    break;
                case 7:
                    buscarAutoresVivosPorFecha();
                    break;
                case 8:
                    mostrarListaAutores();
                    break;
                case 951:
                    mostrarMenuDesarrollador();
                    break;
                case 0:
                    System.out.println("Saliendo de la aplicación...");
                    System.out.println("Gracias por usar nuestro servicio");
                    ayudaMenuPrincipal = 0;
                    break;
                default:
                    System.out.println("** Opción invalida **");
            }
        }
    }

    private void buscarLibroPorTitulo() {
        while (true) {
            System.out.println("Ingrese el título del libro que desea buscar");
            var tituloUsuario = scanner.nextLine();
            var consulta = consultaLibro.consultaLibrosPorTitulo(tituloUsuario);
            System.out.println("Estos son los libros que coinciden con su busqueda: ");
            seleccionaLibro.mostrarLista(consulta);
            System.out.println("""
                    ==================================================================
                    Ingrese el ID del libro que busca para ver la información completa
                    *Si el libro que busca no aparece en los resultados ingrese '0'
                    para volver al menú principal
                    """);
            var idUsuario = scanner.nextLong();
            if (idUsuario > 0){
                var libroUsuario = seleccionaLibro.mostrarLibroSeleccionado(idUsuario);
                repositorio.save(libroUsuario);
                break;
            } else if (idUsuario < 0){
                System.out.println("** ID inválido **");
            } else {
                break;
            }
        }
    }

    private void buscarLibrosPorAutor() {
    }

    private void buscarLibrosPorLanzamiento() {
    }

    private void buscarLibrosPorIdioma() {
    }

    private void mostrarListaLibros() {
    }

    private void mostrarListaLibrosPorIdioma(){
        
    }

    private void buscarAutoresVivosPorFecha() {
    }

    private void mostrarListaAutores() {
    }
    
    private void mostrarMenuDesarrollador(){
        
    }
}
