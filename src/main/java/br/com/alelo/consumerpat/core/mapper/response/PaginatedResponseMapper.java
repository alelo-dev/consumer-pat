package br.com.alelo.consumerpat.core.mapper.response;

import br.com.alelo.consumerpat.core.dto.v1.response.PaginatedResponseDto;
import org.springframework.data.domain.Page;

public class PaginatedResponseMapper<T> {

    public PaginatedResponseDto<T> convert(Page<T> data) {
        PaginatedResponseDto<T> responseDto = new PaginatedResponseDto<>();

        responseDto.setContent(data.getContent());
        responseDto.setFirstPage(data.isFirst());
        responseDto.setLastPage(data.isLast());
        responseDto.setPageNumber(data.getNumber());
        responseDto.setTotalElements(data.getTotalElements());
        responseDto.setSize(data.getSize());
        responseDto.setTotalPages(data.getTotalPages());

        return responseDto;
    }
}
