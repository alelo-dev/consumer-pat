package br.com.alelo.consumer.consumerpat.model.entity;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import br.com.alelo.consumer.consumerpat.model.entity.base.EntityBase;
import lombok.Data;


@Data
@Entity
public class Consumer extends EntityBase {
	
    private String name;
	
    private Integer documentNumber;
	
    private LocalDateTime birthDate;

    //Contacts
    @OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_consumer")
    private List<Contact> contacts = new ArrayList<>();
    
    //Address
    @OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_consumer")
    private List<Addres> adress = new ArrayList<>();
    
    //cards
    @OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_consumer")
    private List<Card> cards = new ArrayList<>();
    
}
