package br.com.alelo.consumer.consumerpat.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaData implements Serializable {
    private String type;
    private Integer size;

    @Builder
    public MetaData(String type, Integer size) {
        this.type = type;
        this.size = size;
    }

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
