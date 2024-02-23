package org.game.rps.service;

import org.game.rps.domain.Player;
import org.game.rps.domain.Result;
import org.game.rps.domain.Statistic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(value = "classpath:/script/statistic_service.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class StatisticServicesTest {

    @Autowired
    private StatisticServices statisticServices;
    private final Statistic expectedStatistic = new Statistic(1L, 20, 10, 5, 5);
    private final Player expectedPlayer = new Player(1L, "Player", null, expectedStatistic);
    private final UserDetailsImpl userDetails = new UserDetailsImpl(expectedPlayer);
    private Statistic expected;
    private Statistic actual;

    @Test
    void testCreateDefaultStatistic_ShouldCreateStatistic_WithZeroData() {
         // set ID 2 because ID 1 already exists in expectedStatistic.
         expected = new Statistic(2L, 0, 0, 0, 0);

         actual = statisticServices.createDefaultStatistic();

         assertEquals(expected, actual);
    }

    @Test
    void testUpdateStatistic_ShouldUpdateStatistic_TotalGamesAndWonGames() {
        expected = new Statistic(1L, 21, 11, 5, 5); // Expected for changed total and won games

        statisticServices.updateStatistic(userDetails, Result.WIN);

        actual = userDetails.getPlayer().getStatistic();

        assertEquals(expected, actual);
    }

    @Test
    void testUpdateStatistic_ShouldUpdateStatistic_TotalGamesAndLostGames() {
        expected = new Statistic(1L, 21, 10, 6, 5);

        statisticServices.updateStatistic(userDetails, Result.LOSE);

        actual = userDetails.getPlayer().getStatistic();

        assertEquals(expected, actual);
    }

    @Test
    void testUpdateStatistic_ShouldUpdateStatistic_TotalGamesAndDrawGames() {
        expected = new Statistic(1L, 21, 10, 5, 6);

        statisticServices.updateStatistic(userDetails, Result.DRAW);

        actual = userDetails.getPlayer().getStatistic();

        assertEquals(expected, actual);
    }

}