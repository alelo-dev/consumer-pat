package br.com.alelo.consumer.consumerpat.view.controller;

import java.util.Optional;

import org.springframework.validation.BindingResult;

import br.com.alelo.consumer.consumerpat.view.exception.ValidationException;

public class BaseController {

	/**
	 * Faz a checagem se o objeto foi ou não validado com sucesso.
	 *
	 * @param bindingResult - Resultado das validacoes do Objeto.
	 * @throws ValidationException - Excecao disparada em virtude da reprova na
	 *                             validacao.
	 */
	protected void validate(BindingResult bindingResult) {
		final Optional<BindingResult> optionalBinding = Optional.ofNullable(bindingResult);
		if (optionalBinding.isPresent() && optionalBinding.get().hasErrors()) {
			throw new ValidationException(bindingResult.getFieldErrors());
		}
	}

}
