package com.example.javaweb.model;

import com.example.javaweb.anatation.Column;
import com.example.javaweb.anatation.Table;
import com.example.javaweb.controller.admin.api.request.NewQuery;

@Table(name = "news")
public class NewModel extends AbstractModel<NewModel>{
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "thumbnail")
    private String thumbnail;
    @Column(name = "shortdescription")
    private String shortDescription;
    @Column(name = "categoryid")
    private Long categoryId;
    private String categoryCode;

    public static NewModel of(NewQuery queryModel) {
        NewModel model = new NewModel();
        model.setTitle(queryModel.getTitle());
        model.setCategoryId(queryModel.getCategoryId());
        model.setContent(queryModel.getContent());
        return model;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
