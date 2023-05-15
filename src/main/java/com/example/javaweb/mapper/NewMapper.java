package com.example.javaweb.mapper;

import com.example.javaweb.model.NewModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewMapper implements RowMapper<NewModel> {
    @Override
    public NewModel mapRow(ResultSet resultSet) {
        NewModel news = new NewModel();
        try {
            news.setId(resultSet.getLong("id"));
            news.setTitle(resultSet.getString("title"));
            news.setContent(resultSet.getString("content"));
            news.setShortDescription(resultSet.getString("shortdescription"));
            news.setThumbnail(resultSet.getString("thumbnail"));
            news.setCategoryId(resultSet.getLong("categoryid"));
            news.setCreatedDate(resultSet.getTimestamp("createddate"));
            news.setCreatedBy(resultSet.getString("createdby"));
            if (resultSet.getTimestamp("modifieddate") != null) {
                news.setModifiedDate(resultSet.getTimestamp("modifieddate"));
            }
            if (resultSet.getString("modifiedby") != null) {
                news.setModifiedBy(resultSet.getString("modifiedby"));
            }

            return news;
        } catch (SQLException e) {
            return null;
        }

    }
}
