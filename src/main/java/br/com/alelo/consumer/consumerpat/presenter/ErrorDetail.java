package br.com.alelo.consumer.consumerpat.presenter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author Guilherme de Castro
 * @created 31/08/2021 - 10:52
 */

@ApiModel
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "message")
public class ErrorDetail {
    @ApiModelProperty(example = "error.business.request.invalid", required = true, value = "Error Code.")
    private String informationCode;

    @ApiModelProperty(example = "The attribute {1} must be filled.", required = true, value = "Description of the error.")
    private String message;

}
