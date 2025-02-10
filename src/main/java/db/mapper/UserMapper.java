package db.mapper;

import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {
    public User mapRow (ResultSet resultSet) throws SQLException {
        return new User (
                resultSet.getInt("user_id"),
                resultSet.getString("username"),
                resultSet.getString("password")
        );
    }

}
