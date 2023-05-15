package com.example.javaweb.paging;

import java.util.List;

/**
 * @author: Nguyen Anh Tuan
 * @created: 5/15/2023
 */

public class PageImpl<T> implements Page<T> {

    List<T> data;
    Integer page;
    Integer size;

    public PageImpl(List<T> data, Integer page, Integer size) {
        this.data = data;
        this.page = page;
        this.size = size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public List<T> getData() {
        return data;
    }

    @Override
    public Integer getSize() {
        return size;
    }

    @Override
    public Integer getPage() {
        return page;
    }
}
