package br.com.alelo.consumer.consumerpat.entity;

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
public class Contact implements Serializable {

    @Column(name = "MOBILE_PHONE_NUMBER")
    private int mobilePhoneNumber;

    @Column(name = "RESIDENCE_PHONE_NUMBER")
    private int residencePhoneNumber;

    @Column(name = "PHONE_NUMBER")
    private int phoneNumber;

    @Column(name = "EMAIL")
    private String email;
}
