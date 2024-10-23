package enterprise.servlets;

import enterprise.dto.ProductDTO;
import enterprise.repository.PostgreConnect;
import enterprise.service.impl.ProductServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AddProductServlet extends HttpServlet {
    private PostgreConnect postgreConnect;
    @Override
    public void init() throws ServletException {
        postgreConnect = (PostgreConnect)getServletContext().getAttribute("postgreConnect");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String productName = req.getParameter("productName");
            String productPrice = req.getParameter("productPrice");
            ProductDTO productDTO = new ProductDTO(productName, Double.parseDouble(productPrice));

            ProductServiceImpl productServiceImpl = new ProductServiceImpl(postgreConnect);
            productServiceImpl.addProduct(productDTO);

            resp.sendRedirect(req.getContextPath() + "/");
        } catch (Exception e){
            var message = e.getMessage();
            req.setAttribute("errorMessage", message);
            req.getRequestDispatcher("/WEB-INF/view/errorPage.jsp").forward(req, resp);
        }
    }
}
