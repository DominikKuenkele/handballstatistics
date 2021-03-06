package com.kuenkele.handballstatistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public @ResponseBody
    ResponseEntity<Player> createPlayer(@Valid @RequestBody Player player) {
        if (player.getUuid() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Player Id may not be filled");
        }

        player.setUuid(UUID.randomUUID().toString());
        playerRepository.save(player);

        return ResponseEntity.status(HttpStatus.CREATED).body(player);
    }

    @GetMapping(produces = "application/json", path = "/{uuid}")
    public @ResponseBody
    ResponseEntity<Player> getPlayer(@PathVariable String uuid) {
        Optional<Player> player = playerRepository.findByUuid(uuid);
        if (player.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Specified player was not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(player.get());
    }

    @DeleteMapping(produces = "application/json", path = "/{uuid}")
    public @ResponseBody
    ResponseEntity<DefaultHttpResponse> deletePlayer(@PathVariable String uuid) {
        int number = playerRepository.deleteByUuid(uuid);

        if (number < 1)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Specified player was not found");

        DefaultHttpResponse httpResponse = new DefaultHttpResponse("Deletion successful", "Player with uuid '" + uuid + "' was deleted");
        return ResponseEntity.status(HttpStatus.OK).body(httpResponse);
    }

    @PutMapping(produces = "application/json", consumes = "application/json", path = "/{uuid}")
    public @ResponseBody
    ResponseEntity<Player> updatePlayer(@PathVariable String uuid, @Valid @RequestBody Player newPlayer) {
        Optional<Player> existingPlayer = playerRepository.findByUuid(uuid);
        if (existingPlayer.isPresent()) {
            existingPlayer.get().setFirstName(newPlayer.getFirstName());
            existingPlayer.get().setLastName(newPlayer.getLastName());

            playerRepository.save(existingPlayer.get());

            return ResponseEntity.status(HttpStatus.CREATED).body(existingPlayer.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Specified player was not found");
        }
    }
}
