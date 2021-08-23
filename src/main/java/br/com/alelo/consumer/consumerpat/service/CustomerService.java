package br.com.alelo.consumer.consumerpat.service;

import java.util.Arrays;
import java.util.HashMap;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDto;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.util.ResponseUtil;

@Service
public class CustomerService {
	@Autowired
	ConsumerRepository repository;

	public ResponseEntity<?> createConsumer(ConsumerDto consumerDto) {
		ResponseEntity<?> output = null;
		try {
			HashMap<String, Object> body = new HashMap<>();

			if (isNull(consumerDto.getFoodCardNumber(), consumerDto.getFuelCardNumber(),
					consumerDto.getDrugstoreNumber())) {
				throw new BusinessException("All card numbers are needed!");
			}

			if (repository.isNewCardNumbers(consumerDto.getFoodCardNumber(), consumerDto.getFuelCardNumber(),
					consumerDto.getDrugstoreNumber())) {

				Consumer consumer = new Consumer();
				BeanUtils.copyProperties(consumerDto, consumer, "id");
				repository.save(consumer);

				body.put("data", consumer);

				output = ResponseEntity.status(HttpStatus.CREATED).body(body);
			} else {
				throw new BusinessException("One of the cards has already been registered!");
			}
		} catch (BusinessException e) {
			output = ResponseUtil.getBadRequestResponse(e);
		} catch (Exception e) {
			e.printStackTrace();
			output = ResponseUtil.getInternalErrorResponse();
		}
		return output;
	}

	private boolean isNull(Object... objects) {
		return Arrays.stream(objects).anyMatch(obj -> obj == null);
	}

}
