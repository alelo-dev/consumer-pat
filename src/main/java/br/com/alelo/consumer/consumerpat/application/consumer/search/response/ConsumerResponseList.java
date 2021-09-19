package br.com.alelo.consumer.consumerpat.application.consumer.search.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerResponseList {

	private List<ConsumerResponse> consumerList;
}
