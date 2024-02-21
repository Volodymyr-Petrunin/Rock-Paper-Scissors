package org.game.rps.service;

import org.game.rps.domain.Player;
import org.game.rps.domain.Statistic;
import org.game.rps.domain.dto.RequestPlayerDto;
import org.game.rps.domain.mapper.PlayerMapper;
import org.game.rps.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.Mockito.*;

@SpringBootTest
class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private PlayerMapper playerMapper;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    @Mock
    private StatisticServices statisticServices;
    @InjectMocks
    private PlayerService playerService;

    private final Statistic defaultStatistic = new Statistic(1L, 0, 0, 0, 0);

    @Test
    void testCreatePlayer_ShouldSavePlayerWithEncodedPasswordAndDefaultStatistic() {
        RequestPlayerDto requestPlayerDto = new RequestPlayerDto();
        requestPlayerDto.setName("Player");
        requestPlayerDto.setPassword("123");

        Player player = new Player(null, "Player", "encodedPassword", null);

        when(playerMapper.requestPlayerDtoToPlayer(requestPlayerDto)).thenReturn(player);
        when(passwordEncoder.encode(requestPlayerDto.getPassword())).thenReturn("encodedPassword");
        when(statisticServices.createDefaultStatistic()).thenReturn(defaultStatistic);
        when(playerRepository.save(any(Player.class))).thenReturn(any());

        playerService.createPlayer(requestPlayerDto);

        verify(playerMapper, times(1)).requestPlayerDtoToPlayer(requestPlayerDto);
        verify(passwordEncoder, times(1)).encode("encodedPassword");
        verify(statisticServices, times(1)).createDefaultStatistic();
    }

}