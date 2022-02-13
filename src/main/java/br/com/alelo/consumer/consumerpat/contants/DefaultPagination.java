package br.com.alelo.consumer.consumerpat.contants;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
public enum DefaultPagination {
    DEFAULT_PAGINATION(PageRequest.of(0, 10));
    //Incluir outros presets de paginação

    Pageable page;

    DefaultPagination(Pageable page){
        this.page = page;
    }

}
