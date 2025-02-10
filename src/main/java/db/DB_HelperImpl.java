package db;

import lombok.AllArgsConstructor;
import model.FavouriteFilm;
import model.Film;
import model.Review;
import model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class DB_HelperImpl implements DB_Helper {

    private Configuration configuration;

    @Override
    public boolean saveFilm(Film film) {
        return configuration.getFilmDao().addFilm(film);
    }

    @Override
    public boolean saveReview(Review review) {
        return configuration.getReviewDao().addReview(review);
    }

    @Override
    public List<Film> getFilms() {
        return configuration.getFilmDao().getAllFilms();
    }

    @Override
    public List<Review> getReviews() {
        return configuration.getReviewDao().getAllReviews();
    }

    @Override
    public int saveUser(User user) {
        user.setHashPassword(BCrypt.hashpw(user.getHashPassword(), BCrypt.gensalt(10)));
        return configuration.getUserDao().addUser(user);
    }

    @Override
    public int checkUser(User user) {
        User userByUsername = configuration.getUserDao().getUserByUsername(user.getUsername());
        boolean checkpw = BCrypt.checkpw(user.getHashPassword(), userByUsername.getHashPassword());
        if (checkpw) {
            return userByUsername.getId();
        } else {
            return -1;
        }
    }

    @Override
    public String getUserName(int userId) {
        return configuration.getUserDao().getUserById(userId).getUsername();
    }

    @Override
    public Film getFilmById(int id) {
        return configuration.getFilmDao().getFilmById(id);
    }

    @Override
    public List<Review> getReviewsByFilmId(int filmId) {
        return configuration.getReviewDao().getReviewByFilmId(filmId);
    }

    @Override
    public List<Review> getReviewsByUserId(int userId) {
        return configuration.getReviewDao().getReviewByUserId(userId);
    }

    @Override
    public User getUserById(int reviewer) {
        return configuration.getUserDao().getUserById(reviewer);
    }

    @Override
    public List<FavouriteFilm> getFavouriteFilmsByUserId(int userId) {
        return configuration.getFilmDao().getFavouriteFilmsByUserId(userId);
    }

    @Override
    public boolean deleteFilm(Integer filmId) {
        return configuration.getFilmDao().deleteFilm(filmId);
    }

    @Override
    public boolean isFilmFavorite(int userId, int filmId) {
        return configuration.getFilmDao().isFilmFavorite(userId, filmId);
    }

    @Override
    public boolean addFilmToFavorites(int userId, int filmId) {
        return configuration.getFilmDao().addFilmToFavorites(userId, filmId);
    }

    @Override
    public boolean removeFilmFromFavorites(Integer userId, int filmId) {
        return configuration.getFilmDao().removeFilmFromFavorites(userId, filmId);
    }

    @Override
    public List<Film> getFilmsByIds(List<Integer> favoriteFilmIds) {
        return configuration.getFilmDao().getFilmsById(favoriteFilmIds);
    }

    @Override
    public boolean updateReview(Review review) {
        return configuration.getReviewDao().updateReview(review);
    }

    @Override
    public boolean deleteReviewById(int reviewId) {
        return configuration.getReviewDao().deleteReview(reviewId);
    }
}
