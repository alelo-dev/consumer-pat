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
public class Contacts implements Serializable {

    private static final long serialVersionUID = -8429725551054630592L;
    @Column(name = "MOBILE_PHONE_NUMBER")
    private Integer mobilePhoneNumber;
    @Column(name = "RESIDENCE_PHONE_NUMBER")
    private Integer residencePhoneNumber;
    @Column(name = "PHONE_NUMBER")
    private Integer phoneNumber;
    @Column(name = "EMAIL")
    private String email;

}
