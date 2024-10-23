package enterprise.filter;

import enterprise.entity.ROLE;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static java.util.Objects.nonNull;

public class AdmFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        if (nonNull(session) && nonNull(session.getAttribute("role"))){
            //есть созданная сессия и в ней есть аттрибут роль
            ROLE role = (ROLE) session.getAttribute("role");
            if (role.equals(ROLE.ADMIN)){
                chain.doFilter(request, response);
                return;
            }
        }
        req.setAttribute("errorMessage", "Попытка неавторизированного доступа!");
        req.getRequestDispatcher("/WEB-INF/view/errorPage.jsp").forward(req, resp);
    }
}
