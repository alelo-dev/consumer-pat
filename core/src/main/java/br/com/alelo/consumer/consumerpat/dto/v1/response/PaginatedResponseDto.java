package br.com.alelo.consumer.consumerpat.dto.v1.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedResponseDto<T> {

    List<T> content;
    Integer size;
    Integer totalPages;
    Long totalElements;
    Integer pageNumber;
    Boolean firstPage;
    Boolean lastPage;
}
