package br.com.alelo.consumer.consumerpat.requests;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
public class BuyRequest {
    @Min(value = 1, message = "Tipo de estabelecimento não pode ser menor que 1")
    @Max(value = 3, message = "Tipo de estabelecimento não pode ser maior que 3")
    public int establishmentType;
    @NotEmpty(message = "Nome do estabelecimento não pode ser vazio")
    public String establishmentName;
    @NotEmpty(message = "Descrição do produto não pode ser vazia")
    public String productDescription;
    @Positive(message = "Número do cartão não pode ser valores menores que zero")
    public int cardNumber;
    @Positive(message = "O valor da compra não pode ser negativo ou zero")
    public double value;
}
