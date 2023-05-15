package com.example.javaweb.controller.admin.api.request;

/**
 * @author: Nguyen Anh Tuan
 * @created: 5/15/2023
 */

public class NewQuery extends CommonQuery {

    Long categoryId;
    String title;
    String content;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
