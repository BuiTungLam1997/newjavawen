package com.example.javaweb.dao.impl;

import com.example.javaweb.dao.INewDAO;
import com.example.javaweb.mapper.NewMapper;
import com.example.javaweb.model.NewModel;
import com.example.javaweb.paging.Pageble;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {
    @Override
    public List<NewModel> findByCategoryId(Long categoryId) {
        String sql = "select * from news where categoryid = ?";
        return query(sql, new NewMapper(), categoryId);
    }

    @Override
    public Long save(NewModel newModel) {
        StringBuilder sql = new StringBuilder("insert into news (title,content,categoryid ,shortdescription , ");
        sql.append(" thumbnail ,createddate ,createdby , modifieddate ,modifiedby )");
        sql.append("values (?,?,?,?,?,?,?,?,?)");
        return insert(sql.toString(), newModel.getTitle(), newModel.getContent(), newModel.getCategoryId(),
                newModel.getShortDescription(), newModel.getThumbnail(), newModel.getCreatedDate(), newModel.getCreatedBy(),
                newModel.getModifiedDate(), newModel.getModifiedBy());
    }

    @Override
    public void delete(long id) {
        String sql = "delete from news where id = ?";
        update(sql, id);
    }


    @Override
    public NewModel findOne(Long id) {
        String sql = "select * from news where id = ?";
        List<NewModel> news = query(sql, new NewMapper(), id);
        return news.isEmpty() ? null : news.get(0);
    }

    @Override
    public void update(NewModel newModel) {
        StringBuilder sql = new StringBuilder("update news set title = ?, thumbnail = ? , ");
        sql.append("shortdescription = ? , content = ? , categoryid = ? , ");
        sql.append("createddate = ? , createdby = ? , modifieddate = ? , modifiedby = ? where id = ?");

        update(sql.toString(), newModel.getTitle(), newModel.getThumbnail(), newModel.getShortDescription(),
                newModel.getContent(), newModel.getCategoryId(), newModel.getCreatedDate(), newModel.getCreatedBy()
                , newModel.getModifiedDate(), newModel.getModifiedBy(), newModel.getId());

    }

    @Override
    public List<NewModel> findAll(Pageble pageble) {
        StringBuilder sql = new StringBuilder("select * from news");
        if (pageble.getSorter() != null && StringUtils.isNoneBlank(pageble.getSorter().getSortName())
                && StringUtils.isNoneBlank(pageble.getSorter().getSortBy())) {
            sql.append(" order by " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + " ");
        }
        if (pageble.getOffset() != null && pageble.getLimit() != null) {
            sql.append(" limit " + pageble.getOffset() + "," + pageble.getLimit() + "");
        }
        return query(sql.toString(), new NewMapper());
    }

    @Override
    public int getToTalItem() {
        String sql = "select count(*) from news";
        return count(sql, new NewMapper());
    }

}
