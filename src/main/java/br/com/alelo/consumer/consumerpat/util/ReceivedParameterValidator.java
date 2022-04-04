package br.com.alelo.consumer.consumerpat.util;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import br.com.alelo.consumer.consumerpat.response.StandardResponse;


@Component
public class ReceivedParameterValidator {
	

	private static final Logger log = LoggerFactory.getLogger( ReceivedParameterValidator.class );

	
	/**
	* Realiza a validação de um objeto "BindingResult" recebido, se houver erros de validação registrados, então objeto "StandardResponse<?>" é preparado para que seja possivel retorna-lo como resposta.
	* @param  BindingResult       - Objeto para informar os erros de validação ocorridos.
	* @param  StandardResponse<?> - Resposta padronizada.
	* @return boolean             - 'true' caso exista erros de validação. 'false' caso não existam erros de validação.
	*/
	public static boolean checkValidationResultBindingResult( BindingResult bindingResult,  StandardResponse<?> responsePadronizado  ){
		
		
		log.info("Executing checkValidationResultBindingResult()'");


		if ( bindingResult.hasErrors() ) {
			
			
			log.error( "There are validation errors in the received dto object: ", bindingResult.getAllErrors() );
			
			
			List<ObjectError> listaDeErros = bindingResult.getAllErrors();
			
			
			for( ObjectError auxiliar : listaDeErros  ){
				
				//Pegar a mensagem de erro da posicao atual.
				String extractedErrorMessage = auxiliar.getDefaultMessage();	

				//Preparar mensagem de erro os dados recebidos estejam no tipo incorreto.
				if( auxiliar.getCode().equals("typeMismatch") ){
					
					extractedErrorMessage = "The parameters received are not correctly typed (text, numeric, etc.). Please check the type of parameters.";
				}

				responsePadronizado.getErrors().add( extractedErrorMessage );

			}
					
			//Foram encontrados erros de validação.
			return true;
		}
		
		//Não foram encontrados erros de validação.
		return false;
	}


}