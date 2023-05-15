package com.example.javaweb.controller.admin.api;

import com.example.javaweb.model.NewModel;
import com.example.javaweb.model.UserModel;
import com.example.javaweb.service.INewService;
import com.example.javaweb.service.impl.NewService;
import com.example.javaweb.utils.HttpUtil;
import com.example.javaweb.utils.SessionUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-admin-new"})
public class NewAPI extends HttpServlet {
    private INewService newService;

    public NewAPI() {
        newService = new NewService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");//nhan du lieu dang tieng viet co dau
        response.setContentType("application/json");//dinh dang du lieu tra ve cho client(tra ve du lieu dang json)
        // request.getReader() nhan du lieu tu client
        NewModel newModel = HttpUtil.of(request.getReader()).toModel(NewModel.class);//convert tu json sang newmodel
        newModel.setCreatedBy(((UserModel) SessionUtils.getInstance().getValue(request,"USERMODEL")).getUserName());
        newModel = newService.save(newModel);
        mapper.writeValue(response.getOutputStream(), newModel);//tra du lieu cho client

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");//nhan du lieu dang tieng viet co dau
        response.setContentType("application/json");//dinh dang du lieu tra ve cho client(tra ve du lieu dang json)
        NewModel updateNew = HttpUtil.of(request.getReader()).toModel(NewModel.class);//convert tu json sang newmodel
        updateNew.setCreatedBy(((UserModel) SessionUtils.getInstance().getValue(request,"USERMODEL")).getUserName());
        updateNew = newService.update(updateNew);
        mapper.writeValue(response.getOutputStream(), updateNew);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");//nhan du lieu dang tieng viet co dau
        response.setContentType("application/json");//dinh dang du lieu tra ve cho client(tra ve du lieu dang json)
        NewModel newModel = HttpUtil.of(request.getReader()).toModel(NewModel.class);//convert tu json sang newmodel
        newService.delete(newModel.getIds());
        mapper.writeValue(response.getOutputStream(), "{}");
    }

    private void saveOfUpate() {
    }
}
