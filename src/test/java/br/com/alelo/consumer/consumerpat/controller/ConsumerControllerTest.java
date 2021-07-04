package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.controller.request.ConsumerRequest;
import br.com.alelo.consumer.consumerpat.controller.response.ConsumerResponse;
import br.com.alelo.consumer.consumerpat.services.CardService;
import br.com.alelo.consumer.consumerpat.services.ConsumerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.parseMediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
class ConsumerControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    ConsumerService consumerService;
    @MockBean
    CardService cardService;

    @Test
    @DisplayName("Deve listar todos os consumers por paginação")
    public void findAllConsumer() throws Exception {

        final var response = createResponses();
        BDDMockito
                .given(consumerService.getAllConsumersList(Mockito.any(Pageable.class))).willReturn(new PageImpl<>(response, getPageable(), response.size()));

        final MockHttpServletRequestBuilder requestBuilder = requestGetAll();
        mockMvc
                .perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentType(parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"content\":[{\"consumer_id\":1,\"name\":\"Teste 1\",\"document_number\":\"77533311100-1\",\"birth_date\":\"02/08/1981\"},{\"consumer_id\":2,\"name\":\"Teste 2\",\"document_number\":\"77533311100-2\",\"birth_date\":\"03/08/1982\"},{\"consumer_id\":3,\"name\":\"Teste 3\",\"document_number\":\"77533311100-3\",\"birth_date\":\"04/08/1983\"},{\"consumer_id\":4,\"name\":\"Teste 4\",\"document_number\":\"77533311100-4\",\"birth_date\":\"05/08/1984\"},{\"consumer_id\":5,\"name\":\"Teste 5\",\"document_number\":\"77533311100-5\",\"birth_date\":\"06/08/1985\"}],\"pageable\":{\"pageNumber\":0,\"pageSize\":10,\"offset\":0,\"sort\":null,\"paged\":true,\"unpaged\":false},\"last\":true,\"totalPages\":1,\"totalElements\":5,\"first\":true,\"numberOfElements\":5,\"number\":0,\"size\":10,\"sort\":null,\"empty\":false}"))
        ;

    }
    @Test
    @DisplayName("Deve Criar um consumer e retornar Http Status 201 ")
    public void createConsumer() throws Exception {

        final ConsumerRequest andre = ConsumerRequest.builder().name("Andre").build();
        final ConsumerResponse consumerResponse = ConsumerResponse.builder().name("Andre").build();
        BDDMockito
                .given(consumerService.create(Mockito.any(ConsumerRequest.class))).willReturn(consumerResponse);

        final MockHttpServletRequestBuilder requestBuilder = requestCreate(andre);
        mockMvc
                .perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentType(parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"consumer_id\":0,\"name\":\"Andre\"}"))
        ;

    }

    private MockHttpServletRequestBuilder requestCreate(ConsumerRequest andre) throws JsonProcessingException {
        final String json = new ObjectMapper().writeValueAsString(andre);
        return post("/consumers")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(json);
    }


    private MockHttpServletRequestBuilder requestGetAll() {
        return get("/consumers")
                .accept(APPLICATION_JSON)
                ;
    }




    private ConsumerResponse createRequest() {
        return ConsumerResponse.builder().build();
    }
    private List<ConsumerResponse> createResponses() {
        List<ConsumerResponse> list = new ArrayList<>();
        for (int i = 1; i < 6; i++){
            final LocalDate of = LocalDate.of(1980 + i, 8, 1 + i);
            list.add(ConsumerResponse
                    .builder()
                    .id(i)
                    .name("Teste " + i)
                    .documentNumber("77533311100-" + i)
                    .birthDate(of).build());
        }
        return list;
    }

    private Pageable getPageable() {
        return new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 10;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
    }

}