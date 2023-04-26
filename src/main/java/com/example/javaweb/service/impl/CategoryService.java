package com.example.javaweb.service.impl;

import com.example.javaweb.dao.ICategoryDAO;
import com.example.javaweb.dao.impl.CategoryDAO;
import com.example.javaweb.model.CategoryModel;
import com.example.javaweb.service.ICategoryService;

import javax.inject.Inject;
import java.util.List;

public class CategoryService implements ICategoryService {

   // @Inject
    private ICategoryDAO categoryDAO;
    public  CategoryService(){
        categoryDAO = new CategoryDAO();
    }
    @Override
    public List<CategoryModel> findAll() {
        return categoryDAO.findAll();
    }
}
