package org.game.rps.service;

import org.game.rps.domain.Result;
import org.game.rps.domain.Selection;
import org.game.rps.domain.dto.ResponsePlayerDto;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GameService {
    private final Random random;

    public GameService(Random random) {
        this.random = random;
    }

    public ResponsePlayerDto getWinner(Selection playerPick){
        Selection computerPick = getRandomElement();
        Result result = determineWinner(playerPick, computerPick);

        return new ResponsePlayerDto(playerPick, computerPick, result);
    }

    private Result determineWinner(Selection playerPick, Selection computerPick){
        if (playerPick.beats(computerPick)){
            return Result.WIN;
        } else if (computerPick.beats(playerPick)){
            return Result.LOSE;
        } else {
            return Result.DRAW;
        }
    }

    private Selection getRandomElement() {
        Selection[] selections = Selection.values();
        return selections[random.nextInt(selections.length)];
    }
}
