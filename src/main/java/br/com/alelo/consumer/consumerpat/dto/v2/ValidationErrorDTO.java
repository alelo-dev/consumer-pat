package br.com.alelo.consumer.consumerpat.dto.v2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorDTO {
    private String campo;
    private String mensagemErro;
}
