package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/*")
public class AuthorizationCheckFilter implements Filter {
    private final List<String> checkRegistrationList;
    private final List<String> checkNoLoginList;

    public AuthorizationCheckFilter() {
        this.checkRegistrationList =  new ArrayList<>();
        checkRegistrationList.add("/addfilm");
        checkRegistrationList.add("/addReviewToFilm");
        checkNoLoginList =  new ArrayList<>();
        checkNoLoginList.add("/authorization");
        checkNoLoginList.add("/register");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String requestURI = req.getRequestURI();
        HttpServletResponse resp = (HttpServletResponse) response;
        if (checkRegistrationList.contains(requestURI)) {
            Object userId = req.getSession().getAttribute("userId");
            if (userId == null) {
                resp.sendRedirect(req.getContextPath() + "/");
                return;
            }
        }
        if (checkNoLoginList.contains(requestURI)) {
            Object userId = req.getSession().getAttribute("userId");
            if (userId != null) {
                resp.sendRedirect(req.getContextPath() + "/");
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
