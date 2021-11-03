package br.com.alelo.consumer.consumerpat.util;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.LongStream;

import br.com.alelo.consumer.consumerpat.model.Address;
import br.com.alelo.consumer.consumerpat.model.Card;
import br.com.alelo.consumer.consumerpat.model.Consumer;
import br.com.alelo.consumer.consumerpat.model.Establishment;
import br.com.alelo.consumer.consumerpat.model.BusinessType;

/**
 * Existe apenas para criar uma massa de dados para testar a aplicação.
 *
 */
public class DataGenerator {

	/**
	 * Gerando uma lista de consumidores
	 * @param size tamanho qualquer
	 * @return lista de consumidores
	 */
	
	public static List<Consumer> generateConsumerList(int size) {
		final List<Consumer> consumers = new ArrayList<>();
		LongStream.range(0, size).forEach(i -> consumers.add(generateConsumer()));
		return consumers;
	}

	/**
	 * Gerando um consumidor ficticio
	 * @return
	 */
	public static Consumer generateConsumer() {
		final Consumer consumer = new Consumer(generateDummyCpf(), generateConsumerName());
		consumer.setAddress(generateAddress());
		consumer.setCards(generateCardList(consumer));
		consumer.setBirthDate(generateBirthDate());
		consumer.setEmail("devtest@alelo.com.br");
		consumer.setMobilePhone("+55 11 99977-7799");
		consumer.setPhone("+55 11 99777-7797");
		consumer.setResidencePhone("+55 11 99937-7793");
		return consumer;
	}

	/**
	 * Gerando uma lista de estabelecimentos
	 * @param size tamanho qualquer
	 * @return lista de estabelecimentos
	 */
	
	public static List<Establishment> generateEstablishmentList(int size) {
		final Set<Establishment> establishments = new HashSet<>();
		LongStream.range(0, size).forEach(i -> establishments.add(generateEstablishment()));
		return new ArrayList<>(establishments);
	}
	
	/**
	 * Gerando um estabelecimento qualquer
	 * @return
	 */
	public static Establishment generateEstablishment() {
		final Establishment establishment = new Establishment(generateEstablishmentName(), generateAddress(), BusinessType.getBusinessTypeById(new SecureRandom().nextInt(3) + 1).get()); 
		return establishment;
	}
	
	/**
	 * Gerando um estabelecimento com tipo definido
	 * @return
	 */
	public static Establishment generateEstablishment(BusinessType businessType) {
		final Establishment establishment = new Establishment(generateEstablishmentName(), generateAddress(), businessType); 
		return establishment;
	}
	
	/**
	 * Gerando um endereco qualquer
	 * @return
	 */
	public static Address generateAddress() {
		final SecureRandom random = new SecureRandom();
		final List<String> randomStreet = Arrays.asList("Rua das Flores", "Avenida Brasil", "Avenida Atlântica", "Rua Rui Barbosa", "Avenida Duque de Caxias");
		final List<Integer> randomNumbers = Arrays.asList(10, 500, 123, 127, 564, 1700, 2344, 2320, 553, 674, 2345, 123, 904, 423, 743);
		final List<Integer> randomCeps = Arrays.asList(87000123, 81000123, 10012345, 17564215, 88123456, 95123400, 65485612, 65000123, 45123000);
		final List<String> randomCidades = Arrays.asList("São Paulo", "Florianópolis", "Bauru", "Campos do Jordão", "Canela", "Urubici", "Aracaju", "Recife");
		final List<String> randomUfs = Arrays.asList("AC", "AP", "AM", "PR", "SP", "SC", "TO", "RS", "MG", "ES");
		
		final Address address = new Address(randomCeps.get(random.nextInt(randomCeps.size())),
				randomStreet.get(random.nextInt(randomStreet.size())),
				randomNumbers.get(random.nextInt(randomNumbers.size())), 
				randomCidades.get(random.nextInt(randomCidades.size())), 
				randomUfs.get(random.nextInt(randomUfs.size())), 
				"Brasil");		
		return address;
	}

