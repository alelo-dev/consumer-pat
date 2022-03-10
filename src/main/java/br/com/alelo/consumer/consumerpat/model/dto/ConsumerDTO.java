package br.com.alelo.consumer.consumerpat.model.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ConsumerDTO {
	
	@NotEmpty(message = "Required field")
    private String name;
	
	@NotNull(message = "Required field")
    private Integer documentNumber;
    
	@NotNull(message = "Required field")
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @NotNull(message = "Required field")
    private ContactVO contact;

    @NotNull(message = "Required field")
	private AdressVO adress;

    @NotNull(message = "Required field")
	private List<CardVO> cards;
}
