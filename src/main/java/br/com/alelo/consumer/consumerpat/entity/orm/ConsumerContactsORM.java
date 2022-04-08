package br.com.alelo.consumer.consumerpat.entity.orm;

import br.com.alelo.consumer.consumerpat.entity.ConsumerContacts;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "ConsumerContacts")
@Setter
@Getter
public class ConsumerContactsORM implements ConsumerContacts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "mobile_phone_number")
    private String mobilePhoneNumber;

    @Column(name = "residence_phone_number")
    private String residencePhoneNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

}
