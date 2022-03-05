package br.com.alelo.consumer.consumerpat.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class ExtractResponseDTO {

    private Integer establishmentNameId;
    private String establishmentName;
    private String productDescription;
    private Date dateBuy;
    private Integer cardNumber;
    private Double value;
}
