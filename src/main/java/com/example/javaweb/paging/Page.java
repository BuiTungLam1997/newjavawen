package com.example.javaweb.paging;

import java.util.List;

/**
 * @author: Nguyen Anh Tuan
 * @created: 5/15/2023
 */

public interface Page<T>{

    List<T> getData();

    Integer getSize();

    Integer getPage();
    boolean isEmpty();

}
