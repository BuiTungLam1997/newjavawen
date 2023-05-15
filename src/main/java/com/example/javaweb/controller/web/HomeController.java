package com.example.javaweb.controller.web;

import com.example.javaweb.model.UserModel;
import com.example.javaweb.service.ICategoryService;
import com.example.javaweb.service.INewService;
import com.example.javaweb.service.IUserService;
import com.example.javaweb.service.impl.CategoryService;
import com.example.javaweb.service.impl.NewService;
import com.example.javaweb.service.impl.UserService;
import com.example.javaweb.utils.FormUtils;
import com.example.javaweb.utils.SessionUtils;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/trang-chu", "/dang-nhap", "/thoat"})
public class HomeController extends HttpServlet {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
    @Inject
    private ICategoryService categoryService;
    @Inject
    private INewService newService;
    @Inject
    private IUserService userService;

    public HomeController() {
        categoryService = new CategoryService();
        newService = new NewService();
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("login")) {
            String message = request.getParameter("message");
            String alert = request.getParameter("alert");
            if (message != null && alert != null) {
                request.setAttribute("message", resourceBundle.getString(message));
                request.setAttribute("alert", alert);
            }
            RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
            rd.forward(request, response);
        } else if (action != null && action.equals("logout")) {
            SessionUtils.getInstance().removeValue(request, "USERMODEL");
            response.sendRedirect(request.getContextPath() + "/trang-chu");
        } else {
            request.setAttribute("categories", categoryService.findAll());
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action != null && action.equals("login")) {
            UserModel userModel = FormUtils.toModel(UserModel.class, request);
            userModel = userService.findByUserNameAndPasswordAndStatus(userModel.getUserName(), userModel.getPassword(), 1);
            if (userModel != null) {
                SessionUtils.getInstance().putValue(request, "USERMODEL", userModel);
                if (userModel.getRole().getCode().equals("user")) {
                    response.sendRedirect(request.getContextPath() + "/trang-chu");
                } else if (userModel.getRole().getCode().equals("admin")) {
                    response.sendRedirect(request.getContextPath() + "/admin-home");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&message=username_password_invalid&alert=danger");
            }
        }
    }
}
