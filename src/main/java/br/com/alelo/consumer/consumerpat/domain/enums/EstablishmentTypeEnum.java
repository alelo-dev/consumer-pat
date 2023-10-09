package br.com.alelo.consumer.consumerpat.domain.enums;

public enum EstablishmentTypeEnum {

    FOOD_ESTABLISHMENT(1),
    DRUGSTORE(2),
    GAS_STATION(3);

        private final int establishmentType;

        public int getEstablishmentType(){
            return establishmentType;
        }

        EstablishmentTypeEnum(int establishmentType){
            this.establishmentType = establishmentType;
        }
}
