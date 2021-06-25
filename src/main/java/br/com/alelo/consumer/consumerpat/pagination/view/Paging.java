package br.com.alelo.consumer.consumerpat.pagination.view;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Paging implements Serializable {

	private static final long serialVersionUID = 1L;

    private int offset;

    private int limit;

    private long total;
    
    private long totalPage;
}
