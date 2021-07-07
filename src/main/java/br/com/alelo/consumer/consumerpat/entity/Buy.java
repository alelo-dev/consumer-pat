package br.com.alelo.consumer.consumerpat.entity;



import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Buy {
    private int cardNumber;
    private String establishmentName;
    private int establishmentType;
    private String productDescription;
    private Double value;
}
