package com.example.javaweb.paging;

import com.example.javaweb.sort.Sorter;

public class PageRequest implements Pageable {
    private Integer page;
    private Integer maxPageItem;
    private Sorter sorter;


    public PageRequest(Integer page, Integer maxPageItem, Sorter sorter) {
        this.page = page;
        this.maxPageItem = maxPageItem;
        this.sorter = sorter;
    }

    public static PageRequest of(Integer page, Integer size) {
        return new PageRequest(page, size, null);
    }

    public Sorter getSorter() {
        if (this.sorter != null) {
            return sorter;
        }
        return null;
    }


    @Override
    public Integer getPage() {
        return this.page;
    }

    @Override
    public Integer getOffset() {
        if (this.page != null && this.maxPageItem != null) {
            return (this.page - 1) * this.maxPageItem;
        } else return null;
    }

    @Override
    public Integer getLimit() {
        return this.maxPageItem;
    }
}
