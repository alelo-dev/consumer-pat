package br.com.alelo.consumer.consumerpat.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.adapter.ConsumerAdapter;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.enums.EStablishmentType;
import br.com.alelo.consumer.consumerpat.pagination.view.PagedResponseWrapper;
import br.com.alelo.consumer.consumerpat.pagination.view.Paging;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.specs.ConsumerSpec;
import br.com.alelo.consumer.consumerpat.validator.ValidatorExpection;
import br.com.alelo.consumer.consumerpat.vo.ConsumerVO;
import br.com.alelo.consumer.consumerpat.vo.params.BuyParamsVO;
import br.com.alelo.consumer.consumerpat.vo.params.CardBalancerParamsVO;
import br.com.alelo.consumer.consumerpat.vo.params.ConsumerParamsVO;
import br.com.alelo.consumer.consumerpat.vo.params.DefaultParamVO;

@Service
public class ConsumerService {

	@Autowired
	private ConsumerRepository consumerRepository;

	@Autowired
	private ExtractRepository extractRepository;

	public PagedResponseWrapper<ConsumerVO> findAll(ConsumerParamsVO params) {

		final int offset = Optional.ofNullable(params.getOffset()).orElse(DefaultParamVO.DEFAULT_OFFSET);
		final int limit = Optional.ofNullable(params.getLimit()).orElse(DefaultParamVO.DEFAULT_LIMIT);

		final Pageable pageable = PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, "id"));
		final Specification<Consumer> spec = ConsumerSpec.byFilter(params, "id");

		final Page<Consumer> listEntity = this.consumerRepository.findAll(spec, pageable);

		final List<ConsumerVO> listVo = ConsumerAdapter.modelToVo(listEntity.getContent());

		final Paging paging = new Paging(offset, limit, listEntity.getTotalElements(), listEntity.getTotalPages());
		final PagedResponseWrapper<ConsumerVO> wrapper = new PagedResponseWrapper<>(listVo, paging);
		return wrapper;
	}

	public ConsumerVO save(ConsumerVO vo) {

		Consumer entity = ConsumerAdapter.voToModel(vo);
		this.consumerRepository.saveAndFlush(entity);
		return ConsumerAdapter.modelToVo(entity);
	}

	public ConsumerVO update(ConsumerVO vo) {

		Consumer entity = this.consumerRepository.findById(vo.getId()).orElse(null);

		if (entity == null) {
			throw ValidatorExpection.newException("O consumidor não existe!");
		}

		entity = ConsumerAdapter.voToModel(vo, entity);

		this.consumerRepository.saveAndFlush(entity);

		return ConsumerAdapter.modelToVo(entity);
	}

	public void cardBalance(CardBalancerParamsVO params) {

		Consumer consumer = null;
		consumer = this.consumerRepository.findByDrugstoreNumber(params.getCardNumber());

		if (consumer != null) {
			// é cartão de farmácia
			consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + params.getValue());
			this.consumerRepository.save(consumer);
		} else {
			consumer = this.consumerRepository.findByFoodCardNumber(params.getCardNumber());
			if (consumer != null) {
				// é cartão de refeição
				consumer.setFoodCardBalance(consumer.getFoodCardBalance() + params.getCardNumber());
				this.consumerRepository.save(consumer);
			} else {
				// É cartão de combustivel
				consumer = this.consumerRepository.findByFuelCardNumber(params.getCardNumber());

				if(consumer == null) {
					throw ValidatorExpection.newException("O cartão não foi encontrado!");
				}
				consumer.setFuelCardBalance(consumer.getFuelCardBalance() + params.getValue());
				this.consumerRepository.save(consumer);
			}
		}
	}

	public void buy(BuyParamsVO params) {

		Consumer consumer = null;
		/*
		 * O valores só podem ser debitados dos cartões com os tipos correspondentes ao
		 * tipo do estabelecimento da compra. Exemplo: Se a compra é em um
		 * estabelecimeto de Alimentação(food) então o valor só pode ser debitado do
		 * cartão e alimentação
		 *
		 * Tipos de estabelcimentos 1 - Alimentação (food) 2 - Farmácia (DrugStore) 3 -
		 * Posto de combustivel (Fuel)
		 */

		if (params.getEstablishmentType() == EStablishmentType.FOOD.getId()) {
			// Para compras no cartão de alimentação o cliente recebe um desconto de 10%
			Double cashback = (params.getValue() / 100) * 10;
			params.setValue(params.getValue() - cashback);

			consumer = this.consumerRepository.findByFoodCardNumber(params.getCardNumber());
			
			if(consumer == null) {
				throw ValidatorExpection.newException("O cartão não foi encontrado!");
			}
			
			consumer.setFoodCardBalance(consumer.getFoodCardBalance() - params.getValue());
			this.consumerRepository.save(consumer);

		} else if (params.getEstablishmentType() == EStablishmentType.DRUGSTORE.getId()) {
			consumer = this.consumerRepository.findByDrugstoreNumber(params.getCardNumber());
			
			if(consumer == null) {
				throw ValidatorExpection.newException("O cartão não foi encontrado!");
			}
			
			consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - params.getValue());
			this.consumerRepository.save(consumer);

		} else {
			// Nas compras com o cartão de combustivel existe um acrescimo de 35%;
			Double tax = (params.getValue() / 100) * 35;
			params.setValue(params.getValue() + tax);

			consumer = this.consumerRepository.findByFuelCardNumber(params.getCardNumber());
			
			if(consumer == null) {
				throw ValidatorExpection.newException("O cartão não foi encontrado!");
			}
			
			consumer.setFuelCardBalance(consumer.getFuelCardBalance() - params.getValue());
			this.consumerRepository.save(consumer);
		}

		Extract extract = new Extract();

		extract.setEstablishmentName(params.getEstablishmentName());
		extract.setProductDescription(params.getProductDescription());
		extract.setDateBuy(LocalDate.now());
		extract.setCardNumber(params.getCardNumber());
		extract.setValue(params.getValue());

		this.extractRepository.saveAndFlush(extract);

	}

}
