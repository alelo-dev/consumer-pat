package br.com.alelo.consumer.consumerpat.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageData<T> {

    private List<T> rows;
    private int currentPage;
    private long totalItems;
    private int totalPages;

}