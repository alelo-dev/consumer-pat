package br.com.alelo.consumer.consumerpat.adapter.in.web.mapper;

import br.com.alelo.consumer.consumerpat.adapter.in.web.model.ConsumerIdentificationDTO;
import br.com.alelo.consumer.consumerpat.domain.Consumer;

public class ConsumerIdentificationDTOMapper {
  
  public static ConsumerIdentificationDTO mapToDTO(Consumer consumerDTO) {

   return ConsumerIdentificationDTO.builder().id(consumerDTO.getId()).build();

  }
}
