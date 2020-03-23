package com.kuenkele.handballstatistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerDAO playerDAO;

    @Autowired
    private PlayerRepository playerRepository;

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Player> createPlayer(@Valid  @RequestBody Player player) {
        if (player.getUuid() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Player Id may not be filled");
        }

        player.setUuid(UUID.randomUUID().toString());
        playerRepository.save(player);

        return ResponseEntity.status(HttpStatus.CREATED).body(player);
    }
}
