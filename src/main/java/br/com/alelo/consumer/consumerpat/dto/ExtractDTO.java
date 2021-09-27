package br.com.alelo.consumer.consumerpat.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ExtractDTO {

    private Integer id;
    private Integer establishmentNameId;
    private String establishmentName;
    private String productDescription;
    private Date dateBuy;
    private Integer cardNumber;
    private Double value;

}
