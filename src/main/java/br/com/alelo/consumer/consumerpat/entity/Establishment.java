package br.com.alelo.consumer.consumerpat.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Establishment implements Serializable {
	
    private static final long serialVersionUID = 8932056298603043719L;
    
	@Id
    private String idEstablishment;
	
    private String nameEstablishment;

}
