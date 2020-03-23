package com.kuenkele.handballstatistics;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//TODO check if has data --> unassign from all teams and delete

@ExtendWith(MockitoExtension.class)
@WebMvcTest
class PlayerControllerDeleteTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    PlayerRepository playerRepository;

    @Test
    void deletePlayerWithCorrectUuid() throws Exception {
        Player player = new Player();
        player.setFirstName("Dominik");
        player.setLastName("Künkele");
        player.setUuid("a6701a34-80da-4ee3-84d0-fec6d0e61c66");

        when(playerRepository.deleteByUuid(player.getUuid())).thenReturn(1);

        mvc.perform(delete("/player/" + player.getUuid()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is("Deletion successful")))
                .andExpect(jsonPath("message", is("Player with uuid '" + player.getUuid() + "' was deleted")));
    }

    @Test
    void deletePlayerWithNotExistingUUID() throws Exception {
        mvc.perform(delete("/player/notExistingId"))
                .andExpect(status().isNotFound());
    }
}