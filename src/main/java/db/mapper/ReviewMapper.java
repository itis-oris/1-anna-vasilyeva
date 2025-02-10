package db.mapper;

import model.Review;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewMapper {
    public Review mapRow(ResultSet resultSet) throws SQLException {
        return new Review(
                resultSet.getInt("review_id"),
                resultSet.getInt("user_id"),
                resultSet.getInt("film_id"),
                resultSet.getString("text")
        );
    }
}
