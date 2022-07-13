package br.com.alelo.consumer.consumerpat.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;


@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "extract")
public class Extract {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "establishment_name_id")
  private int establishmentNameId;

  @Column(name = "establishment_name")
  private String establishmentName;

  @Column(name = "product_description")
  private String productDescription;

  @CreationTimestamp
  @Column(name = "date_buy")
  private LocalDateTime dateBuy;

  @Column(name = "card_number")
  private int cardNumber;

  @Column(name = "value")
  private BigDecimal value;

  public Extract(String establishmentName, String productDescription, int cardNumber,
      BigDecimal value) {
    this.establishmentName = establishmentName;
    this.productDescription = productDescription;
    this.cardNumber = cardNumber;
    this.value = value;
  }
}
