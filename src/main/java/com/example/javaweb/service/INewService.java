package com.example.javaweb.service;

import com.example.javaweb.model.NewModel;
import com.example.javaweb.paging.Pageble;

import java.util.List;

public interface INewService {
    List<NewModel> findByCategoryid(Long categoryid);
    NewModel save(NewModel newModel);
    NewModel findOne(long id);
    NewModel update(NewModel updateNew);
    void delete(long[] ids);
    List<NewModel> findAll(Pageble pageble);
    int getTotalItem();
}
