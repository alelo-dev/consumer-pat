package br.com.alelo.consumer.consumerpat.infrastructure.repositories.entities;

import br.com.alelo.consumer.consumerpat.domain.entities.Extract;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "extract")
@NoArgsConstructor
public class ExtractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "establishment_name")
    private String establishmentName;
    @Column(name = "product_description")
    private String productDescription;
    @Column(name = "date_buy")
    private LocalDate dateBuy;
    @Column(name = "card_number")
    private String cardNumber;
    private double amount;

    @Column(name = "consumer_id")
    private int consumerId;

    public Extract toModel() {
        Extract extract = new Extract();
        BeanUtils.copyProperties(this, extract);
        return extract;
    }

}
