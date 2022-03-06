package br.com.alelo.consumer.consumerpat.domain.dto;

import br.com.alelo.consumer.consumerpat.domain.entity.Extract;
import br.com.alelo.consumer.consumerpat.domain.enums.EstablishmentType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel
public class EstablishmentRequestDTO {
    private ExtractRequestDTO extract;

    @ApiModelProperty(name = "establishmentType", example = "DRUGSTORE, FOOD OU FUEL")
    private EstablishmentType establishmentType;

}
