package br.com.alelo.consumer.consumerpat.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardDto implements Serializable {

    private static final long serialVersionUID = 5176252856729555060L;

	private Long cardNumber;

    private TypeCardDto typeCard;
}
