package br.com.alelo.consumerpat.core.dto.v1.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ConsumerPaginatedV1RequestDto {

    private Integer page = 0;
    private Integer size = 10;
}
