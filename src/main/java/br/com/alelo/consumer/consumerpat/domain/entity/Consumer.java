package br.com.alelo.consumer.consumerpat.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "tb_consumer")
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(unique = true)
    private String documentNumber;
    
    @Column(nullable = false)
    private LocalDate birthDate;

    @OneToOne(cascade=CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "contact_id", nullable = false)
  	private Contact contact;
  	
  	@Embedded
  	private Address address;
  	
  	@OneToMany(targetEntity=Card.class,cascade = CascadeType.ALL, 
  			fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "consumer_id", referencedColumnName = "id")
	private Set<Card> cards;
  	
  	@CreationTimestamp
    private LocalDateTime createdAt;
}
