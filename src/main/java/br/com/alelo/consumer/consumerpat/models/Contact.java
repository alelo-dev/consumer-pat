package br.com.alelo.consumer.consumerpat.models;


import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private int mobilePhoneNumber;
    @Column
    private int residencePhoneNumber;
    @Column
    private int phoneNumber;
    @Column
    private String email;

}