	/**
	 * Gerando um cartao ficticio
	 * @param consumer
	 * @param type
	 * @return
	 */
	public static Card generateCard(Consumer consumer, BusinessType type) {
		final SecureRandom random = new SecureRandom();
		final BigDecimal balance = Arrays.asList(
				new BigDecimal("500.00"),
				new BigDecimal("600.00"),
				new BigDecimal("700.00"),
				new BigDecimal("800.00"),
				new BigDecimal("900.00"),
				new BigDecimal("1000.00")).get(random.nextInt(6));
		
		final Card card = new Card(generateDummyCardNumber(), type, balance, consumer, generateExpirationDate());

		return card;
	}

	/**
	 * Gerando numeros de cartoes ficticios (nao validados)
	 * @return numero do cartao como string
	 */
	public static String generateDummyCardNumber() {
		final StringBuilder cardNumber = new StringBuilder();
		for (int i = 1; i <= 16; i++) {
			cardNumber.append(new SecureRandom().nextInt(10));
		}
		
		return cardNumber.toString();
	}

	/**
	 * Gerando uma String contendo 11 numeros (nao sao cpf válidos)
	 * @return String com 11 numeros
	 */
	public static String generateDummyCpf() {
		final StringBuilder cpf = new StringBuilder();
		for (int i = 1; i <= 11; i++) {
			cpf.append(new SecureRandom().nextInt(10));
		}
		
		return cpf.toString();
	}

	/**
	 * Gerando de 1 a 3 cartões ficticios
	 * @param consumer
	 * @return lista de cartoes
	 */
	public static List<Card> generateCardList(Consumer consumer) {
		final SecureRandom random = new SecureRandom();
		final Set<Card> cards = new HashSet<>();
		final int total = random.nextInt(3) + 1;
		for(int i=0; i < total; i++) {
			cards.add(generateCard(consumer, BusinessType.getBusinessTypeById(i+1).get()));
		}
		
		return new ArrayList<>(cards);
	}

	/**
	 * Gerando data de nascimento ficticia
	 * @return data de nascimento
	 */
	public static LocalDate generateBirthDate() {
		final SecureRandom random = new SecureRandom();
		final int minDummyAge = 16;
		final int randomYear = LocalDate.now().getYear() - (minDummyAge + random.nextInt(80));
		final int randomMonth = random.nextInt(12) + 1;
		final int randomDay = random.nextInt(25) + 1;

		final LocalDate birthDate = LocalDate.of(randomYear, randomMonth, randomDay);
		return birthDate;
	}

	/**
	 * Gerando data de expiração ficticia
	 * @return data de expiracao
	 */
	public static LocalDate generateExpirationDate() {
		final SecureRandom random = new SecureRandom();
		final int randomYear = 2022 + random.nextInt(11);
		final int randomMonth = random.nextInt(12) + 1;
		final LocalDate expirationDate = LocalDate.of(randomYear, randomMonth, 1);
		return expirationDate;
	}

	/**
	 * Obtendo nome ficticio
	 * @return nome
	 */
	public static String generateConsumerName() {
		final SecureRandom random = new SecureRandom();
		final List<String> randomNames = Arrays.asList("Andrew Adams,Emilia Baker,Jessica Clark,Maria Davis,Richard Evans,Johnnie Frank,William Ghosh,Michael Hills,Angela Irwin,Julie Jones,Arnold Klein,Amy Lopez,Peter Mason,Lincoln Nalty,David Ochoa,Steve Patel,Walt Quinn,Richard Reily,Quincy Smith,Lourdes Trott,Helen Usman,Claire Valdo,James White,Cory Xiang,John Yakub,Ann Zafar".split(","));
		return randomNames.get(random.nextInt(randomNames.size()));
	}
	
	/**
	 * Obtendo nome ficticio
	 * @return nome
	 */
	public static String generateEstablishmentName() {
		final SecureRandom random = new SecureRandom();
		final List<String> randomNames = Arrays.asList("Mercado Dez,Panificadora Legal,Supermercado Família,Loja de Conveniência,Posto Amigo,Hipermercado Grande,Restaurante das Celebridades,Loja de Bebidas".split(","));
		return randomNames.get(random.nextInt(randomNames.size()));
	}
	
}