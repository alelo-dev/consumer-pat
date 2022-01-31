package br.com.alelo.consumer.consumerpat.domain.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;



@Data
@Builder
@EqualsAndHashCode(of = "{id,documentNumber}")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Consumer extends EntidadeBase<Integer> {

    private static final long serialVersionUID = 1L;

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


    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createDate;

}
