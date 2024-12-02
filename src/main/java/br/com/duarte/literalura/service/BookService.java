package br.com.duarte.literalura.service;

import br.com.duarte.literalura.model.*;
import br.com.duarte.literalura.repository.BookRepository;
import br.com.duarte.literalura.repository.LanguageRepository;
import br.com.duarte.literalura.repository.PersonRepository;
import com.alura.literalura.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private LanguageRepository languageRepository;

    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "http://gutendex.com/books/";

    public void addBook(String nomeLivro) {
        var json = consumo.obterDados(ENDERECO + "?search=" + nomeLivro.replace(" ", "%20"));
        ApiData apiData = conversor.obterDados(json, ApiData.class);

        System.out.println(apiData);

        if (apiData.count() > 0) {
            saveBook(apiData.results());

            while (apiData.next() != null) {
                json = consumo.obterDados(ENDERECO + "?search=" + nomeLivro.replace(" ", "%20"));
                apiData = conversor.obterDados(json, ApiData.class);
                saveBook(apiData.results());
            }
        }
    }

    public void saveBook(List<BookData> books) {
        books.stream().forEach(book -> {
                    Book newBook = new Book(book);
                    Optional<Book> isThisBookRegistered = bookRepository.findByPublicId(newBook.getPublicId());
                    if (!isThisBookRegistered.isPresent()) {
                        try {
                            List<Person> authors = newBook.getAuthors().stream()
                                    .map(author -> {
                                        Optional<Person> isAuthorThere = personRepository.findByName(author.getName());
                                        if (isAuthorThere.isPresent()) {
                                            return isAuthorThere.get();
                                        } else {
                                            personRepository.save(author);
                                            return author;
                                        }
                                    })
                                    .collect(Collectors.toList());

                            List<Language> languages = newBook.getLanguages().stream()
                                    .map(language -> {
                                        Optional<Language> isLanguageThere = languageRepository.findByLanguage(language.getLanguage());

                                        if (isLanguageThere.isPresent()) {
                                            return isLanguageThere.get();
                                        } else {
                                            languageRepository.save(language);
                                            return language;
                                        }
                                    }).collect(Collectors.toList());

                            newBook.setAuthors(authors);
                            newBook.setLanguages(languages);


                            bookRepository.save(newBook);
                        } catch (DataIntegrityViolationException e) {
                            System.out.println("Livro já existe na base de dados");
                        }
                    }

                    System.out.println(newBook);
                }
        );
    }

    public void listBooks() {
        bookRepository.findAll().stream()
                .forEach(System.out::println);
    }
}
