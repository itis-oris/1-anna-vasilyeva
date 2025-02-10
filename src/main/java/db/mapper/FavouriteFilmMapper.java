package db.mapper;

import model.FavouriteFilm;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FavouriteFilmMapper {
    public FavouriteFilm mapRow(ResultSet resultSet) throws SQLException {
        return new FavouriteFilm(
                resultSet.getInt("film_id"),
                resultSet.getInt("user_id")
        );
    }
}
