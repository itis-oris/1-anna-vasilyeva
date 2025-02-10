package servlet;

import db.DB_Helper;
import model.Review;
import model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/reviewByFilm")
public class ReviewByFilmServlet extends HttpServlet {
    private DB_Helper dbHelper;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dbHelper = (DB_Helper) config.getServletContext().getAttribute("dbHelper");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer filmId = Integer.parseInt(req.getParameter("filmId"));
        if (filmId != null) {
            req.setAttribute("film", dbHelper.getFilmById(filmId));
        }
        List<Review> reviews = dbHelper.getReviewsByFilmId(filmId);
        addUserNames(reviews);
        req.setAttribute("reviews", reviews);
        req.getRequestDispatcher("/WEB-INF/jsp/reviewOfFilm.jsp").forward(req, resp);
    }

    private void addUserNames(List<Review> reviews) {
        for (Review review : reviews) {
            int reviewer = review.getReviewer();
            User user = dbHelper.getUserById(reviewer);
            review.setReviewerName(user.getUsername());
        }
    }
}
