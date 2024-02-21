package org.game.rps.domain.dto;

import lombok.*;
import org.game.rps.domain.Result;
import org.game.rps.domain.Selection;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class ResponsePlayerDto {
    private Selection playerPick;
    private Selection computerPick;
    private Result result;
}
