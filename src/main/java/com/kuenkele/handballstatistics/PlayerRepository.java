package com.kuenkele.handballstatistics;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PlayerRepository extends CrudRepository<Player, Integer> {

    @Query("select p from Player p where p.uuid = ?1")
    Optional<Player> findByUuid(String uuid);

    @Query("delete from Player p where p.uuid = ?1")
    @Modifying
    @Transactional
    int deleteByUuid(String uuid);
}
