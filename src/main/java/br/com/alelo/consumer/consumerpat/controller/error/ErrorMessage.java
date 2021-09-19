package br.com.alelo.consumer.consumerpat.controller.error;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorMessage {

  private String message;
}
