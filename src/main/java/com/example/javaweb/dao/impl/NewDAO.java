package com.example.javaweb.dao.impl;

import com.example.javaweb.anatation.Column;
import com.example.javaweb.anatation.Table;
import com.example.javaweb.controller.admin.api.request.NewQuery;
import com.example.javaweb.dao.INewDAO;
import com.example.javaweb.mapper.NewMapper;
import com.example.javaweb.model.NewModel;
import com.example.javaweb.paging.Page;
import com.example.javaweb.paging.PageImpl;
import com.example.javaweb.paging.Pageable;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.List;

public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {

    private final String tableName;

    public NewDAO() {
        this.tableName = NewModel.class.getAnnotation(Table.class).name();
    }

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
    public List<NewModel> findAll(Pageable pageable) {
        StringBuilder sql = new StringBuilder("select * from news");
        if (pageable.getSorter() != null && StringUtils.isNoneBlank(pageable.getSorter().getSortName())
                && StringUtils.isNoneBlank(pageable.getSorter().getSortBy())) {
            sql.append(" order by " + pageable.getSorter().getSortName() + " " + pageable.getSorter().getSortBy() + " ");
        }
        if (pageable.getOffset() != null && pageable.getLimit() != null) {
            sql.append(" limit " + pageable.getOffset() + "," + pageable.getLimit() + "");
        }
        return query(sql.toString(), new NewMapper());
    }

    @Override
    public int getToTalItem() {
        String sql = "select count(*) from news";
        return count(sql, new NewMapper());
    }

    @Override
    public Page<NewModel> query(NewModel query, Pageable pageable) {
        StringBuilder sql = new StringBuilder("select * from ").append(tableName);
        sql.append(" WHERE 1=1 ");
        Field[] fields = NewModel.class.getDeclaredFields();
        try {
            for (Field field : fields) {
                Column column = field.getAnnotation(Column.class);
                if (column == null) {
                    continue;
                }
                field.setAccessible(true);
                Object value = field.get(query);
                if (value == null) {
                    continue;
                }
                sql.append(" AND ");
                sql.append(tableName).append(".").append(column.name());
                sql.append(" = ").append(value);
            }
            sql.append(" LIMIT ").append(pageable.getOffset());
            sql.append(", ").append(pageable.getLimit());
            List<NewModel> result = query(sql.toString(), new NewMapper());
            return new PageImpl<>(result, pageable.getPage(), pageable.getLimit());
        } catch (Exception e) {

        }
        return null;
    }

    public static void main(String[] args) {
        Field[] fields = NewModel.class.getDeclaredFields();
        for (Field field : fields) {
            System.out.printf(field.getName());
        }
    }

}
