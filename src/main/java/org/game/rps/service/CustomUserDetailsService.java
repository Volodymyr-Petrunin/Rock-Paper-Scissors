package org.game.rps.service;

import org.game.rps.domain.Player;
import org.game.rps.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final PlayerRepository repository;

    @Autowired
    public CustomUserDetailsService(PlayerRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Player player = repository.findByName(username);

        if (player == null){
            throw new UsernameNotFoundException("Player with name: " + username + " was not found.");
        }

        return new UserDetailsImpl(player);
    }
}
