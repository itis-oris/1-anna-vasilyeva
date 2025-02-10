package db.dao;

import db.Configuration;
import db.mapper.FilmMapper;
import db.mapper.ReviewMapper;
import model.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao {
    private Configuration configuration;
    private ReviewMapper mapper;

    public ReviewDao(Configuration configuration) {
        this.configuration = configuration;
        this.mapper = new ReviewMapper();
    }

    public List<Review> getAllReviews() {
        try (Connection connection = configuration.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM review")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Review> reviews = new ArrayList<>();
            while (resultSet.next()) {
                reviews.add(mapper.mapRow(resultSet));
            }
            return reviews;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Review getReviewById(int id) {
        try(Connection connection = configuration.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM review WHERE review_id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Review review = mapper.mapRow(resultSet);
            return review;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Review> getReviewByFilmId(int filmId) {
        try (Connection connection = configuration.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM review WHERE film_id = ?")) {
            preparedStatement.setInt(1, filmId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List <Review> reviews = new ArrayList<>();
            while (resultSet.next()) {
                reviews.add(mapper.mapRow(resultSet));
            }
            return reviews;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Review> getReviewByUserId(int userId) {
        try (Connection connection = configuration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM review WHERE user_id = ?")) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Review> reviews = new ArrayList<>();
            while (resultSet.next()) {
                reviews.add(mapper.mapRow(resultSet));
            }
            return reviews;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addReview(Review review) {
        try (Connection connection = configuration.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO review (film_id, user_id,text) VALUES (?, ?,?)")) {
            preparedStatement.setInt(1, review.getFilmId());
            preparedStatement.setInt(2,review.getReviewer());
            preparedStatement.setString(3, review.getReviewText());
            int i = preparedStatement.executeUpdate();
            return i == 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateReview(Review review) {
        try (Connection connection = configuration.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE review SET text = ? WHERE film_id = ? AND user_id = ?")) {
            preparedStatement.setString(1, review.getReviewText());
            preparedStatement.setInt(2, review.getFilmId());
            preparedStatement.setInt(3, review.getReviewer());
            int i = preparedStatement.executeUpdate();
            return i == 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteReview(int id) {
        try (Connection connection = configuration.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM review WHERE review_id = ?")) {
            preparedStatement.setInt(1, id);
            int i = preparedStatement.executeUpdate();
            return i == 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
