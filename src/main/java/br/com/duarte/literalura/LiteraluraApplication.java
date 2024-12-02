package br.com.duarte.literalura;

import br.com.duarte.literalura.main.Main;
import br.com.duarte.literalura.service.BookService;
import br.com.duarte.literalura.service.LanguageService;
import br.com.duarte.literalura.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
	@Autowired
	private BookService service;
	@Autowired
	private PersonService personService;
	@Autowired
	private LanguageService languageService;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(service, personService, languageService);
		main.exibeMenu();
	}
}
