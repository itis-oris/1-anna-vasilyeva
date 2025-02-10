package db.mapper;

import model.Film;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmMapper {
    public Film mapRow(ResultSet resultSet) throws SQLException {
        return new Film(
                resultSet.getInt("film_id"),
                resultSet.getString("title"),
                resultSet.getString("author")
        );
    }

}
