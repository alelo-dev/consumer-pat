package br.com.alelo.consumer.consumerpat.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(exclude = "consumer")
public class Contact {
	
	@Id
	private Integer id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "consumer_id")
	private Consumer consumer;
	
    private String mobilePhoneNumber;
    private String residencePhoneNumber;
    private String phoneNumber;
    private String email;

}
