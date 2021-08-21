package br.com.alelo.consumer.consumerpat.mapper.response;

import br.com.alelo.consumer.consumerpat.dto.v1.response.PaginatedResponseDto;
import br.com.alelo.consumer.consumerpat.mapper.BaseMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class PaginatedResponseMapper<T, K> {

    public PaginatedResponseDto<K> convert(Page<T> data, BaseMapper<T, K> mapper) {
        PaginatedResponseDto<K> responseDto = new PaginatedResponseDto<>();

        List<K> content = data.getContent().stream().map(mapper::convert).collect(Collectors.toList());

        responseDto.setContent(content);
        responseDto.setFirstPage(data.isFirst());
        responseDto.setLastPage(data.isLast());
        responseDto.setPageNumber(data.getNumber());
        responseDto.setTotalElements(data.getTotalElements());
        responseDto.setSize(data.getSize());
        responseDto.setTotalPages(data.getTotalPages());

        return responseDto;
    }
}
