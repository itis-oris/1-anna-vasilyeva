package servlet;

import db.DB_Helper;
import model.FavouriteFilm;
import model.Film;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/all")
public class AllFilmsServlet extends HttpServlet {
    private DB_Helper dbHelper;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dbHelper = (DB_Helper) config.getServletContext().getAttribute("dbHelper");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = (Integer) req.getSession().getAttribute("userId"); // Получаем userId из сессии
        List<Film> films = dbHelper.getFilms();
        req.setAttribute("films", films);
        if(userId != null) {
            List<Integer> favoriteFilms = dbHelper.getFavouriteFilmsByUserId(userId).stream()
                    .map(FavouriteFilm::getFilmId) // Получаем только filmId из списка FavouriteFilm
                    .collect(Collectors.toList());
            req.setAttribute("favoriteFilms", favoriteFilms);
        }

        req.getRequestDispatcher("/WEB-INF/jsp/allFilms.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer userId = (Integer) req.getSession().getAttribute("userId");

        String filmIdParam = req.getParameter("filmId");
        int filmId = Integer.parseInt(filmIdParam);
        boolean success = false;

        List<Integer> favoriteFilms = dbHelper.getFavouriteFilmsByUserId(userId).stream()
                .map(FavouriteFilm::getFilmId) // Получаем только filmId из списка FavouriteFilm
                .collect(Collectors.toList());

        if (favoriteFilms.contains(filmId)) {
            // Если фильм уже в избранных, удаляем его
            success = dbHelper.removeFilmFromFavorites(userId, filmId);
        } else {
            // Если фильма нет в избранных, добавляем его
            success = dbHelper.addFilmToFavorites(userId, filmId);
        }

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.write("{\"status\": \"" + (success ? "success" : "error") + "\"}");
    }

}

