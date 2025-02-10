package model;

import lombok.Data;

@Data
public class FavouriteFilm {
    private int id;
    private int filmId;
    private int userId;

    public FavouriteFilm() {
    }

    public FavouriteFilm(int filmId, int userId) {
        this.filmId = filmId;
        this.userId = userId;
    }
}
