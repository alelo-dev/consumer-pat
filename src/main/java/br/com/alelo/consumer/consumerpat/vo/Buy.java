package br.com.alelo.consumer.consumerpat.vo;

import lombok.Data;

@Data
public class Buy {
    int establishmentType;
    String establishmentName;
    Cards cards;
    String productDescription;
}
