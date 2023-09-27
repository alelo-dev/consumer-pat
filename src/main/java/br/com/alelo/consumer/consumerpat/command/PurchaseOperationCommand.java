package br.com.alelo.consumer.consumerpat.command;

import lombok.Data;

@Data
public class PurchaseOperationCommand implements Command{

    private int establishmentType; 
    private String establishmentName; 
    private int cardNumber; 
    private String productDescription; 
    private double value;
    
    public PurchaseOperationCommand() {
    }

    public PurchaseOperationCommand(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
        this.establishmentType = establishmentType;
        this.establishmentName = establishmentName;
        this.cardNumber = cardNumber;
        this.productDescription = productDescription;
        this.value = value;
    }   
}
