package enterprise.servlets;

import enterprise.entity.User;
import enterprise.repository.PostgreConnect;
import enterprise.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class ShowUsersServlet extends HttpServlet {
    private PostgreConnect postgreConnect;
    @Override
    public void init() throws ServletException {
        postgreConnect = (PostgreConnect)getServletContext().getAttribute("postgreConnect");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRepository userRepository = new UserRepository(postgreConnect);
        List<User> users = userRepository.getUsers();
        req.setAttribute("usersBase", users);
        req.getRequestDispatcher("/WEB-INF/view/showUsersPage.jsp").forward(req, resp);
    }
}
