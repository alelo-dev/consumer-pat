package br.com.alelo.consumer.consumerpat.web.vo.pagination;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseFilterVO implements Serializable {

    private Integer page = 0;
    private Integer size = 10;
    private SortDirection sortDirection = SortDirection.ASC;

}
