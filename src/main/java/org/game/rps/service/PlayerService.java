package org.game.rps.service;

import org.game.rps.domain.Player;
import org.game.rps.domain.dto.RequestPlayerDto;
import org.game.rps.domain.mapper.PlayerMapper;
import org.game.rps.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    private final PlayerRepository repository;
    private final PlayerMapper playerMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public PlayerService(PlayerRepository repository,
                         PlayerMapper playerMapper, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.playerMapper = playerMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public void createPlayer(RequestPlayerDto dto){
        Player player = playerMapper.requestPlayerDtoToPlayer(dto);
        player.setPassword(passwordEncoder.encode(player.getPassword()));

        repository.save(player);
    }
}
