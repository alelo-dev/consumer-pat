package br.com.alelo.consumer.consumerpat.services.impl;

import br.com.alelo.consumer.consumerpat.entity.*;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.exception.RequestNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.RequestRepository;
import br.com.alelo.consumer.consumerpat.services.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class RequestServiceImpl implements RequestService {

    private static final BigDecimal CASHBACK_DEFAULT_DISCOUNT = BigDecimal.TEN;
    private static final BigDecimal DEFAULT_TAX_FUEL = new BigDecimal(35);

    private final RequestRepository requestRepository;
    private final EstablishmentService establishmentService;
    private final ExtractService extractService;
    private final ConsumerService consumerService;
    private final CardService cardService;
    private final ProductService productService;


    public RequestServiceImpl(RequestRepository requestRepository, EstablishmentService establishmentService,
                              ExtractService extractService, ConsumerService consumerService, CardService cardService, ProductService productService) {
        this.requestRepository = requestRepository;
        this.establishmentService = establishmentService;
        this.extractService = extractService;
        this.consumerService = consumerService;
        this.cardService = cardService;
        this.productService = productService;
    }

    @Override
    public Request findOrFail(Long id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException(id) {
                });
    }

    @Transactional
    public Request save(Request request) {

        Establishment establishment = establishmentService.findOrFail(request.getEstablishment().getId());
        request.setEstablishment(establishment);

        Consumer consumer = consumerService.findOrFail(request.getConsumer().getId());
        request.setConsumer(consumer);

        Card card = cardService.findOrFail(request.getCard().getId());
        request.setCard(card);

        Product product = productService.findOrFail(request.getProduct().getId());
        request.setProduct(product);

        if (establishment.getEstablishmentType().toString().equals(card.getCardType().toString())) {
            if (establishment.getEstablishmentType().equals(EstablishmentTypeEnum.FOOD)) {
                BigDecimal productAmountWithCashBackDiscount = this.generateCashback(product.getAmount());
                product.setAmount(productAmountWithCashBackDiscount);

                cardService.decreaseBalance(card.getNumber(), product.getAmount());

                this.generateNewExtract(establishment, consumer, card, product, product.getAmount());
                requestRepository.save(request);

            } else if (establishment.getEstablishmentType().equals(EstablishmentTypeEnum.DRUGSTORE)) {
                cardService.decreaseBalance(card.getNumber(), product.getAmount());
                this.generateNewExtract(establishment, consumer, card, product, product.getAmount());
                requestRepository.save(request);

            } else {
                BigDecimal productAmountWithFuelTax = this.generateFuelTax(product.getAmount());
                product.setAmount(productAmountWithFuelTax);

                cardService.decreaseBalance(card.getNumber(), product.getAmount());

                this.generateNewExtract(establishment, consumer, card, product, product.getAmount());
                requestRepository.save(request);
            }

            return requestRepository.save(request);

        } else {
            throw new BusinessException(
                    String.format("O Estabelecimento informado é do tipo: %s e o cartão utilizado é do tipo: %s." +
                                    " Não é permitido realizar compras de tipos não correspondentes.",
                            establishment.getEstablishmentType().getDescription(), card.getCardType().getDescription()));
        }

    }

    private BigDecimal generateCashback(BigDecimal amount) {
        BigDecimal cashback = (amount.divide(new BigDecimal(100)).multiply(CASHBACK_DEFAULT_DISCOUNT));
        return amount = amount.subtract(cashback);
    }

    private BigDecimal generateFuelTax(BigDecimal amount) {
        BigDecimal fuelTax = (amount.divide(new BigDecimal(100)).multiply(DEFAULT_TAX_FUEL));
        return amount = amount.add(fuelTax);
    }

    private void generateNewExtract(Establishment establishment,Consumer consumer, Card card, Product product, BigDecimal amount) {
        Extract extract = new Extract();
        extract.setDate(OffsetDateTime.now());
        extract.setEstablishment(establishment);
        extract.setConsumer(consumer);
        extract.setCard(card);
        extract.setProduct(product);
        extract.setAmount(amount);

        extractService.save(extract);
    }


}
