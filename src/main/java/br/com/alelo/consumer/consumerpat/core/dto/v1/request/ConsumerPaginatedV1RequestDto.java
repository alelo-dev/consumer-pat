package br.com.alelo.consumer.consumerpat.core.dto.v1.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConsumerPaginatedV1RequestDto {

    private Integer page = 0;
    private Integer size = 10;
}
