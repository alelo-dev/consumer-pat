package br.com.alelo.consumer.consumerpat.pagination.view;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class PagedResponseWrapper<T> {

    private List<T> objects;

    private Paging paging;
}
