package br.com.alelo.consumer.consumerpat.tool;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HttpResponse {
    int code;
    String body;
}
