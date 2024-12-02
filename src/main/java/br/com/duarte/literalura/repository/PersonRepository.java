package br.com.duarte.literalura.repository;

import br.com.duarte.literalura.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByName(String name);
    @Query("SELECT p FROM Person p WHERE p.birthYear <= :year AND p.deathYear >= :year")
    Optional<List<Person>> findAuthorAliveInSpecifYear(Integer year);
}
