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

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
class PlayerControllerUpdateTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    PlayerRepository playerRepository;

    @ParameterizedTest
    @CsvFileSource(resources = "/validNames.csv")
    void updateCorrectPlayer(String validName) throws Exception {
        Player player = new Player();
        player.setFirstName(validName);
        player.setLastName(validName);
        player.setUuid("a6701a34-80da-4ee3-84d0-fec6d0e61c66");

        when(playerRepository.save(player)).thenReturn(player);
        when(playerRepository.findByUuid(player.getUuid())).thenReturn(Optional.of(player));

        mvc.perform(put("/player/a6701a34-80da-4ee3-84d0-fec6d0e61c66")
                .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(player)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("firstName", is(player.getFirstName())))
                .andExpect(jsonPath("lastName", is(player.getLastName())))
                .andExpect(jsonPath("uuid", is(player.getUuid())));
    }

    @Test
    void updatePlayerWithFalseMediaType() throws Exception {
        mvc.perform(put("/player/a6701a34-80da-4ee3-84d0-fec6d0e61c66")
                .contentType(MediaType.APPLICATION_XML).content("<xml></xml>"))
                .andExpect(status().isUnsupportedMediaType());
    }

    //TODO check response message
    @Test
    void updatePlayerWithCorruptJson() throws Exception {
        mvc.perform(put("/player/a6701a34-80da-4ee3-84d0-fec6d0e61c66")
                .contentType(MediaType.APPLICATION_JSON).content("<xml></xml>"))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/invalidNames.csv")
    void updatePlayerWithInvalidFirstName(String invalidName) throws Exception {
        Player player = new Player();
        player.setFirstName(invalidName);
        player.setLastName("Künkele");
        player.setUuid("a6701a34-80da-4ee3-84d0-fec6d0e61c66");

        mvc.perform(put("/player/a6701a34-80da-4ee3-84d0-fec6d0e61c66")
                .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(player)))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/invalidNames.csv")
    void updatePlayerWithInvalidLastName(String invalidName) throws Exception {
        Player player = new Player();
        player.setFirstName("Dominik");
        player.setLastName(invalidName);
        player.setUuid("a6701a34-80da-4ee3-84d0-fec6d0e61c66");

        mvc.perform(put("/player/a6701a34-80da-4ee3-84d0-fec6d0e61c66")
                .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(player)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updatePlayerWithNotExistingUuid() throws Exception {
        Player player = new Player();
        player.setFirstName("Dominik");
        player.setLastName("Künkele");
        player.setUuid("a6701a34-80da-4ee3-84d0-fec6d0e61c66");

        when(playerRepository.findByUuid(player.getUuid())).thenReturn(Optional.empty());

        mvc.perform(put("/player/notExistingUuid")
                .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(player)))
                .andExpect(status().isNotFound());
    }
}