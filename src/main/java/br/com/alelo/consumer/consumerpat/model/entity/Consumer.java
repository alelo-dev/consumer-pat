package br.com.alelo.consumer.consumerpat.model.entity;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"documentNumber"})})
public class Consumer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String documentNumber;
	
	private LocalDate birthDate;

	//contacts
	private String mobilePhoneNumber;
	private String residencePhoneNumber;
	private String phoneNumber;
	
	@Column(nullable = false)
	private String email;

	//Address
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "consumer_adress_id", nullable = false)
	private ConsumerAddress consumerAddress;
	
	@OneToMany(mappedBy = "consumer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<ConsumerCard> consumerCards;

	public void merge(final Consumer consumerConverted) {
		this.name = consumerConverted.getName();
		this.documentNumber = consumerConverted.getDocumentNumber(); 
		this.birthDate = consumerConverted.getBirthDate();
		this.mobilePhoneNumber = consumerConverted.getMobilePhoneNumber();
		this.residencePhoneNumber = consumerConverted.getResidencePhoneNumber();
		this.phoneNumber = consumerConverted.getPhoneNumber();
		this.email = consumerConverted.getEmail();
		consumerAddress.merge(consumerConverted.getConsumerAddress());
	}

}