package br.com.alelo.consumer.consumerpat.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;


//TODO NAO FOI DEFINIDO OS CAMPOS OBRIGATORIOS.
@Data
@Entity
public class Extract {

 //TODO definir modificador de acesso aos paramentros.
	  @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	  private String productDescription;
    private Date dateBuy;
    private int cardNumber;
    private double value;
//TODO Responsabilidade unica, esses parametros nao sao do Extract e sim do Establishment
//    private int establishmentNameId;
//    private String establishmentName;
  	@ManyToOne
    @JoinColumn(name = "establishment_id")
    private Establishment establishment;
    
    


//TODO Como estamos utilizando Lombok, especificamente o @Data nao se faz necessario fazer o override desse construtor.
//TODO  construtor não utilizado
//    public Extract(int id, int establishmentNameId, String establishmentName, String productDescription, Date dateBuy, int cardNumber, double value) {
//        this.id = id;
//        this.establishmentNameId = establishmentNameId;
//        this.establishmentName = establishmentName;
//        this.productDescription = productDescription;
//        this.dateBuy = dateBuy;
//        this.cardNumber = cardNumber;
//        this.value = value;
//    }

//TODO  construtor não utilizado
//    public Extract( String productDescription, Date dateBuy, int cardNumber, double value) {
//        this.productDescription = productDescription;
//        this.dateBuy = dateBuy;
//        this.cardNumber = cardNumber;
//        this.value = value;
//    }

//TODO a melhor forma seria utilizar o @Builder e criar um metodo espefico para preencher os campos
//    public Extract(String establishmentName, String productDescription, Date dateBuy, int cardNumber, double value) {
//        this.establishmentNameId = establishmentNameId; // TODO establishmentNameId nao definido no construtor.
//    	  this.establishmentName = establishmentName;
//        this.productDescription = productDescription;
//        this.dateBuy = dateBuy;
//        this.cardNumber = cardNumber;
//        this.value = value;
//    }
}
