package br.com.alelo.consumer.consumerpat.web.vo.pagination;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class PageVO implements Serializable {

    private Boolean hasNext;
    private Boolean hasPrev;
    private Integer page;
    private Integer totalPages;
    private Integer size;
    private Long totalElements;

}