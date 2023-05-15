package com.example.javaweb.controller.admin;

import com.example.javaweb.constant.SystemConstant;
import com.example.javaweb.model.CategoryModel;
import com.example.javaweb.model.NewModel;
import com.example.javaweb.paging.PageRequest;
import com.example.javaweb.paging.Pageble;
import com.example.javaweb.service.ICategoryService;
import com.example.javaweb.service.INewService;
import com.example.javaweb.service.impl.CategoryService;
import com.example.javaweb.service.impl.NewService;
import com.example.javaweb.sort.Sorter;
import com.example.javaweb.utils.FormUtils;
import com.example.javaweb.utils.MessageUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin-new"})
public class NewController extends HttpServlet {
    private INewService newService;
    private ICategoryService categoryService;

    public NewController() {
        newService = new NewService();
        categoryService = new CategoryService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "";
        NewModel model = FormUtils.toModel(NewModel.class, request);
        if (model.getType().equals(SystemConstant.LIST)) {
            Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(),
                    new Sorter(model.getSortName(), model.getSortBy()));
            Integer offset = ((model.getPage() - 1) * model.getMaxPageItem());
            model.setListResult(newService.findAll(pageble));
            model.setTotalItem(newService.getTotalItem());
            model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
            view = "/views/admin/new/list.jsp";
        } else if (model.getType().equals(SystemConstant.EDIT)) {
            if (model.getId() != null) {
                model = newService.findOne(model.getId());
            } else {

            }
            CategoryModel categoryModel = new CategoryModel();
            request.setAttribute("categories", categoryService.findAll());
            view = "/views/admin/new/edit.jsp";
        }
        MessageUtils.showMessage(request);
        request.setAttribute(SystemConstant.MODEL, model);
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
