package org.game.rps.repository;

import org.game.rps.domain.Player;
import org.game.rps.domain.Statistic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = "classpath:script/player_repository.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository repository;

    // create expected data, same with a script

    private final List<Statistic> expectedStatistic = List.of(
            new Statistic(1L, 10, 2, 4, 4),
            new Statistic(2L, 22, 12, 5, 5),
            new Statistic(3L, 4, 1, 1, 2)
    );

    private final List<Player> expectedPlayers = List.of(
            new Player(1L, "Player1", null, expectedStatistic.get(0)),
            new Player(2L, "Player2", null, expectedStatistic.get(1)),
            new Player(3L, "Player3", null, expectedStatistic.get(2))
    );

    private Player actual;
    private Player expected;

    @Test
    void testFindPlayerByName_ShouldFindCorrectPlayer_AndReturnHim() {
        String expectedName = expectedPlayers.get(0).getName();
        expected = expectedPlayers.get(0);

        actual = repository.findByName(expectedName);

        assertEquals(expected, actual);
    }

    @Test
    void testFindPlayerByName_ShouldNotFindAnyStudent_WithWrongName() {
        String wrongName = "wrongName";
        expected = null;

        actual = repository.findByName(wrongName);

        assertEquals(expected, actual);
    }

    @Test
    void testFindPlayerByName_ShouldFindCorrectPlayer_AndCorrectStatistic() {
        String expectedName = expectedPlayers.get(1).getName();
        expected = expectedPlayers.get(1);
        Statistic expectedStats = expected.getStatistic();

        actual = repository.findByName(expectedName);
        Statistic actualStats = actual.getStatistic();


        assertEquals(expectedStats, actualStats);
    }
}