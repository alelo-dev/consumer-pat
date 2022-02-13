package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class RequestMapper {

    public static Consumer toConsumer(ConsumerRequest request){
        return Consumer
                .builder()
                .id(request.getId())
                .name(request.getName())
                .documentNumber(request.getDocumentNumber())
                .birthDate(request.getBirthDate())
                .mobilePhoneNumber(request.getMobilePhoneNumber())
                .residencePhoneNumber(request.getResidencePhoneNumber())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .street(request.getStreet())
                .number(request.getNumber())
                .city(request.getCity())
                .country(request.getCountry())
                .postalCode(request.getPostalCode())
                .build();
    }

    /**
     * @param consumer
     * @param request
     *
     * @return Consumer com os dados atualizados
     */
    //     * Solução de melhoria utilizar o Map2Struct ou melhorar o Merge através do Reflection
    public static Consumer mergeConsumer(Consumer consumer, ConsumerUpdateRequest request){
        BeanUtils.copyProperties(request, consumer, getNullPropertyNames(request));

        return consumer;
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }
}
