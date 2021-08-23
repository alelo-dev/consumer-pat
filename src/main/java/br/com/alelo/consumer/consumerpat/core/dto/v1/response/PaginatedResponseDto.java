package br.com.alelo.consumer.consumerpat.core.dto.v1.response;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaginatedResponseDto<T> {

    List<T> content;
    Integer size;
    Integer totalPages;
    Long totalElements;
    Integer pageNumber;
    Boolean firstPage;
    Boolean lastPage;
}
