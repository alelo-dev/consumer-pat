package br.com.alelo.consumer.consumerpat.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ExtractDto {
    private int id;
    private int establishmentNameId;
    private String establishmentName;
    private String productDescription;
    private LocalDate dateBuy;
    private int cardNumber;
    private double value;
}
