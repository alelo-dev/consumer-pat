package br.com.alelo.consumer.consumerpat.entity;


import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@Entity(name = "consumers")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;

    @EqualsAndHashCode.Include
    @Column(unique = true, nullable = false)
    private Integer documentNumber;
    
    private LocalDate birthDate;

    @OneToMany(mappedBy = "consumer", cascade = CascadeType.ALL)
	private List<Address> address;
    
    @OneToMany(mappedBy = "consumer", cascade = CascadeType.PERSIST)
	private List<Card> cards;
    
    @OneToMany(mappedBy = "consumer", cascade = CascadeType.ALL)
	private List<Contact> contacts;

}
