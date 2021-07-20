package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

import javax.persistence.EntityManager;
import java.util.Date;

public class ConsumerDTO {

    String name;
    String  documentNumber;
    Date birthDate;

    public ConsumerDTO(Date birthDate, String documentNumber, String name) {
        this.birthDate = birthDate;
        this.documentNumber = documentNumber;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Consumer toModel(EntityManager manager) {
        return new Consumer(birthDate, documentNumber, name);
    }
}
