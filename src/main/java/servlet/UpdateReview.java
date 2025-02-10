package servlet;

import db.DB_Helper;
import model.Review;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateReview")
public class UpdateReview extends HttpServlet {
    private DB_Helper dbHelper;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dbHelper = (DB_Helper) config.getServletContext().getAttribute("dbHelper");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/updateReview.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reviewText = req.getParameter("reviewText");
        Integer filmId = Integer.parseInt(req.getParameter("filmId"));
        int userId = (Integer) req.getSession().getAttribute("userId");
        Review review = new Review(filmId, userId,reviewText);
        dbHelper.updateReview(review);
        resp.sendRedirect(req.getServletContext().getContextPath() + "/");
    }
}
