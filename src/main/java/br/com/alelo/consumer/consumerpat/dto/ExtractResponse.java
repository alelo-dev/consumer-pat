package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.Card;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@Builder
public class ExtractResponse {

    private String establishmentName;
    private String productDescription;
    private Date dateBuy;
    private double value;

}
