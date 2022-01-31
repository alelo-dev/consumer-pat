package br.com.alelo.consumer.consumerpat.Service;

import br.com.alelo.consumer.consumerpat.entity.Benefit;
import br.com.alelo.consumer.consumerpat.respository.BenefitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class BenefitService {

    @Autowired
    BenefitRepository benefitRepository;

    public BenefitService(BenefitRepository benefitRepository) {
        this.benefitRepository = benefitRepository;
    }



    public Benefit setBenefit(Benefit benefit, UriComponentsBuilder uriBuilder){ // - ok
        //URI uri = uriBuilder.path("/benefit/{id}").buildAndExpand(benefit.getBenefit_id()).toUri();
        return benefitRepository.save(benefit);
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


    public ResponseEntity<HttpStatus> update(Benefit benefit) {
        Benefit benefit1 = benefitRepository.findById(benefit.getId()).get();

        try {
            if (benefit != null) {
//                benefit1.setBenefitType_id(benefit.getBenefitType_id());
//                benefit1.(benefit.getName());
                  benefitRepository.save(benefit1);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro não esperado");
        }
    }

    public Optional<Benefit> findBenefitById(Long id){
        return benefitRepository.findById(id);
    }

}