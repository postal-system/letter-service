package io.codero.letterservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.codero.letterservice.dto.LetterDto;
import io.codero.letterservice.dto.RawLetterDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
class LetterControllerTest extends AbstractControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String URL = "/letters";

    private final UUID id = UUID.randomUUID();

    private LetterDto getLetterDto() {
        LetterDto letterDto = new LetterDto();
        letterDto.setId(id);
        letterDto.setSender("Government Department of Transportation");
        letterDto.setReceiver("mr. Tea");
        letterDto.setContent("Dr. mr. Tea, Return our bas, please");
        letterDto.setRawLetterDto(new RawLetterDto());
        letterDto.setPostOfficeId(666_666);
        return letterDto;
    }

    private String toJson(LetterDto dto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(dto);
    }

    @Test
    void shouldReturnUUIDifGoodDataAndStatusCode200() throws Exception {
        String requestJson = objectMapper.writeValueAsString(getLetterDto());
        String ExpectedJson = objectMapper.writeValueAsString(id);

        mvc.perform(post(URL)
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON
                        )
                )
                .andExpect(status().isOk())
                .andExpect(content().json(ExpectedJson));
    }

    @Test
    void shouldTrowExceptionIfObjectAlreadyExist() throws Exception {
        String requestJson = objectMapper.writeValueAsString(getLetterDto());
        String ExpectedJson = objectMapper.writeValueAsString(id);

        mvc.perform(post(URL)
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(ExpectedJson));


        mvc.perform(post(URL)
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is(409));
    }

    @Test
    void shouldReturnDtoEqualsDtoExpected() throws Exception {
        String jsonDto = objectMapper.writeValueAsString(getLetterDto());

        mvc.perform(post(URL)
                        .content(jsonDto)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        mvc.perform(get(URL + "/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonDto));
    }

    @Test
    void shouldReturn404ifNotExists() throws Exception {
        mvc.perform(get(URL + "/" + id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldObjectUpdatedIfAllRight() throws Exception {
        String jsonDto = objectMapper.writeValueAsString(getLetterDto());
        mvc.perform(post(URL)
                        .content(jsonDto)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        LetterDto newDto = getLetterDto();
        newDto.setContent("new content");
        String newJsonDto = objectMapper.writeValueAsString(newDto);

        mvc.perform(put(URL)
                        .content(newJsonDto)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(get(URL + "/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(newJsonDto));
    }

    @Test
    void shouldNotUpdateAndReturn404IfIdIsFake() throws Exception {
        LetterDto newDto = getLetterDto();
        newDto.setContent("new content");
        String newJsonDto = objectMapper.writeValueAsString(newDto);

        mvc.perform(put(URL)
                        .content(newJsonDto)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldBeDeletedIfObjectIsExistAndReturn404IfWasDeleted() throws Exception {
        String requestJson = objectMapper.writeValueAsString(getLetterDto());
        String ExpectedJson = objectMapper.writeValueAsString(id);

        mvc.perform(post(URL)
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(ExpectedJson));

        mvc.perform(delete(URL + "/" + id))
                .andExpect(status().isOk());

        mvc.perform(get(URL + "/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteAllObjects() throws Exception {
        String requestJson = objectMapper.writeValueAsString(getLetterDto());
        String ExpectedJson = objectMapper.writeValueAsString(id);

        mvc.perform(post(URL)
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(ExpectedJson));

        mvc.perform(delete(URL))
                .andExpect(status().isOk());

        String emptyListJson = objectMapper.writeValueAsString(List.<LetterDto>of());

        mvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(content().json(emptyListJson));
    }

    @Test
    void shouldReturnListLetterDtoIfWasAddedSomeThing() throws Exception {
        String requestJson = objectMapper.writeValueAsString(getLetterDto());
        String ExpectedJson = objectMapper.writeValueAsString(id);

        mvc.perform(post(URL)
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(ExpectedJson));

        String listResponseJson = objectMapper.writeValueAsString(List.of(getLetterDto()));

        mvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(content().json(listResponseJson));
    }
}
