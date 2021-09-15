package br.com.alelo.consumer.consumerpat.entity;

import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="Address", description="Model class for fisical addresses")
public class Address implements Serializable {

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "CITY")
    private String city;

    @Column(name = "POSTAL_CODE")
    private int postalCode;

    @Column(name = "STREET")
    private String street;

    @Column(name = "NUMBER")
    private int number;
}
