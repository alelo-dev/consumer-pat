package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.enumerator.EstablishmentType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(NON_EMPTY)
public class EstablishmentDTO {
    private String cnpj;
    private String name;
    private EstablishmentType type;
}
