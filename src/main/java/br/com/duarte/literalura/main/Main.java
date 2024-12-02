package br.com.duarte.literalura.main;

import br.com.duarte.literalura.service.BookService;
import br.com.duarte.literalura.service.LanguageService;
import br.com.duarte.literalura.service.PersonService;

import java.util.*;

public class Main {

    private Scanner leitura = new Scanner(System.in);

    private BookService service;
    private PersonService personService;
    private LanguageService languageService;

    public Main(BookService service, PersonService personService, LanguageService languageService) {
        this.service = service;
        this.personService = personService;
        this.languageService = languageService;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                    0 - Sair
                                               
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroPorTitulo();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresVivosEmAnoEspecifico();
                    break;
                case 5:
                    listarLivrosEmDeterminadoIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void listarLivrosEmDeterminadoIdioma() {
        System.out.println("Digite o idioma desejado da lista abaixo");
        languageService.listAllLanguages();

        String language = leitura.nextLine();

        languageService.listBooksByLanguage(language);
    }

    private void listarAutoresVivosEmAnoEspecifico() {
        System.out.println("Digite o ano que deseja saber");
        try {
            Integer ano = leitura.nextInt();
            leitura.nextLine();
            personService.listAuthorsAliveInSpecifYear(ano);
        } catch (InputMismatchException e) {
            System.out.println("Ops, acho que digitou algo diferente de numeros ...");
        }

    }

    private void listarAutores() {
        System.out.println("Listando autores: ");
        personService.listAuthors();
    }

    private void listarLivros() {
        System.out.println("Listando livros: ");
        service.listBooks();
    }

    private void buscarLivroPorTitulo() {
        System.out.println("Digite o nome do livro que busca");
        String livro = leitura.nextLine();
        service.addBook(livro);
    }
}