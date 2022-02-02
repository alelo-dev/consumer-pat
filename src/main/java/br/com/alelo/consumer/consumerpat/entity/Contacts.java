package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="contacts")
public class Contacts {


    //contacts - Entidade de telefone ok
    // Referencia
    //https://www.bbc.co.uk/bitesize/guides/zghbgk7/revision/4
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer contacts_id;

    private String mobilePhoneNumber;
    private String residencePhoneNumber;
    private String phoneNumber;
    private String email;
}
