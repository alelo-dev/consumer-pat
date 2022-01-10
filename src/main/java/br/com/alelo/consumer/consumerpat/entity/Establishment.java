package br.com.alelo.consumer.consumerpat.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Builder
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Establishment implements Serializable {
    private static final long serialVersionUID = -8751276983280904785L;

    @Id
    private String idEstablishment;
    private String nameEstablishment;


}
