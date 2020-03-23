package com.kuenkele.handballstatistics;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
class PlayerControllerGetTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    PlayerRepository playerRepository;

    @Test
    void getPlayerWithCorrectUUID() throws Exception {
        Player player = new Player();
        player.setFirstName("Dominik");
        player.setLastName("Künkele");
        player.setUuid("a6701a34-80da-4ee3-84d0-fec6d0e61c66");

        when(playerRepository.findByUuid(player.getUuid())).thenReturn(Optional.of(player));

        mvc.perform(get("/player/" + player.getUuid()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName", is(player.getFirstName())))
                .andExpect(jsonPath("lastName", is(player.getLastName())))
                .andExpect(jsonPath("uuid", is(player.getUuid())));
    }

    @Test
    void getPlayerWithNotExistingUUID() throws Exception {
        mvc.perform(get("/player/notExistingId"))
                .andExpect(status().isNotFound());
    }
}