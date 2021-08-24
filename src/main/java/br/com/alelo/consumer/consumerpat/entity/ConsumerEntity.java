package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity 
public class ConsumerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer documentNumber; 
    private LocalDate birthDate;

    //contacts EAGER default
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private ContactEntity contactEntity;

    //Address EAGER default
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private AddressEntity addressEntity;
    
    //cards 
    //SET performance na inserção e elimina cartões duplicados
    @OneToMany(mappedBy = "consumerEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CardEntity> cards;

}
