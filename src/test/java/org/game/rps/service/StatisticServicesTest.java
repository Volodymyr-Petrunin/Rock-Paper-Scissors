package org.game.rps.service;

import org.game.rps.domain.Player;
import org.game.rps.domain.Result;
import org.game.rps.domain.Statistic;
import org.game.rps.repository.StatisticRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 I've included the @DirtiesContext annotation because when I attempted to use a script containing
 'ALTER SEQUENCE statistic_seq RESTART WITH 1', it resulted in incorrect behavior, breaking the sequence,
 and causing the ID to become negative (-98 in this case).
 */

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class StatisticServicesTest {

    @Autowired
    private StatisticServices statisticServices;

    @Autowired
    private StatisticRepository statisticRepository;

    private final Statistic statistic = new Statistic(null, 20, 10, 5, 5);
    private final Player expectedPlayer = new Player(null, "Player", null, statistic);
    private final UserDetailsImpl userDetails = new UserDetailsImpl(expectedPlayer);
    private Statistic expected;
    private Statistic actual;


    @Test
    void testCreateDefaultStatistic_ShouldCreateStatistic_WithZeroData() {
         expected = new Statistic(1L, 0, 0, 0, 0);

         actual = statisticServices.createDefaultStatistic();

        System.out.println(statisticRepository.findAll());

         assertEquals(expected, actual);
    }

    @Test
    void testUpdateStatistic_ShouldUpdateStatistic_TotalGamesAndWonGames() {
        expected = new Statistic(1L, 21, 11, 5, 5); // Expected for changed total and won games

        statisticServices.updateStatistic(userDetails, Result.WIN);

        System.out.println(statisticRepository.findAll());

        actual = statisticRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("Can't fetch statistic"));

        assertEquals(expected, actual);
    }

    @Test
    void testUpdateStatistic_ShouldUpdateStatistic_TotalGamesAndLostGames() {
        expected = new Statistic(1L, 21, 10, 6, 5);

        statisticServices.updateStatistic(userDetails, Result.LOSE);

        System.out.println(statisticRepository.findAll());

        actual = statisticRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("Can't fetch statistic"));

        assertEquals(expected, actual);
    }

    @Test
    void testUpdateStatistic_ShouldUpdateStatistic_TotalGamesAndDrawGames() {
        expected = new Statistic(1L, 21, 10, 5, 6);

        statisticServices.updateStatistic(userDetails, Result.DRAW);

        System.out.println(statisticRepository.findAll());

        actual = statisticRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("Can't fetch statistic"));

        assertEquals(expected, actual);
    }

}