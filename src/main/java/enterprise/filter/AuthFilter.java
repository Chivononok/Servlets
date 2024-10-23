package enterprise.filter;

import enterprise.entity.Product;
import enterprise.entity.ROLE;
import enterprise.entity.User;
import enterprise.repository.PostgreConnect;
import enterprise.repository.ProductRepository;
import enterprise.repository.UserRepository;
import enterprise.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;


import static java.util.Objects.nonNull;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        PostgreConnect postgreConnect = (PostgreConnect) req.getServletContext().getAttribute("postgreConnect");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        UserRepository userRepository = new UserRepository(postgreConnect);
        UserService userService = new UserService(postgreConnect);

        ProductRepository productRepository = new ProductRepository(postgreConnect);
        List<Product> productBase = productRepository.getProducts();
        ROLE role = ROLE.ANONYMOUS;

        HttpSession session = req.getSession();
        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {
            role = (ROLE) session.getAttribute("role");
        } else {
            try {
                if(userService.isUserExist(login, password)) {
                    User user = userRepository.getUserByLoginPassword(login, password);
                    role = user.getRole();
                    session.setAttribute("login", login);
                    session.setAttribute("password", password);
                    session.setAttribute("role", role);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        moveToPage(req, res, role, productBase);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void moveToPage(HttpServletRequest req,
                           HttpServletResponse res,
                           ROLE role,
                           List<Product> productBase) throws ServletException, IOException {

        req.setAttribute("productBase", productBase);
        if (role.equals(ROLE.NORMAL_USER)){
            req.getRequestDispatcher("/WEB-INF/view/userPage.jsp").forward(req, res);
        } else if (role.equals(ROLE.ADMIN)){
            req.getRequestDispatcher("/WEB-INF/view/adminPage.jsp").forward(req, res);
        }
        else {
            if (req.getAttribute("errorMessage")!= null) {
                req.getRequestDispatcher("/WEB-INF/view/errorPage.jsp").forward(req, res);
            }else {
                req.getRequestDispatcher("/WEB-INF/view/start.jsp").forward(req, res);
            }
        }
        res.sendRedirect("/");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}