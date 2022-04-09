package br.com.alelo.consumer.consumerpat.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;



import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Consumer implements Serializable {


	private static final long serialVersionUID = -6138314180053033252L;

	@Id
    private String idConsumer;
    
    String name;
    
    Integer documentNumber;
    
    LocalDate birthDate;

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
