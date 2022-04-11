package br.com.alelo.consumer.consumerpat.dtos;

import lombok.Data;

@Data
public class CardsDto {

    private int foodCardNumber;
    private double foodCardBalance;
    private int fuelCardNumber;
    private double fuelCardBalance;
    private int drugstoreNumber;
    private double drugstoreCardBalance;
}
