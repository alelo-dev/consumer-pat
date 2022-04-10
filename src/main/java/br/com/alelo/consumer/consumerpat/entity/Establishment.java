package br.com.alelo.consumer.consumerpat.entity;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Establishment implements Serializable {
	
    private static final long serialVersionUID = 8932056298603043719L;
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private UUID idEstablishment;
    
    private String nameEstablishment;
    
    @OneToMany(targetEntity=TypeEstablishment.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "ID_ESTABLISHMENT", referencedColumnName = "idEstablishment")
    private List<TypeEstablishment> typeEstablishments;
    
}
