package enterprise.servlets;

import enterprise.dto.UserDto;
import enterprise.entity.ROLE;
import enterprise.repository.PostgreConnect;
import enterprise.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AddUserServlet extends HttpServlet {
    private PostgreConnect postgreConnect;

    @Override
    public void init() throws ServletException {
        postgreConnect = (PostgreConnect)getServletContext().getAttribute("postgreConnect");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String role = req.getParameter("role");

            UserDto userDto = new UserDto();
            userDto.setLogin(req.getParameter("login"));
            userDto.setPassword(req.getParameter("password"));
            userDto.setRole(ROLE.getRoleByStringName(role));

            UserService userService=new UserService(postgreConnect);
            userService.addUser(userDto);

            resp.sendRedirect(req.getContextPath() + "/");  //перенаправили на страничку с вводом логина/пароля
        } catch (Exception e) {
            var message = e.getMessage();
            req.setAttribute("errorMessage", message);
            req.getRequestDispatcher("/WEB-INF/view/errorPage.jsp").forward(req, resp);
        }
    }
}
