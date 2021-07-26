package br.com.alelo.consumer.consumerpat.entity;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 25/07/2021 | 10:40
 */

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@EqualsAndHashCode
@ToString
public class AuthToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String token;
    private LocalDateTime createdAt;
}
