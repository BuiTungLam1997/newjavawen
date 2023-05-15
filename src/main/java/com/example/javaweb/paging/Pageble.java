package com.example.javaweb.paging;

import com.example.javaweb.sort.Sorter;

public interface Pageble {
    Integer getPage();

    Integer getOffset();

    Integer getLimit();

    Sorter getSorter();
}
