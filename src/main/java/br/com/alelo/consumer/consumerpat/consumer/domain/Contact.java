package br.com.alelo.consumer.consumerpat.consumer.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contactId;

    @JoinColumn(name = "contact_type_id")
    @ManyToOne
    private ContactType type;

    @Column(length = 100)
    private String info;

    @ManyToOne(fetch = FetchType.LAZY)
    private Consumer consumer;
}
