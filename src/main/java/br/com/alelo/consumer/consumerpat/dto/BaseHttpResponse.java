package br.com.alelo.consumer.consumerpat.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class BaseHttpResponse<T> implements Serializable {
    private MetaData metaData;
    private T result;

    public static <T> BaseHttpResponse<T> of(T result) {
        return BaseHttpResponse.<T>builder()
                .result(result)
                .metaData(MetaData.of(result))
                .build();
    }
}
