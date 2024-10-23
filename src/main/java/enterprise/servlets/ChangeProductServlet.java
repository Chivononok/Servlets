package enterprise.servlets;

import enterprise.entity.Product;
import enterprise.repository.PostgreConnect;
import enterprise.repository.ProductRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ChangeProductServlet extends HttpServlet {
    private PostgreConnect postgreConnect;
    @Override
    public void init() throws ServletException {
        postgreConnect = (PostgreConnect) getServletContext().getAttribute("postgreConnect");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        ProductRepository productRepository = new ProductRepository(postgreConnect);
        Product product = productRepository.getProductById(Integer.parseInt(id));
        req.setAttribute("product", product);
        if(product.getId()==-1){
            //если пробуют поменять продукт с несуществующим Id
            resp.sendRedirect("/");
        } else {
            req.getRequestDispatcher("/WEB-INF/view/changeProduct.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            String newPrice = req.getParameter("price");
            String newName = req.getParameter("name");
            ProductRepository productRepository = new ProductRepository(postgreConnect);
            productRepository.updateProductById(Integer.parseInt(id), newName, Double.parseDouble(newPrice));
            resp.sendRedirect(req.getContextPath() + "/");
        } catch (Exception e){
            var message = e.getMessage();
            req.setAttribute("errorMessage", message);
            req.getRequestDispatcher("/WEB-INF/view/errorPage.jsp").forward(req, resp);
        }
    }
}
