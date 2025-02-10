package db;

import model.FavouriteFilm;
import model.Film;
import model.Review;
import model.User;

import java.util.List;

public interface DB_Helper {
    boolean saveFilm(Film film);
    boolean saveReview(Review review);
    List<Film> getFilms();
    List<Review> getReviews();

    int saveUser(User user);

    int checkUser(User user);

    String getUserName(int userId);
    Film getFilmById(int id);
    List<Review> getReviewsByFilmId(int filmId);

    List<Review> getReviewsByUserId(int userId);

    User getUserById(int reviewer);

    List<FavouriteFilm> getFavouriteFilmsByUserId(int userId);


    boolean deleteFilm(Integer filmId);

    boolean isFilmFavorite(int userId, int filmId);

    boolean addFilmToFavorites(int userId, int filmId);

    boolean removeFilmFromFavorites(Integer userId, int filmId);

    List<Film> getFilmsByIds(List<Integer> favoriteFilmIds);

    boolean updateReview(Review review);

    boolean deleteReviewById(int reviewId);
}
