package org.mournlied.literalura;

import org.mournlied.literalura.principal.Principal;
import org.mournlied.literalura.repository.AutorRepository;
import org.mournlied.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.InputMismatchException;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}
	@Autowired
	private LibroRepository repositorioLibro;
	@Autowired
	private AutorRepository repositorioAutor;
	
	@Override
	public void run(String... args) {
		while(true) {
			try {
				Principal principal = new Principal(repositorioLibro, repositorioAutor);
				principal.showMenu();
				break;
			} catch (InputMismatchException e) {
				System.out.println("** Se produjo un error: Dato ingresado no válido **\n");
			}
		}
	}
}
