package br.com.alelo.consumer.consumerpat.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author renanravelli
 */

@Getter
@ApiModel(value = "CustomErrorJsonResponse", description = "Informa\u00E7\u00F5es de mensagem de erro.")
public class CustomErrorJsonResponse {

  @ApiModelProperty(name = "errorCode", value = "Code error", position = 1)
  private final int errorCode;

  @ApiModelProperty(name = "error", value = "Error", position = 2)
  private final String error;

  @ApiModelProperty(name = "errorMessage", value = "Error message", position = 3)
  private final String errorMessage;

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private final List<? extends Object> fieldErrors;

  @Builder
  public CustomErrorJsonResponse(HttpStatus status, String message,
      List<? extends Object> fieldErrors) {
    this.errorCode = status.value();
    this.error = status.name();
    this.errorMessage = message;
    this.fieldErrors = fieldErrors;
  }


}
