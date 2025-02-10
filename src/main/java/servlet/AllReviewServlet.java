package servlet;

import db.DB_Helper;
import db.DB_HelperImpl;
import model.Film;
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

@WebServlet("/reviews")
public class AllReviewServlet extends HttpServlet {
    private DB_Helper dbHelper;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dbHelper = (DB_Helper) config.getServletContext().getAttribute("dbHelper");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = (Integer) req.getSession().getAttribute("userId");
        List<Review> reviews = dbHelper.getReviewsByUserId(userId);
        addFilmTitle(reviews);
        req.setAttribute("reviews", reviews);
        req.getRequestDispatcher("/WEB-INF/jsp/reviews.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reviewIdParam = req.getParameter("reviewId");
        if (reviewIdParam != null && !reviewIdParam.isEmpty()) {
            int reviewId = Integer.parseInt(reviewIdParam);
            boolean success = dbHelper.deleteReviewById(reviewId);

            if (success) {
                // Если удаление прошло успешно, перенаправляем обратно на страницу отзывов
                resp.sendRedirect(req.getContextPath() + "/reviews");
            } else {
                // Если ошибка при удалении
                req.setAttribute("error", "Не удалось удалить отзыв.");
                req.getRequestDispatcher("/WEB-INF/jsp/reviews.jsp").forward(req, resp);
            }
        }
    }

    private void addFilmTitle(List<Review> reviews) {
        for (Review review : reviews) {
            int filmId = review.getFilmId();
            System.out.println(filmId);
            Film film = dbHelper.getFilmById(filmId);
            System.out.println(film.getTitle());
            review.setFilmTitle(film.getTitle());
        }
    }
}