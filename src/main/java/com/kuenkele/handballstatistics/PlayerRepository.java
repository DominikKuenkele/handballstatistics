package com.kuenkele.handballstatistics;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlayerRepository extends CrudRepository<Player, Integer> {

    @Query("select p from Player p where p.uuid = ?1")
    Optional<Player> findByUuid(String uuid);
}
