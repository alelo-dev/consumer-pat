package br.com.alelo.consumer.consumerpat.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Builder
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class TypeCard implements Serializable {
    private static final long serialVersionUID = -8067670584985855955L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTypeCard;
    private String typeCard;
}
