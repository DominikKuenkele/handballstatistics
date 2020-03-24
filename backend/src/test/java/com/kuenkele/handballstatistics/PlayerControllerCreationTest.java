package com.kuenkele.handballstatistics;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
class PlayerControllerCreationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    PlayerRepository playerRepository;


    @ParameterizedTest
    @CsvFileSource(resources = "/validNames.csv")
    void createCorrectPlayer(String validName) throws Exception {
        Player player = new Player();
        player.setFirstName(validName);
        player.setLastName(validName);

        when(playerRepository.save(any(Player.class))).thenAnswer(i -> i.getArguments()[0]);

        mvc.perform(post("/player")
                .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(player)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("firstName", is(player.getFirstName())))
                .andExpect(jsonPath("lastName", is(player.getLastName())));
    }

    @Test
    void createPlayerWithFalseMediaType() throws Exception {
        mvc.perform(post("/player")
                .contentType(MediaType.APPLICATION_XML).content("<xml></xml>"))
                .andExpect(status().isUnsupportedMediaType());
    }

    //TODO check response message
    @Test
    void createPlayerWithCorruptJson() throws Exception {
        mvc.perform(post("/player")
                .contentType(MediaType.APPLICATION_JSON).content("<xml></xml>"))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/invalidNames.csv")
    void createPlayerWithInvalidFirstName(String invalidName) throws Exception {
        Player player = new Player();
        player.setFirstName(invalidName);
        player.setLastName("Künkele");

        mvc.perform(post("/player")
                .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(player)))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/invalidNames.csv")
    void createPlayerWithInvalidLastName(String invalidName) throws Exception {
        Player player = new Player();
        player.setFirstName("Dominik");
        player.setLastName(invalidName);

        mvc.perform(post("/player")
                .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(player)))
                .andExpect(status().isBadRequest());
    }
}