package servlet;


import db.DB_Helper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("")
public class GeneralServlet extends HttpServlet {
    private DB_Helper dbHelper;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dbHelper = (DB_Helper) config.getServletContext().getAttribute("dbHelper");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object userId = req.getSession().getAttribute("userId");
        if(userId != null) {
            req.setAttribute("username", dbHelper.getUserName((Integer)userId));
        }
        req.getRequestDispatcher("/WEB-INF/jsp/general_menu.jsp").forward(req, resp);
    }
}
