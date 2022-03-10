package br.com.alelo.consumer.consumerpat.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Extract {
  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer establishmentNameId;

  String establishmentName;
  String productDescription;
  LocalDateTime dateBuy;
  String cardNumber;
  Double value;
}
