package com.example.javaweb.dao.impl;

import com.example.javaweb.dao.ICategoryDAO;
import com.example.javaweb.mapper.CategoryMapper;
import com.example.javaweb.mapper.NewMapper;
import com.example.javaweb.model.CategoryModel;
import com.example.javaweb.model.NewModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends AbstractDAO implements ICategoryDAO {

    @Override
    public List<CategoryModel> findAll() {
        String sql = "select * from category";
        return query(sql, new CategoryMapper());
    }

    @Override
    public CategoryModel findOne(long id) {
        String sql = "select * from category where id = ?";
        List<CategoryModel> categoryModels = query(sql, new CategoryMapper(), id);
        return categoryModels.isEmpty() ? null : categoryModels.get(0);
    }

    @Override
    public CategoryModel findOneByCode(String code) {
        String sql = "select * from category where code = ?";
        List<CategoryModel> categoryModels = query(sql, new CategoryMapper(), code);
        return categoryModels.isEmpty() ? null : categoryModels.get(0);
    }
}
