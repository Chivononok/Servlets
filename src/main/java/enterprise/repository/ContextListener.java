package enterprise.repository;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.SQLException;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        String url = servletContext.getInitParameter("DBURL");
        String login = servletContext.getInitParameter("DBUSER");
        String password = servletContext.getInitParameter("DBPWD");

        try {
            PostgreConnect postgreConnect = new PostgreConnect(url, login, password);
            servletContext.setAttribute("postgreConnect", postgreConnect);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        PostgreConnect postgreConnect = (PostgreConnect)servletContext.getAttribute("postgreConnect");
        try {
            postgreConnect.closeConnection();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
