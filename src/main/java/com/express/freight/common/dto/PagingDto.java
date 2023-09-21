package com.express.freight.common.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagingDto<T> {

    private List<T> data;
    private Long totalCount;
    private Long totalMount;

    public PagingDto(List<T> data, Long totalCount) {
        this.data = data;
        this.totalCount = totalCount;
    }


}
