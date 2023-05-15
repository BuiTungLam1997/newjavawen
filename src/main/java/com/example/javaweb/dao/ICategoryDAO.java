package com.example.javaweb.dao;

import com.example.javaweb.model.CategoryModel;

import java.util.List;

public interface ICategoryDAO extends GenericDAO{
    List<CategoryModel> findAll();
    CategoryModel findOne(long id);
    CategoryModel findOneByCode(String code);
}
