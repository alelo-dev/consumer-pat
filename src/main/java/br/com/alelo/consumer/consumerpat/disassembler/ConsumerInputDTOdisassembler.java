package br.com.alelo.consumer.consumerpat.disassembler;

import br.com.alelo.consumer.consumerpat.dto.input.ConsumerInputDTO;
import br.com.alelo.consumer.consumerpat.entity.*;
import org.springframework.stereotype.Component;

@Component
public class ConsumerInputDTOdisassembler {

    public Consumer toDomainObject (ConsumerInputDTO consumerInputDTO){
            Consumer consumer = new Consumer();
            consumer.setName(consumerInputDTO.getName());
            consumer.setBirthDate(consumerInputDTO.getBirthDate());
            consumer.setDocumentNumber(consumerInputDTO.getDocumentNumber());
            consumer.setContacts(consumerInputDTO.getContacts());

            Address address = new Address();
            address.setStreet(consumerInputDTO.getAddress().getStreet());
            address.setNumber(consumerInputDTO.getAddress().getNumber());
            address.setPostalCode(consumerInputDTO.getAddress().getPostalCode());

            City city = new City();
            city.setId(consumerInputDTO.getAddress().getCityId());
            address.setCity(city);

            State state = new State();
            state.setId(consumerInputDTO.getAddress().getStateId());
            address.getCity().setState(state);

            Country country = new Country();
            country.setId(consumerInputDTO.getAddress().getCountryId());
            address.getCity().getState().setCountry(country);

            consumer.setAddress(address);

            return consumer;
    }
}
