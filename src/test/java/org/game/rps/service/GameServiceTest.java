package org.game.rps.service;

import org.game.rps.domain.Result;
import org.game.rps.domain.Selection;
import org.game.rps.domain.dto.ResponsePlayerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class GameServiceTest {

    @Autowired
    private GameService gameService;
    @MockBean
    private Random random;
    private final Selection playerPick = Selection.PAPER;
    private ResponsePlayerDto expected;
    private ResponsePlayerDto actual;

    @Test
    void testGetWinner_ShouldReturnCorrect_WinResponsePlayerDto() {
        expected = new ResponsePlayerDto(playerPick, Selection.ROCK, Result.WIN);
        when(random.nextInt(anyInt())).thenReturn(0); // Configure the computer to choose "ROCK"

        actual = gameService.getWinner(playerPick);

        assertEquals(expected, actual);
    }

    @Test
    void testGetWinner_ShouldReturnCorrect_LoseResponsePlayerDto() {
        expected = new ResponsePlayerDto(playerPick, Selection.SCISSORS, Result.LOSE);
        when(random.nextInt(anyInt())).thenReturn(2); // Configure the computer to choose "SCISSORS"

        actual = gameService.getWinner(playerPick);

        assertEquals(expected, actual);
    }

    @Test
    void testGetWinner_ShouldReturnCorrect_DrawResponsePlayerDto() {
        expected = new ResponsePlayerDto(playerPick, Selection.PAPER, Result.DRAW);
        when(random.nextInt(anyInt())).thenReturn(1); // Configure the computer to choose "PAPER"

        actual = gameService.getWinner(playerPick);

        assertEquals(expected, actual);
    }
}