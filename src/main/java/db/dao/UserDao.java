package db.dao;

import db.Configuration;
import db.mapper.UserMapper;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    private Configuration configuration;
    private UserMapper mapper;

    public UserDao(Configuration configuration) {
        this.configuration = configuration;
        this.mapper = new UserMapper();
    }

    public User getUserById(int id) {
        try (Connection connection = configuration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM account WHERE user_id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            User user = mapper.mapRow(resultSet);
            return user;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int addUser(User user) {
        try(Connection connection = configuration.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Insert into account(username, password) values(?,?)")) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getHashPassword());
            int i = preparedStatement.executeUpdate();
            if (i == 1){
                User userByUsername = getUserByUsername(user.getUsername());
                return userByUsername.getId();
            }else{
                throw new RuntimeException();
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public User getUserByUsername(String username) {
        try(Connection connection = configuration.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM account WHERE username = ?")){
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            User user = mapper.mapRow(resultSet);
            return user;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public boolean deleteUser(int id) {
        try (Connection connection = configuration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM account WHERE user_id = ?")) {
            preparedStatement.setInt(1, id);
            int i = preparedStatement.executeUpdate();
            return i == 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateUser(User user, int id) {
        try(Connection connection = configuration.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE account SET username = ?, password = ? WHERE id = ?")) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getHashPassword());
            preparedStatement.setInt(3, id);
            int i = preparedStatement.executeUpdate();
            return i == 1;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
