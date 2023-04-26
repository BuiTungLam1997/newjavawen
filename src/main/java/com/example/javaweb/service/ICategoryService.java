package com.example.javaweb.service;

import com.example.javaweb.model.CategoryModel;

import java.util.List;

public interface ICategoryService {
    List<CategoryModel> findAll();
}
