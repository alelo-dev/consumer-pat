package br.com.alelo.consumer.consumerpat.web.vo.pagination;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@Setter
public class QueryResultVO<T> implements Serializable {

    private List<T> records;
    private PageVO page;

}
