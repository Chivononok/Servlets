package enterprise.servlets;

import enterprise.repository.PostgreConnect;
import enterprise.repository.ProductRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeleteProductServlet extends HttpServlet {
    private PostgreConnect postgreConnect;
    @Override
    public void init() throws ServletException {
        postgreConnect = (PostgreConnect)getServletContext().getAttribute("postgreConnect");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id;
        try {
            id = req.getParameter("id");
            ProductRepository productRepository = new ProductRepository(postgreConnect);
            productRepository.deleteProductById(Integer.parseInt(id));
            resp.sendRedirect(req.getContextPath() + "/");
        } catch (Exception e){
            var message = e.getMessage();
            req.setAttribute("errorMessage", message);
            req.getRequestDispatcher("/WEB-INF/view/errorPage.jsp").forward(req, resp);
        }
    }
}
