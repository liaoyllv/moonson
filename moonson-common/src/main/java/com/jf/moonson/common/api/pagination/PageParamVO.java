package com.jf.moonson.common.api.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>分页参数接收</p>
 **/
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageParamVO {

    /**
     * 分页编号
     * */
    private int pageNumber;

    /**
     * 分页大小
     * */
    private int pageSize;

    public void setPageNumber(Integer pageNumber) {
        if(pageNumber == null || pageNumber < 1){
            pageNumber = 1;
        }
        this.pageNumber = pageNumber;
    }

    public void setPageSize(Integer pageSize) {
        if(pageSize == null || pageSize < 1){
            pageSize = 10;
        }
        this.pageSize = pageSize;
    }
}
