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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/favourite")
public class FavouriteFilmsServlet extends HttpServlet {
    private DB_Helper dbHelper;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dbHelper = (DB_Helper) config.getServletContext().getAttribute("dbHelper");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = (Integer) req.getSession().getAttribute("userId"); // Получаем userId из сессии
        if (userId == null) {
            resp.sendRedirect("/login"); // Перенаправление на страницу входа
            return;
        }

        // Получаем список ID избранных фильмов
        List<Integer> favoriteFilmIds = dbHelper.getFavouriteFilmsByUserId(userId).stream()
                .map(FavouriteFilm::getFilmId)
                .collect(Collectors.toList());

        // Получаем подробности о фильмах по этим ID
        List<Film> favoriteFilms = dbHelper.getFilmsByIds(favoriteFilmIds); // Метод, который возвращает список объектов Film по их ID

        req.setAttribute("favoriteFilms", favoriteFilms);
        req.getRequestDispatcher("/WEB-INF/jsp/favourite.jsp").forward(req, resp);
    }
}
