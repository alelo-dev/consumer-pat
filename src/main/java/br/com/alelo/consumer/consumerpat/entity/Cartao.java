package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.exceptions.BusinessException;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author renanravelli
 */

@Entity
@Getter
@Table(name = "cartao")
@NoArgsConstructor
@AllArgsConstructor
public class Cartao {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "tipo")
  @Enumerated(EnumType.STRING)
  private CartaoTipo tipo;

  @Column(name = "number")
  private Integer number;

  @Column(name = "balance")
  private BigDecimal balance;

  @ManyToOne
  @JoinColumn(name = "id_consumer", referencedColumnName = "id")
  private Consumer consumer;

  public void addConsumer(Consumer consumer) {
    this.consumer = consumer;
  }

  public Cartao creditar(BigDecimal valor) {
    this.balance = this.balance.add(valor);
    return this;
  }

  public Cartao debitar(BigDecimal valor) {
    assertConsumer(hasSaldo(valor, balance), "Saldo insuficiente no cart\u00E3o");
    this.balance = this.balance.subtract(valor);
    return this;
  }

  private boolean hasSaldo(BigDecimal valor, BigDecimal p) {
    return p.compareTo(valor) < 0;
  }

  private void assertConsumer(Boolean is, String msg) {
    if (is) {
      throw new BusinessException(msg);
    }
  }

}
