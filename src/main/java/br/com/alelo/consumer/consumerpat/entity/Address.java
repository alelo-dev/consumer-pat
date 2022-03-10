package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Address {
  String streetName;
  String streetNumber;
  String postalCode;
  String cityName;
  String country;
}
