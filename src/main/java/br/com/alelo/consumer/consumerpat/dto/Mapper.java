package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.ServiceWarningException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Log4j2
@Component
public class Mapper {

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public Consumer toConsumer(ConsumerCreateDto consumer) throws ServiceWarningException {

        log.info("Convert consumerCreateDto to consumer");

        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);

        try {
            return Consumer.builder()
                .name(consumer.getName())
                .documentNumber(consumer.getDocumentNumber())
                .birthDate(LocalDate.parse(consumer.getBirthDate(), format))
                .mobilePhoneNumber(consumer.getMobilePhoneNumber())
                .residencePhoneNumber(consumer.getResidencePhoneNumber())
                .phoneNumber(consumer.getPhoneNumber())
                .email(consumer.getEmail())
                .street(consumer.getStreet())
                .number(consumer.getNumber())
                .city(consumer.getCity())
                .country(consumer.getCountry())
                .postalCode(consumer.getPostalCode())
                .foodCardNumber(consumer.getFoodCardNumber())
                .foodCardBalance(BigDecimal.ZERO)
                .fuelCardNumber(consumer.getFuelCardNumber())
                .fuelCardBalance(BigDecimal.ZERO)
                .drugstoreNumber(consumer.getDrugstoreNumber())
                .drugstoreCardBalance(BigDecimal.ZERO)
            .build();
        } catch (Exception e) {
            log.warn("Date format invalid");
            throw new ServiceWarningException("Date format invalid");
        }
    }

    public Consumer toConsumer(ConsumerUpdateDto consumer) {

        log.info("Convert consumerUpdateDto to consumer");

        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);

        try {
            return Consumer.builder()
                    .name(consumer.getName())
                    .documentNumber(consumer.getDocumentNumber())
                    .birthDate(LocalDate.parse(consumer.getBirthDate(), format))
                    .mobilePhoneNumber(consumer.getMobilePhoneNumber())
                    .residencePhoneNumber(consumer.getResidencePhoneNumber())
                    .phoneNumber(consumer.getPhoneNumber())
                    .email(consumer.getEmail())
                    .street(consumer.getStreet())
                    .number(consumer.getNumber())
                    .city(consumer.getCity())
                    .country(consumer.getCountry())
                    .postalCode(consumer.getPostalCode())
                    .foodCardNumber(consumer.getFoodCardNumber())
                    .fuelCardNumber(consumer.getFuelCardNumber())
                    .drugstoreNumber(consumer.getDrugstoreNumber())
                    .build();
        } catch (Exception e) {
            log.warn("Date format invalid");
            throw new ServiceWarningException("Date format invalid");
        }
    }
}