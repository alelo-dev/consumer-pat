package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.enums.TypeContact;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Contact extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private TypeContact typeContact;

    @Column(nullable = false)
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    private Consumer consumer;

}
