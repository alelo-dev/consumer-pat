package br.com.alelo.consumer.consumerpat.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Country extends BaseEntity{

    @NotNull
    private String name;

}
