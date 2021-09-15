package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.request.PurchaseRequest;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.entity.Transaction;
import br.com.alelo.consumer.consumerpat.repository.TransactionRepository;
import br.com.alelo.consumer.consumerpat.utils.TransactionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final CardService cardService;
    private final TransactionUtils transactionUtils;
    private final TransactionRepository transactionRepository;
    private final EstablishmentService establishmentService;

    public Transaction save(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    public Transaction buy(PurchaseRequest purchaseRequest) {
        Transaction purchase = null;
        try {
            Establishment establishment = establishmentService.findByTypeAndName(purchaseRequest.getEstablishment().getType(),
                    purchaseRequest.getEstablishment().getName());

            Card card = cardService.findByTypeAndNumber(purchaseRequest.getCard().getType(), purchaseRequest.getCard().getNumber());

            //Os valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
            if(!establishment.getType().equals(card.getType())) {
                throw new Exception("The establishment don't accepts the card type: " + card.getType());
            }

            purchase = transactionUtils.buildPurchase(establishment, card, purchaseRequest.getProduct());

            cardService.debit(card, purchaseRequest.getProduct().getValue());

            purchase = transactionRepository.save(purchase);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return purchase;
    }

}
