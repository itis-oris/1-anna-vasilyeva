package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Film {
    private int id;
    private String title;
    private String author;

    public Film(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Film() {
    }
}