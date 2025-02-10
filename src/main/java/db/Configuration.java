package db;

import db.dao.FilmDao;
import db.dao.ReviewDao;
import db.dao.UserDao;
import model.User;

import java.sql.Connection;

public class Configuration {

    private ConnectionsCreater connectionsCreater;

    public Configuration() {
        connectionsCreater = new ConnectionsCreater();
    }

    public FilmDao getFilmDao() {
        return new FilmDao(this);
    }

    public ReviewDao getReviewDao() { return new ReviewDao(this); }

    public Connection getConnection() {
        return connectionsCreater.getConnection();
    }

    public UserDao getUserDao() {
        return new UserDao(this);
    }
}
