package br.com.duarte.literalura.service;

import br.com.duarte.literalura.repository.LanguageRepository;
import br.com.duarte.literalura.model.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    @Autowired
    private LanguageRepository languageRepository;

    public void listBooksByLanguage(String language){
        Optional<Language> isThereLanguage = languageRepository.findByLanguage(language);
        if(isThereLanguage.isPresent()){
            isThereLanguage.get().getBooks().stream()
                    .forEach(System.out::println);
        }
        else{
            System.out.println("Não foram encontrados livros com esse idioma");
        }
    }

    public void listAllLanguages(){
        List<Language> languages = languageRepository.findAll();
        languages.stream()
                .forEach(l -> System.out.println("Idioma: "+l.getLanguage()));
    }

}
