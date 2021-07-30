package br.com.alelo.consumer.consumerpat.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;

@Data
@Builder
public class MetaData implements Serializable {
    private String type;
    private Integer size;

    public static MetaData of(Object result) {
        if (result instanceof Collection) {
            return MetaData
                    .builder()
                    .type("list")
                    .size(((Collection<?>) result).size())
                    .build();
        }
        return MetaData
                .builder()
                .type("object")
                .build();
    }
}
