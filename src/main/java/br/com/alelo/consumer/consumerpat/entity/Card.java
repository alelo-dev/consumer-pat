package br.com.alelo.consumer.consumerpat.entity;

import java.time.LocalDateTime;
import java.util.Date;


import javax.persistence.*;

import lombok.*;



@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "CARD_NUMBER")
	private String CardNumber;
	@Column(name = "CARD_TYPE")
	private Integer CarType;
	@Column(name = "CARD_BALANCE")
	private Double CardBalance;
	@Column(name = "EXPIRATIO_DATE")
	private Date expirationdate;
	@Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
	

}
