package br.com.alelo.consumer.consumerpat.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Consumer implements Serializable {


	private static final long serialVersionUID = -6138314180053033252L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private UUID idConsumer;
    
    private String name;
    
    @Column(unique = true)
    private String documentNumber;
    
    private LocalDate birthDate;
    
    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    LocalDateTime creationTime;

    @OneToMany(targetEntity=Contact.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "CONSUMER_ID", referencedColumnName = "idConsumer")
    private List<Contact> contacts;
    
    @OneToMany(targetEntity=Address.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "CONSUMER_ID", referencedColumnName = "idConsumer")
    private List<Address> addresses;
    
    @OneToMany(targetEntity=Card.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "CONSUMER_ID", referencedColumnName = "idConsumer")
    private List<Card> cards;
}
