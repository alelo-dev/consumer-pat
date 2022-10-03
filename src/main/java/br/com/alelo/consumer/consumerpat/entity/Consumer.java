package br.com.alelo.consumer.consumerpat.entity;



import lombok.*;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Consumer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "DOCUMENT_NUMBER")
    private String documentNumber;
	@Column(name = "BIRTH_DATE")
	private LocalDate birthDate;
	@Column(name = "EMAIL")
	private String email;    
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "id_consumer", nullable = false)
	private List<Phone> PhoneList = new ArrayList<>();
	@OneToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_consumer", nullable = false)
    private List<Card> cardList;
	 @OneToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
	 @JoinColumn(name = "id_consumer", nullable = false)
	private List<Address> addressList;
	@Column(name = "CREATE_DATE")
    private LocalDateTime createdAt;
	@Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
	
	

}
