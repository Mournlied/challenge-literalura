package org.mournlied.literalura.principal;

import org.mournlied.literalura.model.AutorDTO;
import org.mournlied.literalura.model.Libro;
import org.mournlied.literalura.model.LibroDTO;
import org.mournlied.literalura.repository.AutorRepository;
import org.mournlied.literalura.repository.LibroRepository;
import org.mournlied.literalura.service.ConsultaLibro;
import org.mournlied.literalura.service.SeleccionaLibro;

import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConsultaLibro consultaLibro = new ConsultaLibro();
    private SeleccionaLibro seleccionaLibro = new SeleccionaLibro();
    private LibroRepository repositorioLibro;
    private AutorRepository repositorioAutor;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.repositorioLibro = libroRepository;
        this.repositorioAutor = autorRepository;
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
                    3- Buscar libros por total de descargas
                    4- Mostrar Top 5 libros mas descargados
                    5- Mostrar lista completa de libros
                    6- Mostrar total de libros por idioma
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
                    buscarLibrosPorDescargas();
                    break;
                case 4:
                    mostrarTop5LibrosPorDescarga();
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
                var autorUsuario = libroUsuario.getAutor().getNombre();
                var autorDB = repositorioAutor.findAutorByNombre(autorUsuario);
                if (autorDB.isPresent()){
                    var autorExistente = autorDB.get();
                    autorExistente.addLibro(libroUsuario);
                }else{
                    repositorioAutor.save(libroUsuario.getAutor());
                }
                repositorioLibro.save(libroUsuario);
                break;
            } else if (idUsuario < 0){
                System.out.println("** ID inválido **");
            } else {
                break;
            }
        }
    }

    private void buscarLibrosPorAutor() {
        System.out.println("Por favor, ingrese el nombre del autor o la autora");
        var autorUsuario = scanner.nextLine();
        var consultaLibros = repositorioLibro.buscarLibroPorNombreAutor(autorUsuario);
        if (consultaLibros.isPresent()){
            if(!consultaLibros.get().isEmpty()){
                var listaLibros = consultaLibros.get();
                var listaUsuario = listaLibros.stream().map(l ->new LibroDTO(l.getTitulo(),l.getAutor(),l.getGeneros(),l.getLenguaje(),l.getDescargas())).toList();
                listaUsuario.forEach(System.out::println);
            }
        } else {
            System.out.println("No se encontró ningún libro para el autor o la autora ingresado(a) en nuestra base de datos");
        }
    }

    private void buscarLibrosPorDescargas() {
        System.out.println("Por favor, ingrese el número de descargas mínimo que desea consultar");
        var descargasUsuario = scanner.nextLong();
        var consultaLibros = repositorioLibro.findLibroByDescargasGreaterThanEqual(descargasUsuario);
        if (consultaLibros.isPresent()){
            if(!consultaLibros.get().isEmpty()){
                var listaLibros = consultaLibros.get();
                var listaUsuario = listaLibros.stream().map(l ->new LibroDTO(l.getTitulo(),l.getAutor(),l.getGeneros(),l.getLenguaje(),l.getDescargas())).toList();
                listaUsuario.forEach(System.out::println);
            }
        } else {
            System.out.println("No se encontró ningún libro con cantidad de descargas mayor al valor ingresado en nuestra base de datos");
        }
    }

    private void mostrarTop5LibrosPorDescarga() {
        var listaLibros = repositorioLibro.top5Libros();
        var listaUsuario = listaLibros.stream().map(l ->new LibroDTO(l.getTitulo(),l.getAutor(),l.getGeneros(),l.getLenguaje(),l.getDescargas())).toList();
        listaUsuario.forEach(System.out::println);
    }

    private void mostrarListaLibros() {
        System.out.println("Estos son los libros existentes en nuestra base de datos: ");
        var listaLibros = repositorioLibro.findAll();
        var listaUsuario = listaLibros.stream().map(l ->new LibroDTO(l.getTitulo(),l.getAutor(),l.getGeneros(),l.getLenguaje(),l.getDescargas())).toList();
        listaUsuario.forEach(System.out::println);
    }

    private void mostrarListaLibrosPorIdioma(){
        System.out.println("""
                ====================================================
                Por favor, seleccione el idioma que desea consultar:
                
                1- Español
                2- Ingles
                
                """);
        var idiomaUsuario = scanner.nextInt();
        List<Libro> listaLibros;
        int cantidadLibros;
        switch (idiomaUsuario){
            case 1:
                listaLibros = repositorioLibro.findLibroByLenguaje("es");
                cantidadLibros = listaLibros.size();
                System.out.println("Existe(n) "+cantidadLibros+" libro(s) en el idioma ingresado en nuestra base de datos");
                break;
            case 2:
                listaLibros = repositorioLibro.findLibroByLenguaje("en");
                cantidadLibros = listaLibros.size();
                System.out.println("Existe(n) "+cantidadLibros+" libro(s) en el idioma ingresado en nuestra base de datos");
                break;
            default:
                System.out.println("** Opción inválida **");
        }
    }

    private void buscarAutoresVivosPorFecha() {
        System.out.println("Por favor, ingrese el año que desea consultar");
        Integer fechaUsuario = scanner.nextInt();
        var consultaAutores = repositorioAutor.autoresVivos(fechaUsuario);
        if (consultaAutores.isPresent()) {
            if (!consultaAutores.get().isEmpty()) {
                var listaAutores = consultaAutores.get();
                var listaUsuario = listaAutores.stream().map(a -> new AutorDTO(a.getNombre(), a.getFechaNacimiento(), a.getFechaDefuncion())).toList();
                listaUsuario.forEach(System.out::println);
            } else {
                System.out.println("No se encontró ningún(a) autor(a) con vida en el año ingresado en nuestra base de datos");
            }
        }
    }

    private void mostrarListaAutores() {
        System.out.println("Estos son los autores existentes en nuestra base de datos: ");
        var listaAutores = repositorioAutor.findAll();
        var listaUsuario = listaAutores.stream().map(a->new AutorDTO(a.getNombre(),a.getFechaNacimiento(),a.getFechaDefuncion())).toList();
        listaUsuario.forEach(System.out::println);
    }
    
    private void mostrarMenuDesarrollador(){
        //Eventualmente será utilizado para manejar la base de datos, de forma que el resto de las opciones solo hagan queries a dicha base de datos
    }
}
