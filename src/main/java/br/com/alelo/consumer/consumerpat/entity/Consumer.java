package br.com.alelo.consumer.consumerpat.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Consumer {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  String name;
  Integer documentNumber;
  LocalDateTime birthDate;

  @Embedded private Contact contact;

  @Embedded private Address address;

  @OneToMany(mappedBy = "consumer", cascade = CascadeType.ALL)
  private List<Card> cards;
}
