package br.com.duarte.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="pessoas")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;
    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    public Person() {
    }

    public Person(Long id, String name, Integer birthYear, Integer deathYear) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public Person (PersonData personData){
        this.name = personData.name();
        this.birthYear = personData.birthYear();
        this.deathYear = personData.deathYear();
    }

    @Override
    public String toString() {
        String autor = """
                Autor:
                    nome: %s
                    Ano de nascimento: %d
                    Ano da morte: %d
                """;
        return String.format(autor, this.name, this.birthYear, this.deathYear);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
