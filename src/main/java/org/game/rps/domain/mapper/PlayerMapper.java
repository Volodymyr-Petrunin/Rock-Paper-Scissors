package org.game.rps.domain.mapper;

import org.game.rps.domain.Player;
import org.game.rps.domain.dto.RequestPlayerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    Player requestPlayerDtoToPlayer(RequestPlayerDto requestPlayerDto);
}
