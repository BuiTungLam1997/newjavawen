package com.example.javaweb.service.impl;

import com.example.javaweb.controller.admin.api.request.NewQuery;
import com.example.javaweb.dao.ICategoryDAO;
import com.example.javaweb.dao.INewDAO;
import com.example.javaweb.dao.impl.CategoryDAO;
import com.example.javaweb.dao.impl.NewDAO;
import com.example.javaweb.model.CategoryModel;
import com.example.javaweb.model.NewModel;
import com.example.javaweb.paging.Page;
import com.example.javaweb.paging.Pageable;
import com.example.javaweb.service.INewService;

import java.sql.Timestamp;
import java.util.List;

public class NewService implements INewService {
    private INewDAO newDAO;
    private ICategoryDAO categoryDAO;

    public NewService() {
        newDAO = new NewDAO();
        categoryDAO = new CategoryDAO();
    }

    @Override
    public List<NewModel> findByCategoryid(Long categoryid) {
        return newDAO.findByCategoryId(categoryid);
    }

    @Override
    public NewModel save(NewModel newModel) {
        newModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        CategoryModel categoryModel =categoryDAO.findOneByCode(newModel.getCategoryCode());
        newModel.setCategoryId(categoryModel.getId());
        Long newId = newDAO.save(newModel);
        return newDAO.findOne(newId);
    }

    @Override
    public NewModel findOne(long id) {
        NewModel newModel = newDAO.findOne(id);
        CategoryModel categoryModel = categoryDAO.findOne(newModel.getCategoryId());
        newModel.setCategoryCode(categoryModel.getCode());
        return newModel;
    }

    @Override
    public NewModel update(NewModel updateNew) {
        NewModel oldNew = newDAO.findOne(updateNew.getId());
        updateNew.setCreatedDate(oldNew.getCreatedDate());
        updateNew.setCreatedBy(oldNew.getCreatedBy());
        updateNew.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        CategoryModel categoryModel =categoryDAO.findOneByCode(updateNew.getCategoryCode());
        updateNew.setCategoryId(categoryModel.getId());
        newDAO.update(updateNew);
        return newDAO.findOne(updateNew.getId());
    }

    @Override
    public void delete(long[] ids) {
        for (long id : ids) {
            newDAO.delete(id);
        }
    }

    @Override
    public List<NewModel> findAll(Pageable pageable) {
        return newDAO.findAll(pageable);
    }

    @Override
    public int getTotalItem() {
        return newDAO.getToTalItem();
    }

    @Override
    public Page<NewModel> query(NewQuery queryModel, Pageable pageable) {
        NewModel model = NewModel.of(queryModel);
        return newDAO.query(model, pageable);
    }
}
