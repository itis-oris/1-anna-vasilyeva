package db.dao;

import db.Configuration;
import db.mapper.FavouriteFilmMapper;
import db.mapper.FilmMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import model.FavouriteFilm;
import model.Film;
import model.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class FilmDao {

    private Configuration configuration;
    private FilmMapper mapper;
    private FavouriteFilmMapper favouriteMapper;

    public FilmDao(Configuration configuration) {
        this.configuration = configuration;
        this.mapper = new FilmMapper();
        this.favouriteMapper = new FavouriteFilmMapper();
    }

    public List<Film> getAllFilms() {
        try(Connection connection = configuration.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM film"))
        {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Film> film = new ArrayList<>();
            while (resultSet.next()) {
                film.add(mapper.mapRow(resultSet));
            }
            return film;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Film getFilmById(int id) {
        try (Connection connection = configuration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM film WHERE film_id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Film film = new Film();
            while(resultSet.next()) {
                film = mapper.mapRow(resultSet);
            }

            return film;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<FavouriteFilm> getFavouriteFilmsByUserId(int userId) {
        String query = "SELECT * FROM favourite_film WHERE user_id = ?";
        List<FavouriteFilm> favouriteFilms = new ArrayList<>();

        try (Connection connection = configuration.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM favourite_film WHERE user_id = ?")) {
            stmt.setInt(1, userId); // Устанавливаем значение для первого параметра
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                FavouriteFilm favouriteFilm = new FavouriteFilm();
                favouriteFilm.setUserId(rs.getInt("user_id"));
                favouriteFilm.setFilmId(rs.getInt("film_id"));
                favouriteFilms.add(favouriteFilm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return favouriteFilms;
    }


    public boolean addFilm(Film film) {
        try(Connection connection = configuration.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Insert into film(title, author) values(?,?)")) {
            preparedStatement.setString(1, film.getTitle());
            preparedStatement.setString(2, film.getAuthor());
            int i = preparedStatement.executeUpdate();
            return i == 1;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public boolean deleteFilm(int id) {
        try (Connection connection = configuration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM film WHERE film_id = ?")) {
            preparedStatement.setInt(1, id);
            int i = preparedStatement.executeUpdate();
            return i == 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateFilm(Film film, int id) {
        try(Connection connection = configuration.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE film SET title = ?, author = ? WHERE id = ?")) {
            preparedStatement.setString(1, film.getTitle());
            preparedStatement.setString(2, film.getAuthor());
            preparedStatement.setInt(3, id);
            int i = preparedStatement.executeUpdate();
            return i == 1;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // В классе FilmDao

    public boolean isFilmFavorite(int userId, int filmId) {
        try (Connection connection = configuration.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM favourite_film WHERE user_id = ? AND film_id = ?")) {
            stmt.setInt(1, userId);
            stmt.setInt(2, filmId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Если количество больше 0, то фильм уже избран
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addFilmToFavorites(int userId, int filmId) {
        try (Connection connection = configuration.getConnection();
             PreparedStatement stmt = connection.prepareStatement("INSERT INTO favourite_film (user_id, film_id) VALUES (?, ?)")) {
            stmt.setInt(1, userId);
            stmt.setInt(2, filmId);
            return stmt.executeUpdate() == 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public boolean removeFilmFromFavorites(int userId, int filmId) {
        String query = "DELETE FROM favourite_film WHERE user_id = ? AND film_id = ?";
        try (Connection connection = configuration.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setInt(2, filmId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Film> getFilmsById(List<Integer> filmIds) {
        List<Film> films = new ArrayList<>();
        String query = "SELECT * FROM film WHERE film_id IN (" + filmIds.stream().map(String::valueOf).collect(Collectors.joining(",")) + ")";

        try (Connection connection = configuration.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                films.add(mapper.mapRow(rs));
            }
            return films;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}

