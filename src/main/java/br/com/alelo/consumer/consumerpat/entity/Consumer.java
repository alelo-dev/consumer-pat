package br.com.alelo.consumer.consumerpat.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;



@Data
@Builder
@EqualsAndHashCode(of = "{id,documentNumber}")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Consumer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private  Integer documentNumber;
    private  Date birthDate;

    //contacts
    private  String mobilePhoneNumber;
    private String residencePhoneNumber;
    private  String phoneNumber;
    private  String email;

    //Address
    private  String street;
    private  Integer number;
    private  String city;
    private  String country;
    private  Integer portalCode;


    private LocalDateTime createDate;

}
