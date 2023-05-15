package com.example.javaweb.paging;

import com.example.javaweb.sort.Sorter;

public interface Pageable {
    Integer getPage();

    Integer getOffset();

    Integer getLimit();

    Sorter getSorter();


}
