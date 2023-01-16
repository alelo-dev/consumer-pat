package br.com.alelo.consumer.consumerpat.entity;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;


    @Data
    @Entity
    public class Consumer {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        Integer id;
        String name;
        int documentNumber;
        Date birthDate;




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumer consumer = (Consumer) o;
        return documentNumber == consumer.documentNumber

                && Objects.equals(id, consumer.id) && Objects.equals(name, consumer.name) && Objects.equals(birthDate, consumer.birthDate);

    }


}
