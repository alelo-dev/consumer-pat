package br.com.alelo.consumer.consumerpat.adapter.in;

import java.util.ArrayList;
import java.util.List;
import br.com.alelo.consumer.consumerpat.adapter.in.web.mapper.ConsumerDTOMapper;
import br.com.alelo.consumer.consumerpat.adapter.in.web.mapper.ConsumerIdentificationDTOMapper;
import br.com.alelo.consumer.consumerpat.adapter.in.web.mapper.ConsumerUpdateDTOMapper;
import br.com.alelo.consumer.consumerpat.adapter.in.web.model.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.adapter.in.web.model.ConsumerIdentificationDTO;
import br.com.alelo.consumer.consumerpat.adapter.in.web.model.ConsumerUpdateDTO;
import br.com.alelo.consumer.consumerpat.application.port.in.CreateConsumer;
import br.com.alelo.consumer.consumerpat.application.port.in.FindConsumer;
import br.com.alelo.consumer.consumerpat.application.port.in.UpdateConsumer;
import br.com.alelo.consumer.consumerpat.common.Adapter;

@Adapter
public class ConsumerAdapter {

  private final CreateConsumer createConsumer;
  private final UpdateConsumer updateConsumer;
  private final FindConsumer findConsumer;

  public ConsumerAdapter(final CreateConsumer createConsumer, final UpdateConsumer updateConsumer,
      final FindConsumer findConsumer) {
    this.createConsumer = createConsumer;
    this.updateConsumer = updateConsumer;
    this.findConsumer = findConsumer;
  }

  public ConsumerIdentificationDTO create(final ConsumerDTO consumerDTO) {

    var consumer = ConsumerDTOMapper.mapToDomain(consumerDTO);
    var createdConsumer = createConsumer.create(consumer);
    return ConsumerIdentificationDTOMapper.mapToDTO(createdConsumer);

  }

  public ConsumerIdentificationDTO update(final ConsumerUpdateDTO updateConsumerDTO) {

    var consumer = ConsumerUpdateDTOMapper.mapToDomain(updateConsumerDTO);
    var updatedConsumer = updateConsumer.update(consumer);
    return ConsumerIdentificationDTOMapper.mapToDTO(updatedConsumer);

  }

  public List<ConsumerDTO> list() {

    var consumers = findConsumer.all();

    var consumerDTOs = new ArrayList<ConsumerDTO>();

    consumers.forEach(consumer -> {
      var consumerDTO = ConsumerDTOMapper.mapToDTO(consumer);
      consumerDTOs.add(consumerDTO);
    });

    return consumerDTOs;
  }

}
