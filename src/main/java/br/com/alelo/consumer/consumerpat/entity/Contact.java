package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.enums.TypeContact;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Contact extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private TypeContact typeContact;

    @NotNull
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    private Consumer consumer;

}
