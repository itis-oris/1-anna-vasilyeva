package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Review {
    private int id;
    private int filmId;
    private int reviewer;
    private String reviewText;
    private String reviewerName;
    private String filmTitle;

    public Review(int filmId, String reviewText) {
        this.filmId = filmId;
        this.reviewText = reviewText;
    }

    public Review(int filmId, int reviewer, String reviewText) {
        this.filmId = filmId;
        this.reviewer = reviewer;
        this.reviewText = reviewText;
    }

    public Review(int id, int reviewer, int filmId, String reviewText) {
        this.filmId = filmId;
        this.id = id;
        this.reviewer = reviewer;
        this.reviewText = reviewText;
    }
}