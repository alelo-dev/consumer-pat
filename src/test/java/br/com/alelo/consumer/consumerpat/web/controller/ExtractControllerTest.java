package br.com.alelo.consumer.consumerpat.web.controller;

import br.com.alelo.consumer.consumerpat.model.entity.Extract;
import br.com.alelo.consumer.consumerpat.service.ExtractService;
import br.com.alelo.consumer.consumerpat.utils.Constants;
import br.com.alelo.consumer.consumerpat.web.vo.extract.ExtractFilterVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static br.com.alelo.consumer.consumerpat.fixture.ConsumerPatFixture.buildExtract;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ExtractControllerTest {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(Constants.DATETIME_FORMAT_PATTERN);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExtractService extractService;

    @Test
    void findAll_WithEmptyResult_ShouldReturnEmptyResultInPaginatedList() throws Exception {
        given(extractService.findAll(any(ExtractFilterVO.class), any(Pageable.class)))
                .willReturn(new PageImpl<>(List.of()));

        mockMvc.perform(get("/v1/extract?page=0&size=10&sortDirection=ASC&sortField=ID")
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.records").exists())
            .andExpect(jsonPath("$.records").isArray())
            .andExpect(jsonPath("$.records", hasSize(0)))
            .andExpect(jsonPath("$.page").exists())
            .andExpect(jsonPath("$.page.hasNext", is(false)))
            .andExpect(jsonPath("$.page.hasPrev", is(false)))
            .andExpect(jsonPath("$.page.page", is(0)))
            .andExpect(jsonPath("$.page.totalPages", is(1)))
            .andExpect(jsonPath("$.page.size", is(0)))
            .andExpect(jsonPath("$.page.totalElements", is(0)));
    }

    @Test
    void findAll_WithNonEmptyResult_ShouldReturnExtractInPaginatedList() throws Exception {
        Extract extract = buildExtract(null, null);

        given(extractService.findAll(any(ExtractFilterVO.class), any(Pageable.class)))
                .willReturn(new PageImpl<>(List.of(extract)));

        mockMvc.perform(get("/v1/extract?page=0&size=10&sortDirection=ASC&sortField=ID")
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.records").exists())
            .andExpect(jsonPath("$.records").isArray())
            .andExpect(jsonPath("$.records", hasSize(1)))
            .andExpect(jsonPath("$.records[0].id", is(extract.getId().intValue())))
            .andExpect(jsonPath("$.records[0].establishmentId", is(extract.getEstablishmentId().intValue())))
            .andExpect(jsonPath("$.records[0].establishmentName", is(extract.getEstablishmentName())))
            .andExpect(jsonPath("$.records[0].productDescription", is(extract.getProductDescription())))
            .andExpect(jsonPath("$.records[0].dateBuy", is(extract.getDateBuy().format(FORMATTER))))
            .andExpect(jsonPath("$.records[0].value", is(extract.getValue().doubleValue())))
            .andExpect(jsonPath("$.page").exists())
            .andExpect(jsonPath("$.page.hasNext", is(false)))
            .andExpect(jsonPath("$.page.hasPrev", is(false)))
            .andExpect(jsonPath("$.page.page", is(0)))
            .andExpect(jsonPath("$.page.totalPages", is(1)))
            .andExpect(jsonPath("$.page.size", is(1)))
            .andExpect(jsonPath("$.page.totalElements", is(1)));
    }

}