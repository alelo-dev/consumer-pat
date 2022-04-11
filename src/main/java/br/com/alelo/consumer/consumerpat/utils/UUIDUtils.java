package br.com.alelo.consumer.consumerpat.utils;

import java.math.BigInteger;
import java.util.UUID;

import lombok.Data;

@Data
public class UUIDUtils {

	public static UUID makeUuid(String uuidString) {
		
		if(uuidString.contains("-")) {
			return UUID.fromString(uuidString);
		}

		UUID uuid = new UUID(
				new BigInteger(uuidString.substring(0, 16), 16).longValue(),
				new BigInteger(uuidString.substring(16), 16).longValue());
		return uuid;
	}

}
