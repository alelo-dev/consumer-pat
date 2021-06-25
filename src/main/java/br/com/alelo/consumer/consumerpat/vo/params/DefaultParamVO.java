package br.com.alelo.consumer.consumerpat.vo.params;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonDeserialize(builder = DefaultParamVO.CliDGeneParamVOBuilder.class)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")

public class DefaultParamVO {

	public static final int DEFAULT_OFFSET = 0;
	public static final int DEFAULT_LIMIT = 20;
	
	@Min(0)
	private Integer offset = DEFAULT_OFFSET;

	@Min(0)
	@Max(50)
	private Integer limit = DEFAULT_LIMIT;

	@JsonPOJOBuilder(withPrefix = "")
	public static final class CliDGeneParamVOBuilder {
		private final Integer limit = DEFAULT_LIMIT;
		private final Integer offset = DEFAULT_OFFSET;
	}
}
