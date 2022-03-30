package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;


@Builder
@Data
@Entity
@Table(name = "card_type")
@NoArgsConstructor
@AllArgsConstructor
public class CardType implements Serializable {

    private static final long serialVersionUID = 724496131524444512L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;

}