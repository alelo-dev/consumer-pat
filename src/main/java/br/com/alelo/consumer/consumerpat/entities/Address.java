package br.com.alelo.consumer.consumerpat.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Embeddable
public class Address implements Serializable {

    private static final long serialVersionUID = -8039752987315380274L;
    @Column(name = "STREET")
    private String street;
    @Column(name = "NUMBER")
    private Integer number;
    @Column(name = "CITY")
    private String city;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "PORTAL_CODE")
    private Integer portalCode;

}
