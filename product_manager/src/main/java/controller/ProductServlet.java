package controller;

import model.Products;
import service.IProductService;
import service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IProductService productService;

    public void init() {
        productService = new ProductService();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertProducts(req, resp);
                    break;
                case "edit":
                    updateProducts(req, resp);
                    break;
                case "delete" :
                    deleteProducts(req,resp);
                    break;
                case "search":
                    findCustomers(req, resp);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }



    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    showNewForm(req, resp);
                    break;
                case "edit":
                    showEditForm(req, resp);
                    break;
                case "delete":
                    formDeleteProducts(resp, req);
                    break;
                default:
                    listProduct(req, resp);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listProduct(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        List<Products> listProduct = productService.selectAllProducts();
        req.setAttribute("listProduct", listProduct);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/list.jsp");
        dispatcher.forward(req, resp);
    }

    private void findCustomers(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        String name = req.getParameter("name");

        List<Products> listProduct = productService.findProduct(name);
        req.setAttribute("listProduct", listProduct);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/list.jsp");
        dispatcher.forward(req, resp);
    }

    public void formDeleteProducts(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("product",productService.selectProduct(id));
        RequestDispatcher dis = request.getRequestDispatcher("product/delete.jsp");
        dis.forward(request,response);
    }

    private void deleteProducts(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        int id =Integer.parseInt(req.getParameter("id"));
        boolean deleteProduct =  productService.deleteProduct(id);
        if(deleteProduct){
            req.setAttribute("error",null);
            req.setAttribute("success","Update Product successfully");
        }else {
            req.setAttribute("error","Update Product fail");
            req.setAttribute("success",null);
        }
        resp.sendRedirect("/products");

    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(req.getParameter("id"));
        Products existingProduct = productService.selectProduct(id);
        if (existingProduct != null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("product/edit.jsp");
            req.setAttribute("product", existingProduct);
            dispatcher.forward(req, resp);

        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("product/404.jsp");
            dispatcher.forward(req, resp);
        }

    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException{
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/create.jsp");
        dispatcher.forward(req, resp);
    }


    private void insertProducts(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        String name = req.getParameter("product_name");
        double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String color = req.getParameter("color");
        int category = Integer.parseInt(req.getParameter("category"));

        if(name.equals("") || String.valueOf(price).equals("") || String.valueOf(quantity).equals("") || String.valueOf(color).equals("") || String.valueOf(category).equals("")){
            RequestDispatcher dispatcher = req.getRequestDispatcher("product/create.jsp");
            req.setAttribute("error","Invalid Value");
            req.setAttribute("success",null);
            dispatcher.forward(req,resp);

        }else{
            Products newProduct = new Products();
            newProduct.setProductName(name);
            newProduct.setPrice(price);
            newProduct.setQuantity(quantity);
            newProduct.setColor(color);
            newProduct.setCategory(category);
            productService.insertProduct(newProduct);
            req.setAttribute("error",null);
            req.setAttribute("success","Create Product successfully");
            showNewForm(req, resp);
        }

    }

    private void updateProducts(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("product_name");
        double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String color = req.getParameter("color");
        int category = Integer.parseInt(req.getParameter("category"));
        Products product = new Products(id, name, price, quantity, color, category);
        boolean update = productService.updateProduct(product);
        if(update){
            req.setAttribute("error",null);
            req.setAttribute("success","Update Product successfully");
        }else {
            req.setAttribute("error","Update Product fail");
            req.setAttribute("success",null);
        }
        showEditForm(req, resp);
    }
}