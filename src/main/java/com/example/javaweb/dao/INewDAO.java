package com.example.javaweb.dao;

import com.example.javaweb.controller.admin.api.request.NewQuery;
import com.example.javaweb.model.NewModel;
import com.example.javaweb.paging.Page;
import com.example.javaweb.paging.Pageable;

import java.util.List;

public interface INewDAO extends GenericDAO<NewModel>{
    List<NewModel> findByCategoryId(Long categoryId);
    Long save(NewModel newModel);
    void delete (long id);
    NewModel findOne(Long id);
    void update(NewModel newModel);
    List<NewModel> findAll(Pageable pageable);
    int getToTalItem();

    Page<NewModel> query(NewModel query, Pageable pageable);
}
