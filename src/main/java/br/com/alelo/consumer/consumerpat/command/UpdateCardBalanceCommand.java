package br.com.alelo.consumer.consumerpat.command;

import lombok.Data;

@Data
public class UpdateCardBalanceCommand implements Command{
    private int cardNumber;
    private double value;

    public UpdateCardBalanceCommand() {
    }

    public UpdateCardBalanceCommand(int cardNumber, double value) {
        this.cardNumber = cardNumber;
        this.value = value;
    }
    
}
