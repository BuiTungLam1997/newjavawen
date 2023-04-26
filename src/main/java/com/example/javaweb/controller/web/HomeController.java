package com.example.javaweb.controller.web;

import com.example.javaweb.model.UserModel;
import com.example.javaweb.service.ICategoryService;
import com.example.javaweb.service.impl.CategoryService;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/trang-chu"})
public class HomeController extends HttpServlet {
   // @Inject
    private ICategoryService categoryService;
    public HomeController(){
        categoryService = new CategoryService();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("categories",categoryService.findAll());
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
