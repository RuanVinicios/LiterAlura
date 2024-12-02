package br.com.duarte.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData (
        Long id,
        String title,
        List<String> subjects,
        List<String> languages,
        List<PersonData> authors,
        @JsonAlias("download_count") Integer downloadCount) {
}
