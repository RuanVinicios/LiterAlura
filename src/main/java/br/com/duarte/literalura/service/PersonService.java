package br.com.duarte.literalura.service;

import br.com.duarte.literalura.repository.PersonRepository;
import br.com.duarte.literalura.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository repository;

    public void listAuthors() {
        repository.findAll().stream().forEach(System.out::println);
    }

    public void listAuthorsAliveInSpecifYear(Integer ano) {
        Optional<List<Person>> authorsAlive = repository.findAuthorAliveInSpecifYear(ano);

        if(authorsAlive.isPresent()){
            authorsAlive.get().forEach(System.out::println);
        }
        else{
            System.out.println("Não foi encontrado autores vivos no ano pesquisado.");
        }
    }
}
