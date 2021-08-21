package br.com.alelo.consumer.consumerpat.dto.v1.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerPaginatedV1RequestDto {

    private Integer page = 0;
    private Integer size = 0;
}
