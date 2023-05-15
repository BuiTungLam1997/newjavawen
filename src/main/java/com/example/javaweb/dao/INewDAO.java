package com.example.javaweb.dao;

import com.example.javaweb.model.NewModel;
import com.example.javaweb.paging.Pageble;

import javax.enterprise.inject.New;
import java.util.List;

public interface INewDAO extends GenericDAO<NewModel>{
    List<NewModel> findByCategoryId(Long categoryId);
    Long save(NewModel newModel);
    void delete (long id);
    NewModel findOne(Long id);
    void update(NewModel newModel);
    List<NewModel> findAll(Pageble pageble);
    int getToTalItem();
}
