package org.game.rps.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.game.rps.domain.Result;
import org.game.rps.domain.Selection;

@AllArgsConstructor
@Getter
@Setter
public class ResponsePlayerDto {
    private Selection playerPick;
    private Selection computerPick;
    private Result result;
}
