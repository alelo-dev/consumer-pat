package br.com.alelo.consumer.consumerpat.entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;


@Data
@Entity
@Table(name = "consumer")
public class Consumer implements Serializable {


	private static final long serialVersionUID = -8711909404864335747L;


	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private Long documentNumber;
	
	@Temporal(TemporalType.DATE) 
	private Date birthDate;

	@JoinColumn(unique=true)
	@OneToOne(cascade=CascadeType.PERSIST)	
	private Contact contact;

	@JoinColumn(unique=true)
	@OneToOne(cascade=CascadeType.PERSIST)	
	private Address address;

	@OneToMany(mappedBy = "consumer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)	
	private List<Card> cards;
	

	public Consumer() {
		
		contact = new Contact();
		address = new Address();
		cards   = new ArrayList<Card>();
	}


	//-----------Getters and Setters-------------//
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Long getDocumentNumber() {
		return documentNumber;
	}


	public void setDocumentNumber(Long documentNumber) {
		this.documentNumber = documentNumber;
	}


	public Date getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public Contact getContact() {
		return contact;
	}


	public void setContact(Contact contact) {
		this.contact = contact;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public List<Card> getCards() {
		return cards;
	}


	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
 
}