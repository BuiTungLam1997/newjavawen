package com.example.javaweb.service;

import com.example.javaweb.controller.admin.api.request.NewQuery;
import com.example.javaweb.model.NewModel;
import com.example.javaweb.paging.Page;
import com.example.javaweb.paging.Pageable;

import java.util.List;

public interface INewService {
    List<NewModel> findByCategoryid(Long categoryid);
    NewModel save(NewModel newModel);
    NewModel findOne(long id);
    NewModel update(NewModel updateNew);
    void delete(long[] ids);
    List<NewModel> findAll(Pageable pageable);
    int getTotalItem();

    Page<NewModel> query(NewQuery queryModel, Pageable pageable);
}
