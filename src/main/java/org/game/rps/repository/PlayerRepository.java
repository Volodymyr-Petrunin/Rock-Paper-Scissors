package org.game.rps.repository;

import org.game.rps.domain.Player;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    @EntityGraph(value = "player-entity-graph", type = EntityGraph.EntityGraphType.FETCH)
    Player findByName(String name);
}
