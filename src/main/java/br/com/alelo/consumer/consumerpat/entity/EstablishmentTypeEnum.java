package br.com.alelo.consumer.consumerpat.entity;

public enum EstablishmentTypeEnum {

/*
         * 1 - Alimentação (food)
         * 2 - Farmácia (DrugStore)
         * 3 - Posto de combustivel (Fuel)

 */

    FOOD{
        @Override
        public int value(){
            return 1;
        }
    }, DRUGSTORE{
        @Override
        public int value(){
            return 2;
        }
    }
    , FUEL{
        @Override
        public int value(){
            return 3;
        }
    };

    public int value(){
        return 0;
    }

}
