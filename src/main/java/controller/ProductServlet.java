package controller;

import dao.Dao;
import dao.IDao;
import model.Product;
import service.ProductService;
//import service.ProductServiceIml;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    private IDao iDao = new Dao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";
        switch (action){
            case "create":
                showCreateForm(request,response);
                break;
            case "edit":
                showEditForm(request,response);
                break;
            case "delete":
                showDeleteForm(request,response);
                break;
            case "view":
                showViewForm(request,response);
                break;
            default:
                listProducts(request,response);
        }
    }

    private void showViewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id  = Integer.parseInt(request.getParameter("id"));
        Product product = this.iDao.findByID(id);
        RequestDispatcher dispatcher;
        if (product == null) dispatcher = request.getRequestDispatcher("error-404.jsp");
        else {
            request.setAttribute("product",product);
            dispatcher = request.getRequestDispatcher("products/view.jsp");
        }
        dispatcher.forward(request,response);
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = this.iDao.findByID(id);
        RequestDispatcher dispatcher;
        if (product == null) dispatcher = request.getRequestDispatcher("error-404.jsp");
        else {
            request.setAttribute("product", product);
            dispatcher = request.getRequestDispatcher("products/delete.jsp");
        }
        dispatcher.forward(request,response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = this.iDao.findByID(id);
        RequestDispatcher requestDispatcher;
        if (product == null) requestDispatcher = request.getRequestDispatcher("error-404.jsp");
        else {
            request.setAttribute("product",product);
            requestDispatcher = request.getRequestDispatcher("products/edit.jsp");
        }
        requestDispatcher.forward(request,response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("products/create.jsp");
        dispatcher.forward(request,response);
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = this.iDao.findAll();
        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("products/list.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";
        switch (action){
            case "create":
                createProduct(request,response);
                break;
            case "edit":
                try {
                    editProduct(request,response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "delete":
                deleteProduct(request,response);
                break;
            case "view":
                break;
            default:
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = this.iDao.findByID(id);
        RequestDispatcher dispatcher;
        if (product == null) dispatcher = request.getRequestDispatcher("error-404.jsp");
        else {
            this.iDao.remote(id);
            dispatcher = request.getRequestDispatcher("products/delete.jsp");
        }
        request.setAttribute("message", "Successful product delete");
        dispatcher.forward(request,response);
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Product product = new Product(id,name,price,quantity);
        RequestDispatcher dispatcher;
        if (product ==null) dispatcher = request.getRequestDispatcher("error-404.jsp");
        else {
            iDao.update(id,product);
            dispatcher = request.getRequestDispatcher("products/edit.jsp");
        }
        request.setAttribute("message","Successful product repair");
        dispatcher.forward(request,response);
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = (int) (Math.random()*10000);
        String name = request.getParameter("name");
        int price =Integer.parseInt(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Product product = new Product(id,name,price,quantity);
        this.iDao.save(product);
        RequestDispatcher dispatcher =request.getRequestDispatcher("products/create.jsp");
        request.setAttribute("message","New successful product added");
        dispatcher.forward(request,response);
}
}
