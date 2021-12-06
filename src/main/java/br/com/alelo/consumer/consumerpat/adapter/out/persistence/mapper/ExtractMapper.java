package br.com.alelo.consumer.consumerpat.adapter.out.persistence.mapper;

import br.com.alelo.consumer.consumerpat.adapter.out.persistence.entity.ExtractEntity;
import br.com.alelo.consumer.consumerpat.domain.Extract;

public class ExtractMapper {
  
  public static ExtractEntity mapToJpaEntity(final Extract extract) {
    
    var extractEntity = new ExtractEntity();
    extractEntity.setEstablishmentName(extract.getEstablishmentName());
    extractEntity.setProductDescription(extract.getProductDescription());
    extractEntity.setDateBuy(extract.getDateBuy());
    extractEntity.setCardNumber(extract.getCardNumber());
    extractEntity.setValue(extract.getValue());
    
    return extractEntity;
  
  }
 
}
