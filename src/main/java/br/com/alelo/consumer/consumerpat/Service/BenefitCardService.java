package br.com.alelo.consumer.consumerpat.Service;


import br.com.alelo.consumer.consumerpat.entity.BenefitCard;
import br.com.alelo.consumer.consumerpat.respository.BenefitCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class BenefitCardService {

    @Autowired
    BenefitCardRepository benefitCardRepository;

    public BenefitCardService(BenefitCardRepository benefitCardRepository) {
        this.benefitCardRepository = benefitCardRepository;
    }



    public BenefitCard setBenefit(BenefitCard benefitCard, UriComponentsBuilder uriBuilder){ // - ok
        //URI uri = uriBuilder.path("/benefit/{id}").buildAndExpand(benefit.getBenefit_id()).toUri();
        return benefitCardRepository.save(benefitCard);
    }

    public ResponseEntity<HttpStatus> delBenefit(Long id){// ok

        try {
            if(this.findBenefitById(id) != null) {
                //benefitRepository.deleteById(Math.toIntExact(id));
                return new ResponseEntity<>(HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<HttpStatus> update(BenefitCard benefitCard) {
        BenefitCard benefitCard1 = benefitCardRepository.findById(benefitCard.getId()).get();

        try {
            if (benefitCard != null) {
//                benefit1.setBenefitType_id(benefit.getBenefitType_id());
//                benefit1.(benefit.getName());
                  benefitCardRepository.save(benefitCard1);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro não esperado");
        }
    }

    public Optional<BenefitCard> findBenefitById(Long id){
        return benefitCardRepository.findById(id);
    }

    /* Credita valor */
    public boolean setBalance(int cardNumber, double value) {
        List<BenefitCard> benefitCard = null;
//        benefitCard = benefitCardRepository.findByCardNumber(cardNumber).get();   //consumerRepository.findBycardNumber(cardNumber).get();

//        benefit = benefitRepository.findByCardNumber(cardNumber);
        for (BenefitCard b : benefitCard) {
            if (b.getBenefitType_id() != null) {
                // é cartão de farmácia
                System.out.printf("Cartão " + b.getBenefitType_id() + " creditado");
                b.setCardBalance((b.getCardBalance() + value));
                benefitCardRepository.save(b);
                return true;
            }

        }
        return false;
    }
}