package com.example.javaweb.controller.admin.api.request;

/**
 * @author: Nguyen Anh Tuan
 * @created: 5/15/2023
 */

public class CommonQuery {

    Integer page = 1;
    Integer size = 10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
