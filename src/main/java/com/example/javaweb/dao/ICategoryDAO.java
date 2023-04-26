package com.example.javaweb.dao;

import com.example.javaweb.model.CategoryModel;

import java.util.List;

public interface ICategoryDAO {
    List<CategoryModel> findAll();
}
