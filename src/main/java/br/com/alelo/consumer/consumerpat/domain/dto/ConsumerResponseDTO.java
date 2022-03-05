package br.com.alelo.consumer.consumerpat.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel
public class ConsumerResponseDTO {

    private Integer id;
    private String name;
    private Integer documentNumber;
    private Date birthDate;
}
