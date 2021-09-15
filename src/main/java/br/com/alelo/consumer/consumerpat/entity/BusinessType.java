package br.com.alelo.consumer.consumerpat.entity;

import io.swagger.annotations.ApiModel;

@ApiModel(value="BusinessType", description="Domain types of business")
public enum BusinessType {

    FUEL,
    FOOD,
    DRUGSTORE;

}
