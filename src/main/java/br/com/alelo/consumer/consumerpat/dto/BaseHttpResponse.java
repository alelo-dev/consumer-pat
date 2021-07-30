package br.com.alelo.consumer.consumerpat.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseHttpResponse<T> implements Serializable {
    private MetaData metaData;
    private T result;

    @Builder
    public BaseHttpResponse(MetaData metaData, T result) {
        this.metaData = metaData;
        this.result = result;
    }

    public static <T> BaseHttpResponse<T> of(T result) {
        return BaseHttpResponse.<T>builder()
                .result(result)
                .metaData(MetaData.of(result))
                .build();
    }
}
