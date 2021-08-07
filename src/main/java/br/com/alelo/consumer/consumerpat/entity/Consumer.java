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
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "consumers")
public class Consumer {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
