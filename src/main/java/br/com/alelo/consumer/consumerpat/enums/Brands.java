package br.com.alelo.consumer.consumerpat.enums;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.exception.ObjectNotFoundException;

import java.util.Objects;

public enum Brands {

    FOOD(1) {
        @Override
        public Consumer setBalance(ConsumerRepository repository, Long cardNumber, Long value) {
            Consumer consumer = repository.findByFoodCardNumber(cardNumber);

            if (Objects.nonNull(consumer)) {
                // é cartão de refeição
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
                repository.save(consumer);
            } else {
                throw new ObjectNotFoundException("Codigo nao encontrado!");
            }

            return consumer;
        }

        public Consumer setBuy(ConsumerRepository repository, Long cardNumber, Long value) {
            Consumer consumer = repository.findByFoodCardNumber(cardNumber);

            if (Objects.nonNull(consumer)) {
                Long cashback  = (value / 100) * 10;
                value = value - cashback;

                consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
                repository.save(consumer);
            } else {
                throw new ObjectNotFoundException("Erro...");
            }

            return consumer;
        }
    },

    DROGSTORE(2) {
        @Override
        public Consumer setBalance(ConsumerRepository repository, Long cardNumber, Long value) {
            Consumer consumer = repository.findByDrugstoreNumber(cardNumber);

            if (Objects.nonNull(consumer)) {
                // é cartão de refeição
                consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
                repository.save(consumer);
            } else {
                throw new ObjectNotFoundException("Codigo nao encontrado!");
            }

            return consumer;
        }

        public Consumer setBuy(ConsumerRepository repository, Long cardNumber, Long value) {
            Consumer consumer = repository.findByDrugstoreNumber(cardNumber);

            if (Objects.nonNull(consumer)) {
                Long cashback  = (value / 100) * 10;
                value = value - cashback;

                consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
                repository.save(consumer);
            }  else {
                throw new ObjectNotFoundException("Erro...");
            }

            return consumer;
        }
    },

    FUEL(3) {
        @Override
        public Consumer setBalance(ConsumerRepository repository, Long cardNumber, Long value) {
            Consumer consumer = repository.findByFuelCardNumber(cardNumber);

            if (Objects.nonNull(consumer)) {
                // é cartão de refeição
                consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
                repository.save(consumer);
            } else {
                throw new ObjectNotFoundException("Codigo nao encontrado!");
            }
            return consumer;
        }

        public Consumer setBuy(ConsumerRepository repository, Long cardNumber, Long value) {
            Consumer consumer = repository.findByFuelCardNumber(cardNumber);

            if (Objects.nonNull(consumer)) {
                Long tax  = (value / 100) * 35;
                value = value + tax;;

                consumer.setFuelCardBalance(consumer.getFuelCardNumber() - value);
                repository.save(consumer);
            } else {
                throw new ObjectNotFoundException("Erro...");
            }

            return consumer;
        }
    };

    private final Integer id;

    Brands (Integer id) {
        this.id = id;
    }

    public Integer getId () {
        return id;
    }

    public static Brands getEnumById(Integer id) {
        for (Brands brands : Brands.values()) {
            if (brands.getId().equals(id))  return brands;

        }
        return null;
    }

    public abstract Consumer setBalance(ConsumerRepository repository, Long cardNumber, Long value);
    public abstract Consumer setBuy(ConsumerRepository repository, Long cardNumber, Long value);

}
