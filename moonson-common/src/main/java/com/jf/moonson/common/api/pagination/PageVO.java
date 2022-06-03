package com.jf.moonson.common.api.pagination;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PageVO<T> {

    /**
     * 总条数q
     */
    private long total;

    /**
     * 每页数据对象
     */
    private List<T> data;

    public static <T> PageVO<T> empty() {
        return PageVO.<T>builder().total(0).data(new ArrayList<T>()).build();
    }
}
